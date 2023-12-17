<%--
  Created by IntelliJ IDEA.
  User: jingb
  Date: 2023/11/28
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户新增</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        body {
            background-size: cover; /*扩展以覆盖窗口*/
        }

        .login {
            width: 350px;
            height: 450px;
            background: white;
            box-shadow: 0px 0px 50px #333;
            /*这里实现元素的居中有多种实现方法*/
            position: absolute; /*绝对定位*/
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin: auto;
            padding: 30px 40px;
            box-sizing: border-box;
        }

        .login h3 { /*后代选择器*/
            font-size: 24px;
            margin-bottom: 30px;
            text-align: center;
        }

        .login form .inputBox { /*后代选择器*/
            position: relative;
            height: 40px;
            display: flex; /*弹性盒子 中心对齐*/
            align-items: center;
            margin-bottom: 20px;
        }

        .login form .inputBox input { /*后代选择器*/
            height: 30px;
            border: 0px;
            border-bottom: 1px solid #ccc;
            flex-grow: 1; /*扩展占据弹性容器剩余空间*/
            margin: 10px;
            padding-left: 5px;
            /*当焦点落到输入框上时，默认会有轮廓标识其状态，为美观将它删除*/
            outline: none;
        }

        .login form .inputBox input:focus { /*后代+伪类*/
            border: 1px solid dodgerblue;
        }

        .login form .inputBox label { /*后代选择器*/
            position: absolute; /*相对于弹性盒子绝对定位*/
            padding: 0 3px;
            left: 40px;
            top: 20px; /*父元素高为20*/
            color: dodgerblue;
            font-size: 14px;
            background: #fff;
            opacity: 0; /*不透明度*/
            transition: 0.5s; /*过渡用时*/
        }

        .login form .inputBox input:focus+label { /*后代+兄弟*/
            opacity: 1; /*不透明度*/
            top: -8px;
        }

        .submitButton {
            display: flex; /*弹性盒子 中心对齐*/
            align-items: center;
        }

        .submitButton input { /*后代选择器*/
            margin: 10% 10%;
            flex-grow: 1;
            cursor: pointer; /*更改光标*/
            height: 40px;
            line-height: 40px;
            background: dodgerblue;
            color: #fff;
            border: none;
            border-radius: 5px; /*按钮圆角*/
            outline: none;
        }

        .submitButton input:hover { /*后代+伪类*/
            box-shadow: 2px 2px 10px #555;
            transition: 0.3s;
        }

        .submitButton input:active { /*后代+伪类*/
            box-shadow: none;
            cursor: wait; /*更改光标*/
            outline: none;
        }
    </style>
</head>
<body>
<div class="login">
    <h3>新增图书</h3>
    <form action="BookServlet?op=bookadd" id="myForm" method="post">
        <div class="inputBox">
            <i class="iconfont icon-wode"></i>
            <input type="text" class="name" name="name" id="name"
                   autocomplete="off" placeholder="请输入图书名称">
            <label for="">图书名称</label>
        </div>
        <div class="inputBox">
            <i class="iconfont icon-mima"></i>
            <input type="text" class="author" id="author" name="author"
                   autocomplete="off" placeholder="请输入作者名称">
            <label for="">作者名称</label>
        </div>
        <div class="inputBox">
            <i class="iconfont icon-mima"></i>
            <input type="press" class="press" id="press" name="press"
                   autocomplete="off" placeholder="请输入出版社">
            <label for="">出版社</label>
        </div>
        <div class="inputBox">
            <i class="iconfont icon-mima"></i>
            <input type="number" class="number" id="number" name="number"
                   autocomplete="off" placeholder="请输入可借阅数量">
            <label for="">可借阅数量</label>
        </div>
        <div id="code"></div>
        <div class="submitButton">
            <input type="button" onclick="submitForm();" value="提交" />
        </div>
    </form>
</div>
</body>
</html>
<script type="text/javascript">
    function submitForm() {
        var name = document.getElementById("name").value;
        var author = document.getElementById("author").value;
        var press = document.getElementById("press").value;
        if (name == "" || press == "" || author == "") {
            alert("不能有某一栏为空");
            return false;
        }
        document.getElementById("myForm").submit();
    }
</script>
