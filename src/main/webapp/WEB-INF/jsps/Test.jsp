<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form  action="/submit"  method="post" enctype="multipart/form-data">
  <div>
    <label for="file">Choose file to upload</label>
    <input type="file" id="file" name="file" multiple />
    <input type="text" id="id" name="id" value="1"/>
    <input type ="text" id="doc_name" name="doc_name" value="test">
  </div>
  <div>
    <button>Submit</button>
  </div>
</form>
</body>
</html>