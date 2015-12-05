<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BlackJack</title>
<meta charset="utf-8" />
<script src="sweetalert-master/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="sweetalert-master/dist/sweetalert.css">	
<link rel="stylesheet" href="styles/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="styles/styles.css" />
<script src="_js/lib/jquery-2.1.1.js"></script>
<script src="_js/lib/jquery.flip.js"></script>
<script src="_js/lib/bootbox.min.js"></script>
<script src="_js/blackJack.js"></script>
<script src="_js/constants.js"></script>
<script src="_js/view.js"></script>
<link rel="stylesheet" href="_js/lib/jquery-ui-1.11.4/jquery-ui.css" />
<script src="_js/lib/jquery-ui-1.11.4/jquery-ui.js"></script>

<script src="_js/slider.js"></script>
<meta name="viewport" content="target-densitydpi=device-dpi" />

</head>
<body>



	<div class="row" id="first">
	<div class="col-xs-4" class="deck">
		<img src="img/cards/background.png" class="card"  />
	</div>




	<div class="col-xs-4">

		<div>
			
			<!--полученные карты диллера-->
			<div style="position: relative;display:inline-block;">
			<div id="dealerSum" style="position:absolute;margin-left:-150px;"></div>
				<img src="img/cards/background.png" id="dealerCard0" class="card"
					style="position: absolute;" hidden="hidden" />
				<!--15-->
				<img src="img/cards/background.png" id="dealerCard1" class="card"
					style="position: absolute; margin-left: 25px" hidden="hidden" />
				<!--1-->
				<img src="img/cards/background.png" id="dealerCard2" class="card"
					style="position: absolute; margin-left: 50px;" hidden="hidden" />
				<!--2-->
				<img src="img/cards/background.png" id="dealerCard3" class="card"
					style="position: absolute; margin-left: 75px;" hidden="hidden" />
				<!--3-->
				<img src="img/cards/background.png" id="dealerCard4" class="card"
					style="position: absolute; margin-left: 100px;" hidden="hidden" />
				<!--4-->
				<img src="img/cards/background.png" id="dealerCard5" class="card"
					style="position: absolute; margin-left: 125px;" hidden="hidden" />
				<!--5-->
				<img src="img/cards/background.png" id="dealerCard6" class="card"
					style="position: absolute; margin-left: 150px;" hidden="hidden" />
				<!--6-->
				<img src="img/cards/background.png" id="dealerCard7" class="card"
					style="position: absolute; margin-left: 175px;" hidden="hidden" />
				<!--7-->
				<img src="img/cards/background.png" id="dealerCard8" class="card"
					style="position: absolute; margin-left: 200px;" hidden="hidden" />
				<!--8-->
				<img src="img/cards/background.png" id="dealerCard9" class="card"
					style="position: absolute; margin-left: 225px;" hidden="hidden" />
				<!--9-->
				<img src="img/cards/background.png" id="dealerCard10" class="card"
					style="position: absolute; margin-left: 250px;" hidden="hidden" />
				<!--10-->
				<img src="img/cards/background.png" id="dealerCard11" class="card"
					style="position: absolute; margin-left: 275px;" hidden="hidden" />
				<!--11-->
				<img src="img/cards/background.png" id="dealerCard12" class="card"
					style="position: absolute; margin-left: 300px;" hidden="hidden" />
				<!--12-->
				<img src="img/cards/background.png" id="dealerCard13" class="card"
					style="position: absolute; margin-left: 325px;" hidden="hidden" />
				<!--13-->
				<img src="img/cards/background.png" id="dealerCard14" class="card"
					style="position: absolute; margin-left: 350px;" hidden="hidden" />
				<!--14-->

			</div>
			<!--тут будут очки диллера-->

		</div>
		</div>
		<div id="userInfo" class="col-xs-4">
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<label>Welcome, ${sessionScope.user.getLogin()}!<br /> Good
						luck and have fun!<br /></label><br />
					<button class="logButton" onClick="redirectToProfile()">My
						Profile</button><br />
					<button class="logButton" onClick="logout()">Log out</button>
					<br />
				</c:when>
				<c:otherwise>
					<p>
						Username <br /> <input type="text" style="color: orange;" name="username" id="loginName"/>
					</p>
					<p>
						Password <br /> <input type="password" style="color: orange;" name="password" id="loginPassword"/>
					</p>
					<button class="logButton" onClick="redirectToLogin()">LOG
						IN</button>
					<button class="logButton" onClick="redirectToRegister()">Register</button>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
		<div class="row" id="second">
		<div class="col-xs-4"><div id="infotext"></div></div>
		<div class="col-xs-4">
		<div id="start" style="display: true">
			
			<button class="sbutton" id="startButton">START</button>
			</div>
			<div style="position: relative;display:inline-block;" >
				<div id="playerSum" style="position:absolute;margin-left:-150px;"></div>
				<!--тут будут очки игрока-->


				
				<img src="img/cards/background.png" id="playerCard0" class="card"
					style="position: absolute;" hidden="hidden" />
				<!--15-->
				<img src="img/cards/background.png" id="playerCard1" class="card"
					style="position: absolute; margin-left: 25px;" hidden="hidden" />
				<!--1-->
				<img src="img/cards/background.png" id="playerCard2" class="card"
					style="position: absolute; margin-left: 50px;" hidden="hidden" />
				<!--2-->
				<img src="img/cards/background.png" id="playerCard3" class="card"
					style="position: absolute; margin-left: 75px;" hidden="hidden" />
				<!--3-->
				<img src="img/cards/background.png" id="playerCard4" class="card"
					style="position: absolute; margin-left: 100px;" hidden="hidden" />
				<!--4-->
				<img src="img/cards/background.png" id="playerCard5" class="card"
					style="position: absolute; margin-left: 125px;" hidden="hidden" />
				<!--5-->
				<img src="img/cards/background.png" id="playerCard6" class="card"
					style="position: absolute; margin-left: 150px;" hidden="hidden" />
				<!--6-->
				<img src="img/cards/background.png" id="playerCard7" class="card"
					style="position: absolute; margin-left: 175px;" hidden="hidden" />
				<!--7-->
				<img src="img/cards/background.png" id="playerCard8" class="card"
					style="position: absolute; margin-left: 200px;" hidden="hidden" />
				<!--8-->
				<img src="img/cards/background.png" id="playerCard9" class="card"
					style="position: absolute; margin-left: 225px;" hidden="hidden" />
				<!--9-->
				<img src="img/cards/background.png" id="playerCard10" class="card"
					style="position: absolute; margin-left: 250px;" hidden="hidden" />
				<!--10-->
				<img src="img/cards/background.png" id="playerCard11" class="card"
					style="position: absolute; margin-left: 275px;" hidden="hidden" />
				<!--11-->
				<img src="img/cards/background.png" id="playerCard12" class="card"
					style="position: absolute; margin-left: 300px;" hidden="hidden" />
				<!--12-->
				<img src="img/cards/background.png" id="playerCard13" class="card"
					style="position: absolute; margin-left: 325px;" hidden="hidden" />
				<!--13-->
				<img src="img/cards/background.png" id="playerCard14" class="card"
					style="position: absolute; margin-left: 350px;" hidden="hidden" />
				<!--14-->
		</div>
		</div>
		<div class="col-xs-4">
			<p id="bid" class="bigtext">
				Bid: <br />&nbsp;
			</p>
			<!--ставка-->

		</div>
		</div>

<div  class="row">
		<div class="col-xs-4"></div>
		<div class="col-xs-4">
		
			
			<div id="panel" hidden="true">

			<button class="button" id="hit">HIT</button>
			<button class="button" id="stand">STAND</button>
			</div>
			<div id="betbar" hidden="true">
			<p>
				<label for="amount">Your bid:</label> <input type="number"
					id="amount"
					style="border: 0; color: #f6931f; font-weight: bold; text-align: center"
					maxlength="12">
			</p>
			<div id="slider"></div>
			<div style="padding-top: 20px">
				<button class="sendbutton" id="sendBet">Make bet</button>
			</div>
			
		</div>

		</div>
		<div class="col-xs-4" >
		<div >
			<p id="balance" class="bigtext">
				Balance: <br />
			</p>
		</div>
		<c:if test="${not empty sessionScope.user }">
			<button class="refillButton">REFILL</button>
		</c:if>
		</div>
		</div>
		



</body>
</html>