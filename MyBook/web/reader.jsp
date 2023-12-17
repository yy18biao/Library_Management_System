<%@ page import="entity.Book" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>读者管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        table {
            border-collapse: collapse;
            text-align: center;
        }

        /*合并间距*/
        table, table th, table td {
            border: 1px solid #ddd
        }

        /*表格带线框,6像素的间距*/
        table th, table td {
            padding: 6px
        }

        /*单元格6像素的间距*/
        table th {
            background-color: #eee
        }

        /*表头带背景色*/
    </style>
</head>
<%
    // 从请求里获取数据集合
    List<User> list = (List<User>) request.getAttribute("list");
    // 集合为null，意味着没获取，直接跳转到do页面进行获取
    if (list == null) {
        request.getRequestDispatcher("UserServlet?op=getAll").forward(request, response);
        return;
    }
%>
<body>
<h2><img src="./static/index.jpeg" alt=""></h2>
<table id="mytable1" width="100%" cellspacing="0px" cellpadding="0px">
    <tr>
        <td align="center"><a href="admin_index.jsp">图书信息</a></td>
        <td align="center"><a href="admin_send.jsp">图书借阅信息</a></td>
        <td align="center"><a href="admin_history.jsp">图书归还信息</a></td>
        <td align="center"><a href="reader.jsp">读者管理</a></td>
        <td align="center"><a href="index.jsp">退出登录</a></td>
    </tr>
</table>
<div id="main">
    <form action="UserServlet?op=getAll" method="post">
        输入用户名查询：
        <input type="text" name="keyword"/>
        <input type="submit" value="查询"/>
        <input type="hidden" name="pageIndex" value="1">
    </form>
    <div><a href="user_add.jsp"> 添加用户 </a></div>
    <table id="mytable" width="100%" cellspacing="0px" cellpadding="0px">
        <tr>
            <td align="center">编号</td>
            <td align="center">用户名</td>
            <td align="center">用户密码</td>
            <td align='center'>电话号码</td>
            <td align='center'>电子邮箱</td>
        </tr>
        <c:forEach items="${list}" var="cd" varStatus="status">
            <tr>
                <td>${cd.id}</td>
                <td>${cd.username }</td>
                <td>${cd.password }</td>
                <td>${cd.phone }</td>
                <td>${cd.email}</td>
                <td>
                    <a href="UserServlet?op=toupdate&id=${ cd.id }">编辑</a>
                    <a href="javascript:" onclick="del(${ cd.id })">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
<script>
    function del(id){
        if(confirm("是否删除？")){
            location.href="UserServlet?op=delete&id="+id;
        }
    }
</script>
