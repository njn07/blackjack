$( document ).ready(function() {
		$("#panel").flip({
  		axis: 'x',
  		trigger: 'manual',
		reverse: true
		});	
   $(".sbutton").click(function(){
			$("#panel").flip('toggle');	
			var score=parseInt("0");
			$(".dealerCard").each(function(index) {
					$(this).toggle('slow');
					index=index%10+1;
					score+=index;
 					$( this ).attr("src","img/cards_png/c"+index+".png");
					if(score>=17)return false;
					});
			console.log(score);
			document.getElementById("dealerSum").innerHtml+=score.toString();
	});
});