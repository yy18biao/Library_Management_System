<%--
  Created by IntelliJ IDEA.
  User: jingb
  Date: 2023/11/28
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="entity.Booksend" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>借阅信息</title>
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
    List<Booksend> list = (List<Booksend>) request.getAttribute("list");
    // 集合为null，意味着没获取，直接跳转到do页面进行获取
    if (list == null) {
        request.getRequestDispatcher("UserBookServlet?op=getAllNoReturnBook").forward(request, response);
        return;
    }
%>
<body>
<h2><img src="static/index.jpeg" alt=""></h2>
<table id="mytable1" width="100%" cellspacing="0px" cellpadding="0px">
    <tr>
        <td align="center"><a href="user_index.jsp">图书信息</a></td>
        <td align="center"><a href="user_send.jsp">图书借阅信息</a></td>
        <td align="center"><a href="user_history.jsp">图书归还信息</a></td>
        <td align="center"><a href="index.jsp">退出登录</a></td>
    </tr>
</table>
<div id="main">
    <form action="UserBookServlet?op=getAllNoReturnBook" method="post">
        输入图书名称查询：
        <input type="text" name="keyword"/>
        <input type="submit" value="查询"/>
        <input type="hidden" name="pageIndex" value="1">
    </form>
    <table id="mytable" width="100%" cellspacing="0px" cellpadding="0px">
        <tr>
            <td align="center">编号</td>
            <td align="center">书号</td>
            <td align="center">借阅用户名称</td>
            <td align='center'>图书名称</td>
            <td align='center'>作者名称</td>
            <td align='center'>出版社</td>
            <td align='center'>借出时间</td>
        </tr>
        <c:forEach items="${list}" var="cd" varStatus="status">
            <tr>
                <td>${cd.id }</td>
                <td>${cd.bid }</td>
                <td>${cd.username }</td>
                <td>${cd.bookname }</td>
                <td>${cd.author}</td>
                <td>${cd.press }</td>
                <td>${cd.data }</td>
                <td>
                    <a href="UserBookServlet?op=return_book&id=${ cd.id }">归还</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
