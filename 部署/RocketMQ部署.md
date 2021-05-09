## 2主2从(异步复制)

### 1. 集群规划

|     IP      |                 NAME                  |
| :---------: | :-----------------------------------: |
| 172.16.2.20 | rocketmq-nameserver1, master1, slave1 |
| 172.16.2.21 | rocketmq-nameserver2, master2, slave2 |

* 添加hosts信息

~~~shell
vi /etc/hosts

172.16.2.20 rocketmq-nameserver1
172.16.2.20 rocketmq-master1
172.16.2.21 rocketmq-nameserver2
172.16.2.21 rocketmq-master1-slave
~~~

### 2. 部署步骤

#### 1. 安装

~~~shell
# 1.解压
tar -zxvf apache-rocketmq.tar.gz -C /usr/local
ln -s apache-rocketmq rocketmq

# 2.创建存储目录
## master存储目录
mkdir /usr/local/rocketmq/store
mkdir /usr/local/rocketmq/store/commitlog
mkdir /usr/local/rocketmq/store/consumequeue
mkdir /usr/local/rocketmq/store/index

## slave存储目录
mkdir /usr/local/rocketmq/store-s
mkdir /usr/local/rocketmq/store-s/commitlog
mkdir /usr/local/rocketmq/store-s/consumequeue
mkdir /usr/local/rocketmq/store-s/index
~~~

#### 2. 配置文件

* broker-master1配置(172.16.2.20)

~~~shell
vim /usr/local/rocketmq/conf/2m-2s-async/broker-a.properties

brokerClusterName=rocketmq-cluster
#broker 名字，注意此处不同的配置文件填写的不一样
brokerName=broker-a
#0 表示 Master，>0 表示 Slave
brokerId=0
#nameServer 地址，分号分割
namesrvAddr=rocketmq-nameserver1:9876;rocketmq-nameserver2:9876
#broker地址
brokerIP1=172.16.2.20
#在发送消息时，自动创建服务器不存在的 topic，默认创建的队列数
defaultTopicQueueNums=4
#是否允许 Broker 自动创建 Topic，建议线下开启，线上关闭
autoCreateTopicEnable=true
#是否允许 Broker 自动创建订阅组，建议线下开启，线上关闭
autoCreateSubscriptionGroup=true
#Broker 对外服务的监听端口
listenPort=10911
#删除文件时间点，默认凌晨 4 点
deleteWhen=04
#文件保留时间，默认 48 小时
fileReservedTime=120
#commitLog 每个文件的大小默认 1G
mapedFileSizeCommitLog=1073741824
#ConsumeQueue 每个文件默认存 30W 条，根据业务情况调整
mapedFileSizeConsumeQueue=300000
#destroyMapedFileIntervalForcibly=120000
#redeleteHangedFileInterval=120000
#检测物理文件磁盘空间
diskMaxUsedSpaceRatio=88
#存储路径
storePathRootDir=/usr/local/rocketmq/store
#commitLog 存储路径
storePathCommitLog=/usr/local/rocketmq/store/commitlog
#消费队列存储路径存储路径
storePathConsumeQueue=/usr/local/rocketmq/store/consumequeue
#消息索引存储路径
storePathIndex=/usr/local/rocketmq/store/index
#checkpoint 文件存储路径
storeCheckpoint=/usr/local/rocketmq/store/checkpoint
#abort 文件存储路径
abortFile=/usr/local/rocketmq/store/abort
#限制的消息大小
maxMessageSize=65536
#flushCommitLogLeastPages=4
#flushConsumeQueueLeastPages=2
#flushCommitLogThoroughInterval=10000
#flushConsumeQueueThoroughInterval=60000
#Broker 的角色
#- ASYNC_MASTER 异步复制 Master
#- SYNC_MASTER 同步双写 Master
#- SLAVE
brokerRole=ASYNC_MASTER
#刷盘方式
#- ASYNC_FLUSH 异步刷盘
#- SYNC_FLUSH 同步刷盘
flushDiskType=ASYNC_FLUSH
~~~

* broker-slave1配置(172.16.2.21)

~~~shell
vim /usr/local/rocketmq/conf/2m-2s-async/broker-a-s.properties

brokerClusterName=rocketmq-cluster
#broker 名字，注意此处不同的配置文件填写的不一样
brokerName=broker-a
#0 表示 Master，>0 表示 Slave
brokerId=1
#nameServer 地址，分号分割
namesrvAddr=rocketmq-nameserver1:9876;rocketmq-nameserver2:9876
#broker地址
brokerIP1=172.16.2.21
#在发送消息时，自动创建服务器不存在的 topic，默认创建的队列数
defaultTopicQueueNums=4
#是否允许 Broker 自动创建 Topic，建议线下开启，线上关闭
autoCreateTopicEnable=true
#是否允许 Broker 自动创建订阅组，建议线下开启，线上关闭
autoCreateSubscriptionGroup=true
#Broker 对外服务的监听端口
listenPort=10921
#删除文件时间点，默认凌晨 4 点
deleteWhen=04
#文件保留时间，默认 48 小时
fileReservedTime=120
#commitLog 每个文件的大小默认 1G
mapedFileSizeCommitLog=1073741824
#ConsumeQueue 每个文件默认存 30W 条，根据业务情况调整
mapedFileSizeConsumeQueue=300000
#destroyMapedFileIntervalForcibly=120000
#redeleteHangedFileInterval=120000
#检测物理文件磁盘空间
diskMaxUsedSpaceRatio=88
#存储路径
storePathRootDir=/usr/local/rocketmq/store-s
#commitLog 存储路径
storePathCommitLog=/usr/local/rocketmq/store-s/commitlog
#消费队列存储路径存储路径
storePathConsumeQueue=/usr/local/rocketmq/store-s/consumequeue
#消息索引存储路径
storePathIndex=/usr/local/rocketmq/store-s/index
#checkpoint 文件存储路径
storeCheckpoint=/usr/local/rocketmq/store-s/checkpoint
#abort 文件存储路径
abortFile=/usr/local/rocketmq/store-s/abort
#限制的消息大小
maxMessageSize=65536
#flushCommitLogLeastPages=4
#flushConsumeQueueLeastPages=2
#flushCommitLogThoroughInterval=10000
#flushConsumeQueueThoroughInterval=60000
#Broker 的角色
#- ASYNC_MASTER 异步复制 Master
#- SYNC_MASTER 同步双写 Master
#- SLAVE
brokerRole=SLAVE
#刷盘方式
#- ASYNC_FLUSH 异步刷盘
#- SYNC_FLUSH 同步刷盘
flushDiskType=ASYNC_FLUSH
~~~

* broker-master2配置(172.16.2.21)

~~~shell
vim /usr/local/rocketmq/conf/2m-2s-async/broker-b.properties

brokerClusterName=rocketmq-cluster
#broker 名字，注意此处不同的配置文件填写的不一样
brokerName=broker-b
#0 表示 Master，>0 表示 Slave
brokerId=0
#nameServer 地址，分号分割
namesrvAddr=rocketmq-nameserver1:9876;rocketmq-nameserver2:9876
#broker地址
brokerIP1=172.16.2.21
#在发送消息时，自动创建服务器不存在的 topic，默认创建的队列数
defaultTopicQueueNums=4
#是否允许 Broker 自动创建 Topic，建议线下开启，线上关闭
autoCreateTopicEnable=true
#是否允许 Broker 自动创建订阅组，建议线下开启，线上关闭
autoCreateSubscriptionGroup=true
#Broker 对外服务的监听端口
listenPort=10911
#删除文件时间点，默认凌晨 4 点
deleteWhen=04
#文件保留时间，默认 48 小时
fileReservedTime=120
#commitLog 每个文件的大小默认 1G
mapedFileSizeCommitLog=1073741824
#ConsumeQueue 每个文件默认存 30W 条，根据业务情况调整
mapedFileSizeConsumeQueue=300000
#destroyMapedFileIntervalForcibly=120000
#redeleteHangedFileInterval=120000
#检测物理文件磁盘空间
diskMaxUsedSpaceRatio=88
#存储路径
storePathRootDir=/usr/local/rocketmq/store
#commitLog 存储路径
storePathCommitLog=/usr/local/rocketmq/store/commitlog
#消费队列存储路径存储路径
storePathConsumeQueue=/usr/local/rocketmq/store/consumequeue
#消息索引存储路径
storePathIndex=/usr/local/rocketmq/store/index
#checkpoint 文件存储路径
storeCheckpoint=/usr/local/rocketmq/store/checkpoint
#abort 文件存储路径
abortFile=/usr/local/rocketmq/store/abort
#限制的消息大小
maxMessageSize=65536
#flushCommitLogLeastPages=4
#flushConsumeQueueLeastPages=2
#flushCommitLogThoroughInterval=10000
#flushConsumeQueueThoroughInterval=60000
#Broker 的角色
#- ASYNC_MASTER 异步复制 Master
#- SYNC_MASTER 同步双写 Master
#- SLAVE
brokerRole=ASYNC_MASTER
#刷盘方式
#- ASYNC_FLUSH 异步刷盘
#- SYNC_FLUSH 同步刷盘
flushDiskType=ASYNC_FLUSH
~~~

* broker-slave2配置(172.16.2.20)

~~~shell
vim /usr/local/rocketmq/conf/2m-2s-async/broker-b-s.properties

brokerClusterName=rocketmq-cluster
#broker 名字，注意此处不同的配置文件填写的不一样
brokerName=broker-b
#0 表示 Master，>0 表示 Slave
brokerId=1
#nameServer 地址，分号分割
namesrvAddr=rocketmq-nameserver1:9876;rocketmq-nameserver2:9876
#broker地址
brokerIP1=172.16.2.20
#在发送消息时，自动创建服务器不存在的 topic，默认创建的队列数
defaultTopicQueueNums=4
#是否允许 Broker 自动创建 Topic，建议线下开启，线上关闭
autoCreateTopicEnable=true
#是否允许 Broker 自动创建订阅组，建议线下开启，线上关闭
autoCreateSubscriptionGroup=true
#Broker 对外服务的监听端口
listenPort=10921
#删除文件时间点，默认凌晨 4 点
deleteWhen=04
#文件保留时间，默认 48 小时
fileReservedTime=120
#commitLog 每个文件的大小默认 1G
mapedFileSizeCommitLog=1073741824
#ConsumeQueue 每个文件默认存 30W 条，根据业务情况调整
mapedFileSizeConsumeQueue=300000
#destroyMapedFileIntervalForcibly=120000
#redeleteHangedFileInterval=120000
#检测物理文件磁盘空间
diskMaxUsedSpaceRatio=88
#存储路径
storePathRootDir=/usr/local/rocketmq/store-s
#commitLog 存储路径
storePathCommitLog=/usr/local/rocketmq/store-s/commitlog
#消费队列存储路径存储路径
storePathConsumeQueue=/usr/local/rocketmq/store-s/consumequeue
#消息索引存储路径
storePathIndex=/usr/local/rocketmq/store-s/index
#checkpoint 文件存储路径
storeCheckpoint=/usr/local/rocketmq/store-s/checkpoint
#abort 文件存储路径
abortFile=/usr/local/rocketmq/store-s/abort
#限制的消息大小
maxMessageSize=65536
#flushCommitLogLeastPages=4
#flushConsumeQueueLeastPages=2
#flushCommitLogThoroughInterval=10000
#flushConsumeQueueThoroughInterval=60000
#Broker 的角色
#- ASYNC_MASTER 异步复制 Master
#- SYNC_MASTER 同步双写 Master
#- SLAVE
brokerRole=SLAVE
#刷盘方式
#- ASYNC_FLUSH 异步刷盘
#- SYNC_FLUSH 同步刷盘
flushDiskType=ASYNC_FLUSH
~~~

* 修改日志配置文件

~~~shell
mkdir -p /usr/local/rocketmq/logs
cd /usr/local/rocketmq/conf && sed -i 's#${user.home}#/usr/local/rocketmq#g' *.xml
~~~

* 修改脚本启动参数

~~~shell
vim /usr/local/rocketmq/bin/runbroker.sh

JAVA_OPT="${JAVA_OPT} -server -Xms1g -Xmx1g -Xmn512m -XX:PermSize=128m -XX:MaxPermSize=320m"

vim /usr/local/rocketmq/bin/runserver.sh
JAVA_OPT="${JAVA_OPT} -server -Xms1g -Xmx1g -Xmn512m -XX:PermSize=128m -
XX:MaxPermSize=320m"
~~~

#### 3.启动

~~~shell
# 按顺序先启动Nameserver，再启动Broker-master，最后启动Broker-slave

nohup sh /usr/local/rocketmq/bin/mqnamesrv -n 172.16.2.20:9876 &
nohup sh /usr/local/rocketmq/bin/mqnamesrv -n 172.16.2.21:9876 &

nohup sh /usr/local/rocketmq/bin/mqbroker -c /usr/local/rocketmq/conf/2m-2s-async/broker-a.properties >/dev/null 2>&1 &
nohup sh /usr/local/rocketmq/bin/mqbroker -c /usr/local/rocketmq/conf/2m-2s-async/broker-b.properties >/dev/null 2>&1 &

nohup sh /usr/local/rocketmq/bin/mqbroker -c /usr/local/rocketmq/conf/2m-2s-async/broker-a-s.properties >/dev/null 2>&1 &
nohup sh /usr/local/rocketmq/bin/mqbroker -c /usr/local/rocketmq/conf/2m-2s-async/broker-b-s.properties >/dev/null 2>&1 &
~~~



