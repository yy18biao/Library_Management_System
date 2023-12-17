<%--
  Created by IntelliJ IDEA.
  User: jingb
  Date: 2023/11/28
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        .center {
            position: absolute;
            width: 350px;
            height: 120px;
            left: 45%;
            top: 50%;
            margin: -100px 0px 0px -100px;
        }
    </style>
</head>
<body onload="reloadCode()" style="background-image: url(static/login.jpeg); background-repeat: no-repeat; background-size: 100% 100%">
<div class="center">
    <center>
        <form action="UserServlet?op=loginUser" method="post" id="form"
              onsubmit='return checkForm()'>
            <h1>欢迎登录图书管理系统</h1>
            账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：
            <input name="username" id="username" type="text" style="width: 150px">
            <br>
            密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：
            <input name="password" id="password" type="password" style="width: 150px">
            <br>
            验&nbsp;&nbsp;证&nbsp;&nbsp;码:
            <input name="checkCode" id="checkCode" type="checkCode" style="width: 150px">
            <br>
            <img alt="验证码" id="imagecode" src="<%= request.getContextPath()%>./servlet/ImageServlet"/>
            <a href="javascript:reloadCode();">看不清楚</a>
            <br>
            <input type="submit" value="登录">
            <br>
            <a href="register.jsp">注册</a>
        </form>

    </center>
</div>
</body>
</html>
<script>
    function checkForm() {
        var form = document.getElementById('form');
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var checkCode = document.getElementById("checkCode").value;
        if(checkCode == ""){
            alert("验证码不能为空！！！");
            return false;
        }
        if (username != "" && password != "") {
            form.submit();
            return false;
        } else {
            alert("用户名密码不能为空！！！");
            return false;
        }
        return false;
    }
</script>
<script type="text/javascript">
    function reloadCode()
    {
        var time=new Date().getTime();
        document.getElementById("imagecode").src="<%= request.getContextPath()%>/servlet/ImageServlet?d="+time;
    }
</script>
