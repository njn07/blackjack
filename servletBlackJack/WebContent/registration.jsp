<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:casualpage>
	<div class="register">
		<form action="register" method="post">
			<label>nickname:</label> <input type="text" required autofocus
				name="nickname"></input><br> <label>email:</label> <input
				type="email" required autofocus name="email"></input><br> <label>password:</label>
			<input type="password" required name="password"></input><br> <label>confirm
				password:</label> <input type="password" required onblur="valid_password()"></input><br>
			<input class="regButton" type="submit" value="register!"></input><br>
		</form>
	</div>
</t:casualpage>