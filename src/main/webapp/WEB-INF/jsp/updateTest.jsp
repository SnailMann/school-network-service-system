<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/19
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateTest</title>
</head>
<body>
<center>
    <form action="${pageContext.request.contextPath}/worker/uploadFile.action"  method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="submit" value="上 传" />
    </form>
    <h5>上传结果：</h5>
    <img alt="暂无文件" src="${fileUrl}" />
</center>
</body>
</html>
