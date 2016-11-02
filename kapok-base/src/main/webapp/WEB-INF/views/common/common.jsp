<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page"/>

<c:set var="company_name" value="广东国信物联信息科技有限公司" scope="page"/>
<c:set var="system_name" value="管理平台系统" scope="page"/>
<c:set var="keywords" value="广东国信物联信息科技有限公司,nateiot,物联网,国信物联" scope="page"/>
<c:set var="copyright" value="Copyright © 2016.  " scope="page"/>

<c:set var="baiduKey" value="http://api.map.baidu.com/api?v=2.0&ak=x75aVCwTtN6mHUN3s6kIk51t" scope="page"/>
<c:set var="gutterHeight" value="5" scope="page"/>
<c:set var="rowSpanHeight" value="30" scope="page"/>
<c:set var="comboboxWidth" value="100%" scope="page"/>
<c:set var="comboboxHeight" value="25" scope="page"/>

<c:set var="bootstrap" value="bootstrap-3.3.5" scope="page"/>
<c:set var="fontAwesome" value="font-awesome-4.5.0" scope="page"/>
<c:set var="jqueryEasyui" value="jquery-easyui-1.4.4" scope="page"/>
<c:set var="kindeditor" value="kindeditor-4.1.10" scope="page"/>

<!-- 青海省民和县川口镇智慧社区管理平台 -->

<% response.setHeader("Cache-Control","no-cache");%> 
<% response.setHeader("Pragma","no-cache");%>
<% response.setDateHeader ("Expires",-1); %>