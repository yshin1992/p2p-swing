-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 192.168.0.160    Database: p2p_njic
-- ------------------------------------------------------
-- Server version	5.1.73

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryId` varchar(32) NOT NULL,
  `createBy` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `effTime` datetime NOT NULL,
  `expTime` datetime NOT NULL,
  `listSort` int(11) NOT NULL,
  `state` varchar(3) NOT NULL,
  `stateTime` datetime NOT NULL,
  `categoryCd` varchar(128) DEFAULT NULL,
  `categoryDesc` varchar(255) DEFAULT NULL,
  `categoryNm` varchar(128) DEFAULT NULL,
  `configed` int(11) DEFAULT NULL,
  `edited` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('001','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','member.mermerkind1','后台选择会员种类','企业会员种类',0,0,NULL),('002','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','member.mermerkind2','前台选择会员种类','会员种类',0,0,NULL),('003','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','memberExt','扩展字段','会员扩展属性',0,0,NULL),('004','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','systemconfig','系统参数配置','系统参数设置',1,1,NULL),('005','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','attachment','上传文件参数配置','文件配置',1,1,NULL),('006','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','rateRange','年化收益率范围','年化收益',0,0,NULL),('007','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','amountRange','融资金额范围','项目规模',0,0,NULL),('008','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','periodRange','融资期限范围','项目期限',0,0,NULL),('009','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','productStatus','前台列表项目状态查询,只展示指定的几个项目状态','项目状态',0,0,NULL),('010','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','member.level','会员等级','会员等级',0,0,NULL),('011','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','periodType','期限单位','期限单位',0,0,NULL),('012','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','member.status','会员状态','会员状态',0,0,NULL),('013','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','link.status','友情链接显示状态','友情链接显示状态',0,0,NULL),('014','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','member.audit.status','认证审核状态（实名认证和银行卡认证共用）','认证审核状态',0,0,NULL),('015','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','artcle.status','文章发布状态','文章发布状态',0,0,NULL),('016','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','member.aut.status','认证状态（已认证和未认证）','认证状态',0,0,NULL),('017','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','repayMethod','还款方式','还款方式',0,0,NULL),('018','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','brrowUse','借款用途','借款用途',0,0,NULL),('019','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','tenderKind','标种类型','标种类型',0,0,NULL),('020','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','product.status','项目所有状态','项目状态',0,0,NULL),('022','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','overdueDays','还款项目中逾期未还的逾期天数','逾期天数',0,0,NULL),('023','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','product.readytender.status','项目待招标状态','项目待招标状态',0,0,NULL),('024','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','rateReferened','扣费方式--费率参考','费率参考',0,0,NULL),('025','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','periodOrDay','扣费方式--期数或天数','期数或天数',0,0,NULL),('026','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','feesetting.feenode','费用节点','费用节点',0,0,NULL),('027','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','interestAlgorithm','计息天数算法','计息',0,0,NULL),('029','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','agreement','合同参数配置','合同参数配置',1,1,NULL),('030','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','contactKind','联系人类别','联系人类别',0,1,NULL),('031','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','brokerConfig','经纪人配置','经纪人配置',1,1,NULL),('032','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','PayApIList','支持接口列表','支持接口列表',0,1,NULL),('033','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','exportTemplate','报表模板配置','报表模板配置',1,0,NULL),('034','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','broker.memberKind','经纪类型','经纪类型',0,0,NULL),('035','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','broker.brokerLevel','经纪级别','经纪级别',0,0,NULL),('036','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','credit.transferMaxTime','转让最大期限','债权转让最大期限',0,1,NULL),('037','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','feesetting.feereference','费用参考','费用参考',0,0,NULL),('046','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','transferTenderKind ','实际上是标种类型前台查询转让时用','项目类型',0,0,NULL),('047','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','transferPeriodRange','项目剩余期限前台查询转让时使用','剩余期限',0,0,NULL),('048','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','transferRateRange','认购收益率范围前台查询转让时使用','认购收益',0,0,NULL),('049','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','transferAmountRange','转让金额范围前台查询转让时使用','转让金额',0,0,NULL),('050','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','curCode','系统支持的币种目前只支持人民币','币种',0,0,NULL),('051','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','monthIncome','会员的月收入水平','月收入水平',0,0,NULL),('052','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','education','学历列表','学历列表',0,0,NULL),('053','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','industry','行业列表','行业列表',0,0,NULL),('054','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','ecpRelation','联系人关系','联系人关系',0,0,NULL),('055','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','gender','性别','性别',0,0,NULL),('056','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','payBank','支付银行','支付银行',0,1,NULL),('057','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','orderTimeout','订单超时时间（单位：分）','订单超时时间设置',1,0,NULL),('058','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','publicCard','公共卡号','公共卡号',1,0,NULL),('059','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','tradeType','用户交易类型','交易类型',0,0,NULL),('060','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','platformTradeType','平台交易类型','交易费用类型',0,0,NULL),('061','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','feeTypeType','费用分类','费用分类',0,0,NULL),('062','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','recommendNum','首页推荐显示数量','首页推荐显示数量',1,0,NULL),('063','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','smsStatus','短信发送状态','短信发送状态',0,0,NULL),('064','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','businessBranch','设置业务分支点的判断标识','业务分支设置',1,0,NULL),('065','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','transferSetup','平台债权转让相关设置','债权转让设置',1,0,NULL),('066','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',1,'F0A','2016-07-13 15:38:47','integralConfig','积分设置','积分设置',1,1,NULL),('080','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','smstemplate.triggerNode','短信模板节点','短信模板节点',0,0,NULL),('081','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','businessType','业务类型','业务类型',0,0,NULL),('082','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','accountCreateSetup','用于针对账户的实名、开户、绑卡、授权等业务处理逻辑进行设置','账户开立设置',1,0,''),('083','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',1,'F0A','2016-07-13 15:38:47','coupon.kind','选择会员种类','红包类型',0,0,NULL),('084','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','coupon.send','红包发放相关设置','红包发放设置',1,0,NULL),('085','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','plartform.app','APP平台信息','APP平台信息',1,0,NULL),('090','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',1,'F0A','2016-07-13 15:38:47','company.property','企业性质','企业性质',0,0,NULL),('091','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',1,'F0A','2016-07-13 15:38:47','company.size','企业规模','企业规模',0,0,NULL),('092','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',1,'F0A','2016-07-13 15:38:47','work.years','工作年限','工作年限',0,0,NULL),('093','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','maritalStatus','后台选择会员婚姻状态','婚姻状态',0,0,NULL),('094','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',1,'F0A','2016-07-13 15:38:47','plartform.visit.limit','平台会员访问限制','平台会员访问限制',1,0,NULL),('095','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','openaccount.type','浙商开户存管类型','浙商开户存管类型',0,0,NULL),('096','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','openaccount.certType','浙商开户证件类型','浙商开户证件类型',0,0,NULL),('097','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','product.auto.config','产品自动处理配置','产品自动处理配置',0,0,NULL),('098','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','czbank.businessType','浙商交易类型','浙商交易类型',0,0,NULL),('099','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','risk.rank.limit','风险评估等级限投金额','风险评估等级限投金额',1,0,NULL),('1029','system','2016-07-13 15:38:47',0,'2016-07-13 15:38:47','2114-12-31 23:59:59',0,'F0A','2016-07-13 15:38:47','openoffice','openoffice配置项','openoffice配置项',1,0,'openoffice配置项');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-08 16:37:33