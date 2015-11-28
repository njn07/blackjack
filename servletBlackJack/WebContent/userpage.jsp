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
	<div class="register">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<br /> your login: ${sessionScope.user.getLogin()} <br /> your
		money: ${sessionScope.user.getBalance()} <br /> your email:
		${sessionScope.user.getEmail()} <br /> games played:
		${sessionScope.user.getGames_played()} <br /> games won:
		${sessionScope.user.getGames_won()} <br /> games lost:
		${sessionScope.user.getGames_lost()} <br />
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
	</div>
</body>
</html>
