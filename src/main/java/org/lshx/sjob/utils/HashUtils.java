package org.lshx.sjob.utils;

import java.security.MessageDigest;

/**
 * Created by lee5hx on 16/11/23.
 */
public class HashUtils {
    public static String sha1JobByStr(String str){
        String rt = "";
        try{
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            sha1.update(str.getBytes());
            rt = toHex(sha1.digest());
            //System.out.println(rt);
            rt = rt.substring(rt.length()-10,rt.length());

        }catch (Exception e){
            e.printStackTrace();
        }
        return rt;
    }


    private static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        int len = digest.length;
        String out = null;
        for (int i = 0; i < len; i++) {
//   out = Integer.toHexString(0xFF & digest[i] + 0xABCDEF); //加任意 salt
            out = Integer.toHexString(0xFF & digest[i]);//原始方法
            if (out.length() == 1) {
                sb.append("0");//如果为1位 前面补个0
            }
            sb.append(out);
        }
        return sb.toString();
    }
}
