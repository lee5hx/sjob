# sjob
Quartz Operation Command Line

## Command Line

```
sjob [options] [<arg>=job_hash]
 -a                   List Quartz ALL Jobs
 -d,--delete <arg>    Delete job
 -e                   List Quartz Executing Jobs
 -h,--help            Print usage
 -p,--pause <arg>     Pause job
 -r,--resume <arg>    Resume job
 -ro,--runOne <arg>   Run once job
 -v,--version         Show the SJob version information
```

```
[java@web-schedule ~]$ sjob -a
HASH        NAME             GROUP                                   CRON-EXPRESSION            STATUS   NEXT-FIRE-TIME          PREVIOUS-FIRE-TIME      DESCRIPTION
xxxxxxxxxx  xxxxxxJob        CYCLE_JOB_GROUP                         0 * * * * ?                NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:00     xxxxxxjob,每隔60s执行一次
xxxxxxxxxx  xxxxxxJob        DAILY_JOB_GROUP                         0 0 8 * * ?                NORMAL   2016-12-03 08:00:00     2016-12-02 08:00:00     每天的上午8点,xxxxxjob操作
xxxxxxxxxx  xxxxxxxJob       CYCLE_SERIOUS_CHECK_DATA_JOB_GROUP      0 0/60 * * * ?             NORMAL   2016-12-02 15:00:00     2016-12-02 14:00:00     每1分钟,检查xxx功能的数据
```
