#  Docker安装

[centos7设置阿里云yum源、docker源和docker镜像加速](https://blog.csdn.net/shallow72/article/details/123912946)

1. 备份yum文件

~~~Shell
mv /etc/yum.repos.d/CentOS-Base.repo  /etc/yum.repos.d/CentOS-Base.repo-backup
~~~

2. 下载yum文件

~~~
curl -o /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-7.repo
~~~

3. 设置缓存

~~~Shell
yum clean all
yum makecache
yum update -y
yum upgrade -y
~~~

4. 安装基础工具

~~~Shell
yum install -y yum-utils device-mapper-persistent-data lvm2
~~~

5. 添加软件源信息

~~~shell
yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
~~~

6. 查看仓库中所有Docker版本

~~~Shell
yum list docker-ce --showduplicates | sort -r
~~~

7. 安装指定版本Docker

~~~Shell
yum install docker-ce-19.03.11 -y
~~~

8. 启动并加入开机启动

~~~Shell
systemctl start docker
systemctl enable docker
~~~



# Kurberbenetes部署方案

## 基本功能

1. 将应用水平扩容到多个集群
2. 为扩容的实例提供负载均衡的策略
3. 提供基本的健康检查和自愈能力
4. 实现任务的统一调度

## 部署目标

1. 在所有的节点上安装Docker、kubeadm和kubelet
2. 部署容器网络插件flannel

## 部署架构

| IP                           | 域名     | 备注   | 环境   |      |
| ---------------------------- | ------ | ---- | ---- | ---- |
| 172.17.227.75(120.24.93.88)  | master | 主节点  | 4C8G |      |
| 172.17.227.74(120.24.96.71)  | node1  | 从节点1 | 4C8G |      |
| 172.17.227.73(47.107.51.178) | node2  | 从节点2 | 4C8G |      |

## 安装基础软件

* 配置master和work节点的域名

  ~~~shell 
  vim /etc/hosts

  172.17.227.75 master
  172.17.227.74 node1
  172.17.227.73 node2
  ~~~

* 设置域名解析服务器

  ~~~shell
  echo nameserver 114.114.114.114 > /etc/resolv.conf
  ~~~

* 下载阿里云的yum源repo文件

  ~~~shell
  curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
  ~~~

* 安装基本软件包

  ~~~shell
  yum install wget net-tools ntp git ‐y
  ~~~

* 同步系统时间

  ~~~shell
  ntpdate 0.asia.pool.ntp.org
  ~~~

* 配置Docker，K8S的阿里云yum源

  ~~~shell
  cat >>/etc/yum.repos.d/kubernetes.repo <<EOF
  [kubernetes]
  name=Kubernetes
  baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
  enabled=1
  gpgcheck=1
  repo_gpgcheck=1
  gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
  EOF
  # 查看repo列表
  yum repolist
  ~~~

* 确保br_netfilter 模块被加载

  ~~~Shell
  # 确定是否已经加载
  lsmod | grep br_netfilter
  # 加载该模块
  modprobe br_netfilter
  ~~~

* 将桥接的IPv4流量传递到iptables的链

  ~~~shell
  cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
  br_netfilter
  EOF
  
  echo "1" > /proc/sys/net/bridge/bridge-nf-call-iptables
  vim /etc/sysctl.d/k8s.conf
  net.bridge.bridge-nf-call-ip6tables = 1
  net.bridge.bridge-nf-call-iptables = 1
  ~~~

* 关闭防火墙

  ~~~she
  systemctl stop firewalld
  systemctl disable firewalld
  ~~~
  
* 关闭SeLinux

  ~~~shell
  setenforce 0
  sed -i "s/SELINUX=enforcing/SELINUX=disabled/g" /etc/selinux/config
  ~~~
  
* 关闭swap(如果有)

  ~~~shell
  swapoff -a
  yes | cp /etc/fstab /etc/fstab_bak
  vim /etc/fstab
  # 注释下面这一行
  #/dev/mapper/centos-swap swap                    swap    defaults        0 0
  ~~~
  
  