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
<form action="UserServlet?op=update" method="post">
    <table width="550px" border="1px">
        <tr>
            <td align="center">用户编号</td>
            <td><input type="text" name="id" id="id" readonly="readonly" value="${a.id}"/></td>
        </tr>

        <tr>
            <td align="center">用户名</td>
            <td><input type="text" name="name" id="name" value="${a.username}"/></td>
        </tr>

        <tr>
            <td align="center">用户密码</td>
            <td><input type="text" name="password" id="password" value="${a.password}"/></td>
        </tr>


        <tr>
            <td align="center">电话号码</td>
            <td><input type="text" name="phone" id="phone" value="${a.phone}"/></td>
        </tr>

        <tr>
            <td align="center">电子邮件</td>
            <td><input type="text" name="email" id="email" value="${a.email}"/></td>
        </tr>

        <tr>
            <td align="center">是否修改为管理员(1:否，0:是)</td>
            <td><input type="text" name="status" id="status" value="${a.status}"/></td>
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
