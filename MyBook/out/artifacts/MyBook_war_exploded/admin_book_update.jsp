<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>修改</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

</head>

<body>
<div align="center">
    <form action="BookServlet?op=update" method="post">
        <table width="550px" border="1px">
            <tr>
                <td align="center">图书编号</td>
                <td><input type="text" name="id" id="id" readonly="readonly" value="${a.id}"/></td>
            </tr>

            <tr>
                <td align="center">图书名称</td>
                <td><input type="text" name="name" id="name" value="${a.name}"/></td>
            </tr>

            <tr>
                <td align="center">作者名称</td>
                <td><input type="text" name="author" id="author" value="${a.author}"/></td>
            </tr>

            <tr>
                <td align="center">出版社</td>
                <td><input type="text" name="press" id="press" value="${a.press}"/></td>
            </tr>

            <tr>
                <td align="center">可借阅数量</td>
                <td><input type="text" name="number" id="number" value="${a.number}"/></td>
            </tr>

            <tr>
                <td align="center">操作</td>
                <td><input type="submit" value="保存"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
