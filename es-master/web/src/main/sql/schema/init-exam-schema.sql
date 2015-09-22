-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2015-05-16 21:52:59
-- 服务器版本: 5.5.43-0ubuntu0.14.04.1
-- PHP 版本: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- 数据库: `es`
--

-- --------------------------------------------------------

--
-- 表的结构 `exam_answer`
--

CREATE TABLE IF NOT EXISTS `exam_answer` (
  `id` bigint NOT NULL auto_increment,
  `serial_no` varchar(10) NOT NULL COMMENT '答案序列',
  `content` varchar(2000) NOT NULL COMMENT '答案内容',
  `subject_id` int(11) NOT NULL COMMENT '对应问题',
  `flag` tinyint(1) NOT NULL COMMENT '答案正确与否',
  `remark` varchar(2000) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `exam_subject`
--

CREATE TABLE IF NOT EXISTS `exam_subject` (
  `id` bigint NOT NULL auto_increment,
  `content` varchar(2000) NOT NULL COMMENT '题目详细内容',
  `type` varchar(20) NOT NULL COMMENT '题目类型：1-多选，2-单选，3-判断，4-简答',
  `category` varchar(20) NOT NULL COMMENT '题目类别：1-公共基础，2-通用，3-应用开发',
  `remark` varchar(2000) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
