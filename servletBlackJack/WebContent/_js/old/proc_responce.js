var DIRECTORY = "cards_png";
var DEALER = true;
var PLAYER = false;

function getJSON(dealer){
	if(dealer)
		return '{ "dealerSum" : 16, "gameResult": "OK", "dealerCards": "Qs"}';
	return '{ "playerSum" : 16, "playerCard":"Kd", "gameStatus": "OK"}';
}

function attribute(pair){
	return pair[0];
}

function value(pair){
	return pair[1];
}

function combine(lst1, lst2, f){
	if(!_.isEmpty(lst1)){
		f(car(lst1), car(lst2));
		combine(cdr(lst1), cdr(lst2), f);
	}
}

function formPath(card, dir){
	return dir + "/" + card + ".png"; 
}

function adaptCard(maxCard){
	return (maxCard[1] + maxCard[0]).toLowerCase();
}

function addText(id, value){
	var elem = document.getElementById(id);
	elem.innerHTML += value;
}

function setSrc(id, value){
	var elem = document.getElementById(id);
	elem.setAttribute("src", value);
}

function addImage(id, value){ 
	var path = formPath(adaptCard(value), DIRECTORY);
	setSrc(id, path);
}

function displayJSON(source, actions){
	var jsonObj = JSON.parse(getJSON(source));
	combine(_.pairs(jsonObj), actions, function(obj, action){
		return action(attribute(obj), value(obj));
	});
}

function main(){
	displayJSON(PLAYER, [addText, addImage, addText]);
	displayJSON(DEALER, [addText, addText, addImage]);
}
