<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:casualpage>
	<jsp:attribute name="header">User page</jsp:attribute>
	<jsp:body>
	<div class="register">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<br /> your login: ${sessionScope.user.getLogin()} <br />
				your money: ${sessionScope.user.getBalance()} <br />
		 your email: ${sessionScope.user.getEmail()} <br /> 
		<table style="width: 100%" class="table">
			<tr>
				<td>games played:</td>
				<td>games won:</td>
				<td>games lost:</td>
			</tr>
			<tr>
				<td>${sessionScope.user.getGames_played()}</td>
				<td>${sessionScope.user.getGames_won()}</td>
				<td>${sessionScope.user.getGames_lost()}</td>
			</tr>
			</table>
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
	</jsp:body>
</t:casualpage>
