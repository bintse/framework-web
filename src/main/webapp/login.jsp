<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/jquery.mobile-1.4.5.min.css">
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>
<div data-role="page">
  <div data-role="content">
    <form method="post" action="demoform.asp">
      <div data-role="fieldcontain">
        <label for="switch">切换开关：</label>
        <select name="switch" id="switch" data-role="slider">
          <option value="on">On</option>
          <option value="off">Off</option>
        </select>
      </div>
      <input type="submit" data-inline="true" value="提交">
    </form>
  </div>
</div>
</body>
</html>