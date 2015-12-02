<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@attribute name="header" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title><jsp:invoke fragment="header"></jsp:invoke> - BlackJack</title>
<link rel="stylesheet" type="text/css" href="styles/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="_css/styles.css">
</head>
<body>
	<div id="content">
		<div id="container">
			<row id="part">
			<div id="pageheader" class="col-md-12">
				<div class="col-md-8">
				</div>
				<div class="col-md-4 text-right">
					<c:choose>
						<c:when test="${not empty sessionScope.user }">
							<h2>You are ${sessionScope.user.getLogin() }</h2>
							<form action="logout" method="post">
								<input type="submit" value="Log out" />
							</form>
						</c:when>
						<c:otherwise>
							<form action="login.jsp" method="post">
								<input type="submit" value="Log in" />
							</form>
							<form action="registration.jsp" method="post">
								<input type="submit" value="Register" />
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<row>
			<div id="menu" class="col-md-12 ">
				<a href="game.jsp" class="btn btn-info" role="button">Play Game</a>
				<c:if test="${not empty sessionScope.user }">
				<a href="user" class="btn btn-info" role="button">My Profile</a>
				</c:if>
				<a href="complain" class="btn btn-info" role="button">Contact us</a>
			</div>
			</row> </row>
			<row>
			<div id="body" class="col-md-12" style="background-color: DCDCDC;">
				<jsp:doBody />
			</div>
			</row>
		</div>
	</div>
</body>
</html>