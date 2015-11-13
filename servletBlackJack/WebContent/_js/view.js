function refreshView(){
for (i = 0; i < cardQuantity; i++) {
		$("#playerCard" + i.toString()).hide('slow');
	}
	for (i = 0; i < dealerCardQuantity; i++) {
		$("#dealerCard" + i.toString()).hide('slow');
	}
cardQuantity = parseInt("0");
	dealerCardQuantity = parseInt("0");
	setPlayerScore(-1);
	setDealerScore(-1);
	pot=parseInt("0");
	$(".sbutton").toggle('slow');
	setBid("Bid: <br />");
	setInfoText("");
}
function setBid(income){
document.getElementById('bid').innerHTML = income;
}
function setSlider(maxValue){
      $( "#slider" ).slider({
          range: "min",
          value: 0,
          min: 1,
          max: maxValue,
          slide: function( event, ui ) {
              $( "#amount" ).val(ui.value );
          }
      });
      $( "#amount" ).val( $( "#slider" ).slider( "value" ) );
}
function showGameResult(data){
var status = data["gameStatus"];
	switch(status){
		case "PLAYER_BUSTED":
			swal("Dealer wins, looser!", "You've busted");
			break;
		case "DEALER_BUSTED":
			swal("You've won "+data['winSum']+" ,lucky!", "Dealer's busted");
			break;
		case "PLAYER_WINS":
			swal("You've won "+data['winSum']+" ,lucky!", "You win");
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
	}
function setCardImage(cardOwner, cardName, index) {
	$("#" + cardOwner + index).show('slow');
	document.getElementById(cardOwner + index).src = "img/cards_png/" + cardName + ".png";
}
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