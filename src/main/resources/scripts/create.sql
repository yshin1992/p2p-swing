/** 系统管理员信息表 **/
create table sys_user(
uuid char(32) primary key,
createTime datetime not null,
updateTime datetime not null,
status int default 0,
userCd varchar(32) unique,
password char(32),
email varchar(128),
lastLoginIp varchar(32),
lastLoginTime datetime,
loginCounts int default 0,
isAdmin int default 0) engine=InnoDB default charset=utf8;

/** 会员信息表 **/
CREATE TABLE `member` (
  `uuid` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `auditType` int(11) DEFAULT NULL,
  `bankNo` varchar(20) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `idCard` varchar(18) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `lastLoginIp` varchar(32) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `loginCount` int(11) DEFAULT NULL,
  `memberIdZ` varchar(32) DEFAULT NULL,
  `memberKind` int(11) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  `promotionId` varchar(12) DEFAULT NULL,
  `realCd` varchar(32) DEFAULT NULL,
  `realNm` varchar(64) DEFAULT NULL,
  `registTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `nickName` varchar(64) DEFAULT NULL,
  `activity` varchar(255) DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 会员积分表 **/
CREATE TABLE `memberintegral` (
  `uuid` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memberId` varchar(32) NOT NULL,
  `integralVal` int(11) DEFAULT NULL,
  `maxInvestAmount` decimal(19,4) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `usedAmount` decimal(19,4) DEFAULT NULL,
  `usedValue` int(11) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 会员信息汇总表 **/
CREATE TABLE `user_summary` (
  `uuid` varchar(32) NOT NULL,
  `memberId` varchar(32) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `amountAward` decimal(19,2) DEFAULT NULL,
  `amountAwardInvest` decimal(19,2) DEFAULT NULL,
  `amountAwardRow` int(11) DEFAULT NULL,
  `amountCash` decimal(19,2) DEFAULT NULL,
  `amountCashRow` int(11) DEFAULT NULL,
  `amountDueRepayAll` decimal(19,2) DEFAULT NULL,
  `amountDueRepayCapital` decimal(19,2) DEFAULT NULL,
  `amountDueRepayInterest` decimal(19,2) DEFAULT NULL,
  `amountDueRepayRow` int(11) DEFAULT NULL,
  `amountDueRepayedAll` decimal(19,2) DEFAULT NULL,
  `amountDueRepayedCapital` decimal(19,2) DEFAULT NULL,
  `amountDueRepayedInterest` decimal(19,2) DEFAULT NULL,
  `amountDueRepayedRow` int(11) DEFAULT NULL,
  `amountDueinAll` decimal(19,2) DEFAULT NULL,
  `amountDueinCapital` decimal(19,2) DEFAULT NULL,
  `amountDueinInterest` decimal(19,2) DEFAULT NULL,
  `amountDueinRow` int(11) DEFAULT NULL,
  `amountInedAll` decimal(19,2) DEFAULT NULL,
  `amountInedCapital` decimal(19,2) DEFAULT NULL,
  `amountInedInterest` decimal(19,2) DEFAULT NULL,
  `amountInedRow` int(11) DEFAULT NULL,
  `amountInterestFee` decimal(19,2) DEFAULT NULL,
  `amountInvest` decimal(19,2) DEFAULT NULL,
  `amountInvestRow` int(11) DEFAULT NULL,
  `amountInvit` decimal(19,2) DEFAULT NULL,
  `amountRecharge` decimal(19,2) DEFAULT NULL,
  `amountRechargeRow` int(11) DEFAULT NULL,
  `investIntegral` decimal(19,2) DEFAULT NULL,
  `status` int(11),
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 浙商开户信息表 **/
create table zsaccount(
`uuid` char(32) primary key,
`createTime` datetime not null,
`updateTime` datetime not null,
`status` int default 0,
`memberId` char(32) not null,
`bindSerialNo` varchar(30) DEFAULT NULL,
`branchNo` varchar(255) DEFAULT NULL,
`certNo` varchar(255) DEFAULT NULL,
`certType` varchar(255) DEFAULT NULL,
`ecardNo` varchar(20) DEFAULT NULL,
`extension` varchar(255) DEFAULT NULL,
`failReason` varchar(255) DEFAULT NULL,
`openBankCard` varchar(255) DEFAULT NULL,
`openTel` varchar(13) DEFAULT NULL,
`openTime` datetime DEFAULT NULL,
`otherAccno` varchar(255) DEFAULT NULL,
`platformUid` varchar(255) DEFAULT NULL,
`type` varchar(2) DEFAULT NULL,
`unionNumber` varchar(255) DEFAULT NULL,
`updateStatus` int(11) DEFAULT NULL,
`businessProperty` int(11) DEFAULT NULL,
`bankName` varchar(255) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sysoperationlog` (
  `logId` varchar(32) NOT NULL,
  `createBy` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `operateContent` varchar(200) DEFAULT NULL,
  `operateObj` varchar(32) DEFAULT NULL,
  `operateObjName` varchar(200) DEFAULT NULL,
  `operateObjType` varchar(32) DEFAULT NULL,
  `operateTime` datetime DEFAULT NULL,
  `operateType` int(11) DEFAULT NULL,
  `operatorAccount` varchar(32) DEFAULT NULL,
  `operatorId` varchar(32) DEFAULT NULL,
  `operatorType` varchar(255) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `integralrecord` (
  `recordId` varchar(32) NOT NULL,
  `createBy` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `amount` decimal(19,4) DEFAULT NULL,
  `failureTime` datetime DEFAULT NULL,
  `integralVal` int(11) DEFAULT NULL,
  `isAddFlag` int(11) DEFAULT NULL,
  `isPermanent` int(11) DEFAULT NULL,
  `objId` varchar(32) DEFAULT NULL,
  `objType` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `memberId` varchar(32) NOT NULL,
  PRIMARY KEY (`recordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 合同模板 **/
CREATE TABLE `contract_template` (
  `templateId` varchar(32) NOT NULL,
  `createBy` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `templateName` varchar(255) DEFAULT NULL,
  `templatePath` varchar(255) DEFAULT NULL,
  `templatePdfPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`templateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table product(
uuid char(32) primary key,
createTime datetime,
updateTime datetime,
status int default 0,
productCd varchar(32) not null,
productNm varchar(128) not null,
rate decimal(5,2) default 0,
period int,
periodType int,
unitPrice decimal(10,2),
quantity long,
castQuantity long,
minTenderQuantity long,
maxTenderQuantity long,
minFullQuantity long,
groundTime datetime,
tenderStart datetime,
tenderEnd datetime,
contractEffTime datetime,
fullScaleTime datetime,
interestStartTime datetime,
borrowUse int,
borrowUseName varchar(128),
repayMethod int,
tenderKind int,
tenderKindName varchar(128),
businessType int,
businessTypeNm varchar(128),
memberId char(32),
productContent nvarchar(1024),
repaySource nvarchar(1024),
fundUse nvarchar(1024),
minHoldDay int,
canAutobid int,
awardRate decimal(5,2),
awardAmount decimal(10,2),
awardTotal decimal(10,2),
awardFail int,
amount decimal(10,2),
compensatory int default 0,
compenInterest decimal(5,2),
interestAlgorithm int)engine=InnoDB DEFAULT charset=utf8;

CREATE TABLE `itemtype` (
  `itemTypeId` varchar(32) NOT NULL,
  `createBy` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `biller` int(11) DEFAULT NULL,
  `calOnlineFlag` int(11) DEFAULT NULL,
  `charger` int(11) DEFAULT NULL,
  `edited` int(11) DEFAULT NULL,
  `feeType` varchar(3) DEFAULT NULL,
  `itemTypeCd` varchar(6) DEFAULT NULL,
  `itemTypeNm` varchar(64) DEFAULT NULL,
  `itemTypePCd` varchar(6) DEFAULT NULL,
  `maxAmount` decimal(19,4) DEFAULT NULL,
  `minAmount` decimal(19,4) DEFAULT NULL,
  `node` int(11) DEFAULT NULL,
  `periodOrDay` int(11) DEFAULT NULL,
  `rate` decimal(19,4) DEFAULT NULL,
  `rateReferened` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
