<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>BlackJack</title>
<meta charset="utf-8" />
<script type="text/javascript" src="_js/underscore-min.js"></script>
<script type="text/javascript" src="_js/validate.js"></script>
<link rel="stylesheet" type="text/css" href="_css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="_css/styles.css">
</head>
<body>
	<div class="register">
		<form action="login" method="post">
			<label>login:</label> <input type="text" name="username" required
				autofocus></input><br> <label>password:</label> <input
				type="password" name="password" required></input><br> <input
				class="logButton" type="submit" value="Log in"></input><br>
			<form action="game.jsp" method="get">
				<input class="logButton" type="submit" value="Back to game"></input><br>
			</form>
		</form>
		${error}
	</div>

</body>
</html>
