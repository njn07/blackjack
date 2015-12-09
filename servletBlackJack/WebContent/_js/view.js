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
	setBid("Bid: <br />&nbsp");
	setInfoText("");
	bj=false;
}
function setBid(income){
document.getElementById('bid').innerHTML = income;
}
function setSlider(maxValue){
      $( "#slider" ).slider({
          range: "min",
          value: 1,
          min: 1,
          max: maxValue,
          slide: function( event, ui ) {
              $( "#amount" ).val(ui.value );
          }
      });
      $( "#amount" ).val( $( "#slider" ).slider( "value" ) );
      $("#amount").keydown(function(event) {
    	  var regex = new RegExp(/[0-9]+/);
    	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    	    if (!regex.test(key)) {
    	    	if (key.charCodeAt(0) == 8 || key.charCodeAt(0) == 9 ||key.charCodeAt(0) == 46 || (key.charCodeAt(0)> 36 && key.charCodeAt(0) < 41)) {
    	    			return true;
    	    			}
    	       event.preventDefault();
    		        	       return false;
    	    }
    	  });
      $("#amount").keyup(function(event) {
    	  
    	  var data = $("#amount").val();
    	  if(data==""){
    		  $("#amount").val(1);
    		  console.log("lol");
    	  }
	    if (data.length > 0)
	    {
	       if (parseInt(data) >= 0 && parseInt(data) <= maxValue)
	       {
	           $("#slider").slider("option", "value", data);}
	       
	       else
	       {
	   if (parseInt(data) < 1)
	          {
	     $("#amount").val("1");
	            $("#slider").slider("option", "value", "1");
	          }
	          if (parseInt(data) > maxValue)
	          {
	            $("#amount").val(maxValue);
	            $("#slider").slider("option", "value", maxValue);
	          }
	       }
	    }
	    else
	    {
	      $("#slider").slider("option", "value", "0");
	    } 
      });
      $("#amount").change(function(event) {
    	  var data = $("#amount").val();
	    if (data.length > 0)
	    {
	       if (parseInt(data) >= 0 && parseInt(data) <= maxValue)
	       {
	           $("#slider").slider("option", "value", data);}
	       
	       else
	       {
	   if (parseInt(data) < 1)
	          {
	     $("#amount").val("1");
	            $("#slider").slider("option", "value", "1");
	          }
	          if (parseInt(data) > maxValue)
	          {
	            $("#amount").val(maxValue);
	            $("#slider").slider("option", "value", maxValue);
	          }
	       }
	    }
	    else
	    {
	      $("#slider").slider("option", "value", "0");
	    } 
      });
}
function showGameResult(data){
var status = data["gameStatus"];
	switch(status){
		case "PLAYER_BUSTED":
			countWins=0;
			swal("Dealer wins, looser!", "You've busted");
			break;
		case "DEALER_BUSTED":
			if(bj){
				swal("Black Jack! yeah! ", "You're paid 3:2 to your bid - "+data['winSum']);
			}
			else{
			if(countWins==0){
				swal("You've won "+data['winSum']+" ,lucky!", "Dealer's busted");
			}
			if(countWins==1){
				swal("Wow! Second in a row! Your win is - "+data['winSum'],"Wanna try again, huh?");
			}
			if(countWins==2){
				swal("Come on! It's impossible! ","We bet you're cheating! Now you stole "+data['winSum'] +" from us!");
			}
			if(countWins>2){
				swal("COME FREAKING ON! NO WAY!",data['winSum']);
			}
			countWins++;
			}
			break;
		case "PLAYER_WINS":
			if(bj){
				swal("Black Jack! yeah!","You're paid 3:2 to your bid - "+data['winSum']);
			}
			else
				{
				if(countWins==0){
					swal("You've won "+data['winSum']+", lucky!", "You win");
				}
				if(countWins==1){
					swal("Wow! Second in a row! Your win is - "+data['winSum'],"Wanna try again, huh?");
				}
				if(countWins==2){
					swal("Come on! It's impossible! ","We bet you're cheating! Now you stole "+data['winSum'] +" from us!");
				}
				if(countWins>2){
					swal("COME FREAKING ON! NO WAY!",data['winSum']);
				}
				countWins++;
				}
			break;
		case "DEALER_WINS":
			countWins=0;
			swal("Dealer wins, looser!", "It's a sad sad situation!");
			break;
		case "TIE":
			countWins=0;
			swal("TIE", "Yuppppy!");
			break;
		default:
			swal("Oops","game did some mistake");
			break;
	}
	}

function setCardImage(cardOwner, cardName, index) {
	if(cardName!=undefined){
	if(cardName=="background"){
		$("#" + cardOwner + index).css({ "height":"157px","margin-top":"1px"});
	}
	else{
		$("#" + cardOwner + index).css({ "height":"160px","margin-top":"0"});
	}

	$("#" + cardOwner + index).show("slow");
	document.getElementById(cardOwner + index).src = "img/cards/" + cardName + ".png";
	}
	else fatalError();
}
function setPlayerScore(score){
if(score!=-1)
document.getElementById('playerSum').innerHTML = "Player's score:<br />"+(score).toString();
else 
document.getElementById('playerSum').innerHTML = "";
}
function setDealerScore(score){
if(score!=-1)
document.getElementById('dealerSum').innerHTML = "Dealer's score:<br />"+(score).toString();
else
document.getElementById('dealerSum').innerHTML = "";
}
function setInfoText(text){
document.getElementById('infotext').innerHTML = text;
}