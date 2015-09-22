#如果复制到mysql中执行时 加上
#DELIMITER ;;

drop table if exists `stock_company_detail`;;
-- --------------------------------------------------------

--
-- 表的结构 `stock_company_detail`
--

CREATE TABLE IF NOT EXISTS `stock_company_detail` (
  `id` bigint not null auto_increment,
  `stock_code` varchar(10) NOT NULL COMMENT '股票代码',
  `ssrq` varchar(10) NOT NULL COMMENT '上市日期',
  `zyyw` varchar(100) NOT NULL COMMENT '主营业务',
  `ywxx` varchar(500) NOT NULL COMMENT '业务详细',
  `zgb` varchar(20) NOT NULL COMMENT '总股本',
  `ltg` varchar(20) NOT NULL COMMENT '流通股',
  `yysrzzl` varchar(20) NOT NULL COMMENT '营业收入增长率',
  `yysr` varchar(20) NOT NULL COMMENT '营业收入',
  `jlr` varchar(20) NOT NULL COMMENT '净利润',
  `jlrzzl` varchar(20) NOT NULL COMMENT '净利润增长率',
  `mgsy` varchar(20) NOT NULL COMMENT '每股收益',
  `mgjzc` varchar(20) NOT NULL COMMENT '每股净资产',
  `jzcsyl` varchar(20) NOT NULL COMMENT '净资产收益率',
  `mgxjl` varchar(20) NOT NULL COMMENT '每股现金流',
  `mggjj` varchar(20) NOT NULL COMMENT '每股公积金',
  `mgwfplr` varchar(20) NOT NULL COMMENT '每股未分配利润',
  `gsmc` varchar(200) NOT NULL COMMENT '公司名称',
  `ssdy` varchar(100) NOT NULL COMMENT '所属地域',
  `sjgn` varchar(200) NOT NULL COMMENT '涉及概念',
  `sshy` varchar(200) NOT NULL COMMENT '所属行业',
  `data_date` varchar(8) NOT NULL COMMENT '数据日期',
  constraint `pk_stock_company_detail` primary key(`id`),
  constraint `unique_stock_company_detail` unique(`stock_code`,`data_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司详细信息';