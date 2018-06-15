<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>this is error jsp</title>
</head>
<body>
<h1>Error Handler</h1>
    <div>${url}</div>
    <div>${exception.message}</div>
    <div th:text="${uri}"></div>  
	<div th:text="${e.toString()}"></div>     
</body>
</html>