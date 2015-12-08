<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="_js/profile.js"></script>
<link rel="stylesheet" type="text/css" href="_css/styles.css">
</head>
<body>
	<aside>
	 <c:choose>
		<c:when test="${not empty sessionScope.user}">
			<p id="red">nickname:</p>
			<p>${sessionScope.user.getLogin()}</p>
			<p id="yellow">email:</p>
			<p>${sessionScope.user.getEmail()}</p>
			<p id="indigo">balance:</p>
			<p>${sessionScope.user.getBalance()}</p>
			<p id="black">games played:</p>
			<p>${sessionScope.user.getGames_played()}</p>
			<p id="greenyellow">games won:</p>
			<p>${sessionScope.user.getGames_won()}</p>
			<p id="greenyellow">games lost:</p>
			<p>${sessionScope.user.getGames_lost()}</p>
			<form action="game.jsp" method="get">
				<input type="submit" value="Play BlackJack!" />
			</form>
			<form action="login" method="post">
				<input type="hidden" name="Logout" value="Logout" /> <input
					type="submit" value="Log out" />
			</form>
		</c:when>
		<c:otherwise>
			<p>Please, login or register to view your profile</p>
			<a href="">Back to game</a>
		</c:otherwise>
	</c:choose>
	 </aside>
</body>
</html>
