/** 系统管理员信息表 **/
create table sys_user(
uuid char(32) primary key,
createTime timestamp not null,
updateTime timestamp not null,
status int default 0,
userCd varchar(32) unique,
password char(32),
email varchar(128),
lastLoginIp varchar(32),
lastLoginTime timestamp,
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
`createTime` timestamp not null,
`updateTime` timestamp not null,
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

