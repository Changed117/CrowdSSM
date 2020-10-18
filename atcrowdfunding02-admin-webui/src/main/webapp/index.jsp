<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="jQuery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
            $(function () {
                $("#btn3").click(function () {

                    var array = [5,8,10];

                    var requestBody = JSON.stringify(array);

                    $.ajax({
                        "url": "send/array/three.html",
                        "type": "post",
                        "data": requestBody,
                        "contentType": "application/json;charset=UTF-8",
                        "dataType": "text",
                        "success": function (response) {
                            alert(response);
                        },
                        "error":function (response) {
                            alert(response);
                        }
                    });
                });

                $("#btn2").click(function () {
                    $.ajax({
                        "url": "send/array/two.html",
                        "type": "post",
                        "data": {
                            "array[0]": 5,
                            "array[1]": 8,
                            "array[2]": 10
                        },
                        "dataType": "text",
                        "success": function (response) {
                            alert(response);
                        },
                        "error":function (response) {
                            alert(response);
                        }
                    });
                });

                $("#btn1").click(function () {
                    $.ajax({
                        "url": "send/array/one.html",
                        "type": "post",
                        "data": {
                            "array": [5,8,10]
                        },
                        "dataType": "text",
                        "success": function (response) {
                            alert(response);
                        },
                        "error":function (response) {
                            alert(response);
                        }
                    });
                });
            });
    </script>
</head>
<body>
    <a href="test/ssm.html">测试ssm整合环境</a>

    <br/>
    <br/>

    <button id="btn1">Send [5,8,10] One</button>

    <br/>
    <br/>

    <button id="btn2">Send [5,8,10] Two</button>

    <br/>
    <br/>

    <button id="btn3">Send [5,8,10] Three</button>

</body>
</html>
