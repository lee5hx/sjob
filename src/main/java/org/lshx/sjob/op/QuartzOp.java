package org.lshx.sjob.op;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.lshx.sjob.vo.OpResult;
import org.lshx.sjob.vo.QuartzJob;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lee5hx on 16/10/25.
 */
public class QuartzOp {

    private static final String API_KEY = "kF8Z2zH92fCch3ha";
    private static final String QUARTZ_SERVER = "http://localhost:10101/";

    private static final int LN_HASH_ID_CHARS_SIZE = 12;
    private static final int LN_NAME_CHARS_SIZE = 37;
    private static final int LN_GROUP_CHARS_SIZE = 40;
    private static final int LN_CRON_EXPRESSION_CHARS_SIZE = 27;
    private static final int NEXT_FIRE_TIME_CHARS_SIZE = 24;
    private static final int PREVIOUS_FIRE_TIME_CHARS_SIZE = 24;
    private static final int LN_STATUS_CHARS_SIZE = 9;
    private static final int LN_DESCRIPTION_CHARS_SIZE = 30;

    public String opPrintln(String value, int lnSize) {
        StringBuilder sb = new StringBuilder();

        int se = lnSize - value.length();
        if (se > 0) {
            sb.append(value);
        } else {
            value = value.substring(0, lnSize - 6) + "...";
            se = lnSize - value.length();
            sb.append(value);
        }

        for (int i = 0; i < se; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private void printlnTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append(opPrintln("HASH", LN_HASH_ID_CHARS_SIZE));
        sb.append(opPrintln("NAME", LN_NAME_CHARS_SIZE));
        sb.append(opPrintln("GROUP", LN_GROUP_CHARS_SIZE));
        sb.append(opPrintln("CRON-EXPRESSION", LN_CRON_EXPRESSION_CHARS_SIZE));
        sb.append(opPrintln("STATUS", LN_STATUS_CHARS_SIZE));
        sb.append(opPrintln("NEXT-FIRE-TIME", NEXT_FIRE_TIME_CHARS_SIZE));
        sb.append(opPrintln("PREVIOUS-FIRE-TIME", PREVIOUS_FIRE_TIME_CHARS_SIZE));
        sb.append(opPrintln("DESCRIPTION", LN_DESCRIPTION_CHARS_SIZE));
        System.out.println(sb.toString());



    }

    private void printlnError(OpResult result) {
        System.out.println("error:" + result.getCode() + "," + result.getErrorMessage());
    }

    private void printlnRow(QuartzJob job) {
        StringBuilder sb = new StringBuilder();
        sb.append(opPrintln(job.getHashId(), LN_HASH_ID_CHARS_SIZE));
        sb.append(opPrintln(job.getName(), LN_NAME_CHARS_SIZE));
        sb.append(opPrintln(job.getGroup(), LN_GROUP_CHARS_SIZE));
        sb.append(opPrintln(job.getCronExpression(), LN_CRON_EXPRESSION_CHARS_SIZE));
        sb.append(opPrintln(job.getStatus(), LN_STATUS_CHARS_SIZE));
        sb.append(opPrintln(job.getNextFireTime(), NEXT_FIRE_TIME_CHARS_SIZE));
        sb.append(opPrintln(job.getPreviousFireTime(), PREVIOUS_FIRE_TIME_CHARS_SIZE));
        sb.append(opPrintln(job.getDescription(), LN_DESCRIPTION_CHARS_SIZE));
        System.out.println(sb.toString());
    }


    public OpResult<String> resumeJob(String hashId) {
        OpResult<String> result = new OpResult<>();
        //String[] slis = jobKey.split("\\.", -1);
        try {
            String jobKey=getJobKeyByHashId(hashId);

            HttpResponse<JsonNode> jsonResponse = Unirest.post(QUARTZ_SERVER + "quartz/job/resumeJob")
                    .header("accept", "application/json")
                    .queryString("apiKey", API_KEY)
                    .queryString("jobKey", jobKey)
                    .asJson();
            //System.out.println(jsonResponse.getBody().toString());
            JSONObject jsonObject = jsonResponse.getBody().getObject();
            int status = jsonObject.getInt("status");
            if (status == 0) {
                System.out.println("JOB恢复成功!");
            } else {
                JSONObject errorJSONObject = jsonObject.getJSONObject("error");
                result.setCode(errorJSONObject.getInt("code"));
                result.setErrorMessage(errorJSONObject.getString("message"));
                printlnError(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public OpResult<String> deleteJob(String hashId) {
        OpResult<String> result = new OpResult<>();
        //String[] slis = jobKey.split("\\.", -1);
        try {
            String jobKey=getJobKeyByHashId(hashId);

            HttpResponse<JsonNode> jsonResponse = Unirest.post(QUARTZ_SERVER + "quartz/job/deleteJob")
                    .header("accept", "application/json")
                    .queryString("apiKey", API_KEY)
                    .queryString("jobKey", jobKey)
                    .asJson();
            //System.out.println(jsonResponse.getBody().toString());
            JSONObject jsonObject = jsonResponse.getBody().getObject();
            int status = jsonObject.getInt("status");
            if (status == 0) {
                System.out.println("JOB删除成功!");
            } else {
                JSONObject errorJSONObject = jsonObject.getJSONObject("error");
                result.setCode(errorJSONObject.getInt("code"));
                result.setErrorMessage(errorJSONObject.getString("message"));
                printlnError(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public OpResult<String> pauseJob(String hashId) {
        OpResult<String> result = new OpResult<>();
        //String[] slis = jobKey.split("\\.", -1);
        try {

            String jobKey=getJobKeyByHashId(hashId);

            HttpResponse<JsonNode> jsonResponse = Unirest.post(QUARTZ_SERVER + "quartz/job/pauseJob")
                    .header("accept", "application/json")
                    .queryString("apiKey", API_KEY)
                    .queryString("jobKey", jobKey)
                    .asJson();
            //System.out.println(jsonResponse.getBody().toString());
            JSONObject jsonObject = jsonResponse.getBody().getObject();
            int status = jsonObject.getInt("status");
            if (status == 0) {
                System.out.println("JOB暂停成功!");
            } else {
                JSONObject errorJSONObject = jsonObject.getJSONObject("error");
                result.setCode(errorJSONObject.getInt("code"));
                result.setErrorMessage(errorJSONObject.getString("message"));
                printlnError(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public String getJobKeyByHashId(String hashId){
        String jobKey="";
        List<QuartzJob> jobs = getJobsByOp("all",false).getResult();
        for(QuartzJob job:jobs){
            if(hashId.equals(job.getHashId())){
                jobKey = job.getName()+"."+job.getGroup();
                break;
            }
        }
        return jobKey;
    }

    public OpResult<String> runOneJob(String hashId) {
        OpResult<String> result = new OpResult<>();
        try {
            String jobKey=getJobKeyByHashId(hashId);
            HttpResponse<JsonNode> jsonResponse = Unirest.post(QUARTZ_SERVER + "quartz/job/runOneJob")
                    .header("accept", "application/json")
                    .queryString("apiKey", API_KEY)
                    .queryString("jobKey", jobKey)
                    .asJson();
            //System.out.println(jsonResponse.getBody().toString());
            JSONObject jsonObject = jsonResponse.getBody().getObject();
            int status = jsonObject.getInt("status");
            if (status == 0) {
                System.out.println("JOB已经触发!,使用sjob -e 命令查看正在运行的JOB.");
            } else {
                JSONObject errorJSONObject = jsonObject.getJSONObject("error");
                result.setCode(errorJSONObject.getInt("code"));
                result.setErrorMessage(errorJSONObject.getString("message"));
                printlnError(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public OpResult<List<QuartzJob>> getJobsByOp(String op,boolean isPrint) {

        OpResult<List<QuartzJob>> result = new OpResult<>();
        List<QuartzJob> jobs = new ArrayList<>();
        QuartzJob job;
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post(QUARTZ_SERVER + "quartz/job/query")
                    .header("accept", "application/json")
                    .queryString("apiKey", API_KEY).queryString("op", op)
                    .field("parameter", "value")
                    .field("foo", "bar")
                    .asJson();
            //System.out.println(jsonResponse.getBody().toString());

            JSONObject jsonObject = jsonResponse.getBody().getObject();
            int status = jsonObject.getInt("status");
            if (status == 0) {
                if(isPrint) printlnTitle();
                result.setCode(status);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    job = new QuartzJob();
                    jsonObject = jsonArray.getJSONObject(i);
                    job.setHashId(jsonObject.getString("hashId"));
                    job.setName(jsonObject.getString("jobName"));
                    job.setGroup(jsonObject.getString("jobGroup"));
                    job.setDescription(jsonObject.getString("description"));
                    job.setCronExpression(jsonObject.getString("cronExpression"));
                    job.setStatus(jsonObject.getString("jobStatus"));
                    if(jsonObject.get("previousFireTime").toString().equals("null")){
                        job.setPreviousFireTime("-");
                    }else {
                        job.setPreviousFireTime(jsonObject.getString("previousFireTime"));
                    }
                    if(jsonObject.get("nextFireTime").toString().equals("null")){
                        job.setNextFireTime("-");
                    }else {
                        job.setNextFireTime(jsonObject.getString("nextFireTime"));
                    }
                    if(isPrint) printlnRow(job);
                    jobs.add(job);
                }
                result.setResult(jobs);
            } else {
                JSONObject errorJSONObject = jsonObject.getJSONObject("error");
                result.setCode(errorJSONObject.getInt("code"));
                result.setErrorMessage(errorJSONObject.getString("message"));
                if(isPrint) printlnError(result);
            }
            //System.out.println(result.getResult().size());
            //System.out.println(result.getCode()+"|"+result.getErrorMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
