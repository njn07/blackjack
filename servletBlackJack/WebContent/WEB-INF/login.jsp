<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:casualpage>
<jsp:attribute name="header">Log in</jsp:attribute>
	<jsp:body>
	<div class="register">
		<form action="login" method="post">
			<label>login:</label> <input type="text" name="username" required
				autofocus></input><br> <label>password:</label> <input
				type="password" name="password" required></input><br/>
				<br/> 
				<input class="btn btn-info" type="submit" value="Log in"></input><br/>
		</form>
		${error}
	</div>
	</jsp:body>
</t:casualpage>
