<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<h1>succ1</h1>
<%
    String username = (String)session.getAttribute("username");
    if(username == null) {
    /*
    1. 向request域中保存错误信息，转发到login.jsp
    */
        request.setAttribute("msg", "您还没有登录!请先登录!");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
        return;
    }
%>

欢迎欢迎，热烈欢迎，欢迎<%=username %>领导指导工作！
</body>
</html>