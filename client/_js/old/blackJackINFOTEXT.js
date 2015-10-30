$(function() {
	$("#panel").flip({
		axis : 'x',
		trigger : 'manual',
		reverse : true
	});
	$(".sbutton").click(function() {
		$("#panel").flip('toggle');
		$(this).toggle('slow');
		setInfoText("Choose hit or stand");
	});
	$("#hit").click(function() {
		if(!buttonBlock){
		setInfoText("You chose to hit");
		$.getJSON(restAdress, hitParams, viewResponse);
		}
	});
	$("#stand").click(function() {
		if(!buttonBlock){
		setInfoText("You chose to stood");
		$.getJSON(restAdress, standParams, viewStandResponse);
		}
	});
});
var cardQuantity = parseInt("0");
var dealerCardQuantity = parseInt("0");
var buttonBlock=false;
function setPlayerScore(score){
if(score!=-1)
document.getElementById('playerSum').innerHTML = "Player score:<br />"+(score).toString();
else 
document.getElementById('playerSum').innerHTML = "";
}
function setDealerScore(score){
if(score!=-1)
document.getElementById('dealerSum').innerHTML = "Dealer score:<br />"+(score).toString();
else
document.getElementById('dealerSum').innerHTML = "";
}
function setInfoText(text){
document.getElementById('infotext').innerHTML = text;
}
function setCardImage(cardOwner, cardName, index) {
	$("#" + cardOwner + index).toggle('slow');
	document.getElementById(cardOwner + index).src = "img/cards_png/" + cardName + ".png";
}
function viewStandResponse(data) {
	buttonBlock=true;
	var dC;
	dC = data["dealerCards"];
	console.log("dealer cards are: " + dC);
	var partsOfStr = dC.split(';');
	partsOfStr.pop();
	for (i = 0; i < partsOfStr.length; i++) {
		setCardImage("dealerCard", partsOfStr[i], i);
		dealerCardQuantity++;
	}
	setDealerScore(data["dealerSum"]);
	endGame(data);
}
function endGame(data){
	setTimeout(function() {
		processGameOver(data)
	}, 2000);
}
function processGameOver(data) {
	setInfoText("game over: "+data["gameStatus"]);
	console.log("================GAME OVER============="+data["gameStatus"]);
	alert("GAME IS OVER : " + data["gameStatus"]);
	for (i = 0; i < cardQuantity; i++) {
		$("#playerCard" + i.toString()).hide('slow');
	}
	cardQuantity = parseInt("0");
	for (i = 0; i < dealerCardQuantity; i++) {
		$("#dealerCard" + i.toString()).hide('slow');
	}
	dealerCardQuantity = parseInt("0");
	setPlayerScore(-1);
	setDealerScore(-1);
	$("#panel").flip('toggle');
	$(".sbutton").toggle('slow');
	setInfoText("");
	buttonBlock=false;
}
function viewResponse(data) {
		setInfoText("Choose hit or stand");
		setCardImage("playerCard", data["playerCard"], cardQuantity);
		cardQuantity++;
		setPlayerScore(data["playerSum"]);
	if (data["gameStatus"] != "OK") {
		buttonBlock=true;
		endGame(data);
	}
}
