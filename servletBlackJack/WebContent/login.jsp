<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:casualpage>
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
</t:casualpage>
