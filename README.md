#sjob
Quartz Operation Command Line

##Command Line

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
HASH        NAME                                 GROUP                                   CRON-EXPRESSION            STATUS   NEXT-FIRE-TIME          PREVIOUS-FIRE-TIME      DESCRIPTION
105ac15984  MgRechargeConfirmFromQueueJob        CYCLE_JOB_GROUP                         0 * * * * ?                NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:00     处理redis队列中的MG充值信息,每隔60s执行一次
6873c828c6  CheatMgWithdrawalJob                 DAILY_JOB_GROUP                         0 0 8 * * ?                NORMAL   2016-12-03 08:00:00     2016-12-02 08:00:00     每天的上午8点,进行一次作弊MG的提现操作
69688b0312  CheckWithdrawalStatus99Job           CYCLE_SERIOUS_CHECK_DATA_JOB_GROUP      0 0/60 * * * ?             NORMAL   2016-12-02 15:00:00     2016-12-02 14:00:00     每1分钟,检查提现状态为99的数据
4bbb766d71  CheckFinanceJob                      CYCLE_SERIOUS_CHECK_DATA_JOB_GROUP      0 0/10 * * * ?             NORMAL   2016-12-02 14:30:00     2016-12-02 14:20:00     每10分钟,检查一次财务数据(有财务变动数据)
850c011804  InletYCQueueJob                      CYCLE_JOB_GROUP                         0/30 * * * * ?             NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:30     每30秒,根据排包数YC进场
af6ea14679  DailyGenCenturyMPJob                 DAILY_JOB_GROUP                         0 50 7 * * ?               NORMAL   2016-12-03 07:50:00     2016-12-02 07:50:00     每日上午7:50,生成可分配的智慧包(空包)
a83a9dd735  SyncBlockChainJob                    CYCLE_JOB_GROUP                         0/2 * * * * ?              NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:58     同步MG钱包区块,2秒钟同步一次
fa99bf9c7e  AutoConfirmBtcRechargeJob            CYCLE_JOB_GROUP                         0 0/5 * * * ?              NORMAL   2016-12-02 14:30:00     2016-12-02 14:25:00     每5分钟,自动确认一次BTC充值
513c49798d  DailyGeneratorDifficultyJob          DAILY_JOB_GROUP                         59 59 7 * * ?              NORMAL   2016-12-03 07:59:59     2016-12-02 07:59:59     每日上午7:59:59,生成上涨难度系数
7c9696eca9  DelMeditationComplaintsFromRedisJob  CYCLE_JOB_GROUP                         0 0 * * * ?                NORMAL   2016-12-02 15:00:00     2016-12-02 14:00:00     每1小时,清除一次脑波冷数据
2d49cd570a  DailyGeneratorMGPriceJob             DAILY_JOB_GROUP                         0 0 8 * * ?                NORMAL   2016-12-03 08:00:00     2016-12-02 08:00:00     每日上午8:00,MG价格生成
aa726b0b30  CheckFinanceActionJob                DAILY_CHECK_DATA_JOB_GROUP              0 12 8 * * ?               NORMAL   2016-12-03 08:12:00     2016-12-02 08:12:00     每日上午8点12,检查财务Action数据
a5b7964b96  MpsExpiredDistributionJob            CYCLE_JOB_GROUP                         0 0 */2 * * ?              NORMAL   2016-12-02 16:00:00     2016-12-02 14:00:00     每隔2小时执行一次MPS到期解锁操作
efdaf81e54  ComputeCenturySharePerDayJob         DAILY_JOB_GROUP                         0 0 9 * * ?                NORMAL   2016-12-03 09:00:00     2016-12-02 09:00:00     每日上午9:00,世纪分享积分计算
a152bea3a4  CheckRechargeAddressAndGenerateJob   CYCLE_JOB_GROUP                         0 */30 * * * ?             NORMAL   2016-12-02 14:30:00     2016-12-02 14:00:00     检查充值地址是否为空并生成的任务,30分钟一次
f320c21326  SyncPriceJob                         CYCLE_JOB_GROUP                         */30 * * * * ?             NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:30     比特币汇率价格同步,30秒同步一次
f6fb9c7f83  AnalysisMgBuyAmountJob               CYCLE_JOB_GROUP                         0 10 8 * * ?               NORMAL   2016-12-03 08:10:00     2016-12-02 08:10:00     统计今天8点到昨天8点之间MG买包总花费,每天上...
db022f5002  StatisMeditationPerDayJob            DAILY_JOB_GROUP                         0 5 8 * * ?                NORMAL   2016-12-03 08:05:00     2016-12-02 08:05:00     每日上午8:05,冥想数据统计汇总
789216bf1f  CleanFinanceJob                      DAILY_JOB_GROUP                         0 0 8 * * ? 2099           NORMAL   2099-01-01 08:00:00     -                       手工触发用财务数据清理
e341ec63b5  CheckFinanceOutsideJob               DAILY_CHECK_DATA_JOB_GROUP              0 15 8 * * ?               NORMAL   2016-12-03 08:15:00     2016-12-02 08:15:00     每日上午8点15,检查外部财务数据批量
0a7fd485f1  CheckFinanceInsideJob                DAILY_CHECK_DATA_JOB_GROUP              0 10 8 * * ?               NORMAL   2016-12-03 08:10:00     2016-12-02 08:10:00     每日上午8点10,检查内部财务数据批量
edb123721f  DailyGeneratorYcMindGeneJob          DAILY_JOB_GROUP                         0 5 8 * * ?                NORMAL   2016-12-03 08:05:00     2016-12-02 08:05:00     每日上午08:05:00,生成YCMG产量
53de4c14f0  NoticeFinanceReJob                   CYCLE_JOB_GROUP                         0 0/1 * * * ?              NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:00     财务变化通知邮件,每5分钟检查一次待发送邮件
ff6078fa04  QueueSendSmsAlertJob                 CYCLE_JOB_GROUP                         0 */1 * * * ?              NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:00     每一分钟,短信订单提醒发送.
3573739447  CleanGBrainWavePageCacheJob          CYCLE_JOB_GROUP                         0 */30 * * * ?             NORMAL   2016-12-02 14:30:00     2016-12-02 14:00:00     每30分钟,清除一次全球脑波查询缓存.
fad889a17b  SyncBterInfoJob                      CYCLE_JOB_GROUP                         0/20 * * * * ?             NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:40     获取比特儿网站MG信息,20秒同步一次
a87047efc8  MinerContributionStatisticsPerDayJob DAILY_JOB_GROUP                         0 30 9 * * ?               NORMAL   2016-12-03 09:30:00     2016-12-02 09:30:00     每日上午9:30,矿工贡献统计计算
0e864a3e16  DailyCalculateEquilibriumFactorJob   DAILY_JOB_GROUP                         0 30 8 * * ?               NORMAL   2016-12-03 08:30:00     2016-12-02 08:30:00     每日上午08:30:00,计算昨日的平衡系数
3b415019e8  BtcTransactionNotifyJob              CYCLE_JOB_GROUP                         0 * * * * ?                NORMAL   2016-12-02 14:27:00     2016-12-02 14:26:00     btc充值回执,1分钟一次
c7652615f6  SyncMeditationComplaintsToDbJob      CYCLE_JOB_GROUP                         0 */5 * * * ?              NORMAL   2016-12-02 14:30:00     2016-12-02 14:25:00     每5分钟,脑波投诉数同步一次.
7086337a10  UserMpsDistributionJob               CYCLE_JOB_GROUP                         0 30 9 * * ?               NORMAL   2016-12-03 09:30:00     2016-12-02 09:30:00     每天早上9:30进行MPS分配MG冻结操作
6f5e4f2035  MpsDataAnalysisJob                   CYCLE_JOB_GROUP                         0 0 8 * * ?                NORMAL   2016-12-03 08:00:00     2016-12-02 08:00:00     每天早8点进行一次MPS数据汇总
49fb0034c5  UnfreezeCheatMeditationUserJob       DAILY_JOB_GROUP                         0 0 8 * * ?                NORMAL   2016-12-03 08:00:00     2016-12-02 08:00:00     每日上午8:00,冻结账号解封程序
8a6befd77a  CheckRepeatBtcRechargeJob            CYCLE_SERIOUS_CHECK_DATA_JOB_GROUP      0 0/3 * * * ?              NORMAL   2016-12-02 14:27:00     2016-12-02 14:24:00     每3分钟,检测BTC重复充值
```