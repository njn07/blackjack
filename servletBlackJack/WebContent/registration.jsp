<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>BlackJack</title>
<meta charset="utf-8"/>
<script type="text/javascript" src="_js/underscore-min.js"></script>
<script type="text/javascript" src="_js/validate.js"></script>
<link rel="stylesheet" type="text/css" href="_css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="_css/styles.css">
</head>
<body>
<div class="register">
<form action="register" method="post">
	<label>nickname:</label> 
	<input type = "text" required autofocus name="nickname"></input><br>
	<label>email:</label> 
	<input type = "email" required autofocus name="email"></input><br>
	<label>password:</label>
	<input type = "password" required name="password"></input><br>
	<label>confirm password:</label>
	<input type = "password" required onblur="valid_password()"></input><br>
	<input class="regButton" type = "submit" value = "register!"></input><br>
</form>
</div>
</body>
</html>