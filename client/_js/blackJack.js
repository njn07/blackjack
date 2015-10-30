$(function() {
	$("#panel").flip({
		axis : 'x',
		trigger : 'manual',
		reverse : true
	});
	$(".sbutton").click(function() {
		$("#panel").flip('toggle');
		$(this).toggle('slow');
		document.getElementById('dealerSum').innerHTML = "Dealer is waiting";
		setInfoText("choose hit or stand");
	});
	$("#hit").click(function() {
		if(!buttonBlock){
		
		$.getJSON(restAdress, hitParams, viewResponse);
		}
	});
	$("#stand").click(function() {
		if(!buttonBlock){
		setInfoText("you chose to stand");
		$.getJSON(restAdress, standParams, viewStandResponse);
		}
	});
	setTimeout(function() {
		swal("Here we are", "LET'S SHUFFLE UP AND DEAL");
	}, 500);		
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
	}, 2500);
}
function processGameOver(data) {
var status = data["gameStatus"];
	switch(status){
		case "PLAYER_BUSTED":
			swal("Dealer wins, looser!", "You've busted");
			break;
		case "DEALER_BUSTED":
			swal("You've won, lucky!", "Dealer's busted");
			break;
		case "PLAYER_WINS":
			swal("You've won, lucky!", "Yuppppy!");
			break;
		case "DEALER_WINS":
			swal("Dealer wins, looser!", "It's a sad sad situation!");
			break;
		case "TIE":
			swal("TIE", "Yuppppy!");
			break;
		default:
			swal("wtf","wtf");
			break;
	}
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
		setCardImage("playerCard", data["playerCard"], cardQuantity);
		cardQuantity++;
		setPlayerScore(data["playerSum"]);
	if (data["gameStatus"] != "OK") {
		buttonBlock=true;
		endGame(data);
	}
}
