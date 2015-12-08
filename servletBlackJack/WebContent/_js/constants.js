
var hitParams = {
	action : "HIT",
	userId : "casualPlayer"
};
var restartParams = {
	action : "RESTART",
	userId : "casualPlayer"
};
var standParams = {
	action : "STAND",
	userId : "casualPlayer"
};
var gameStartParams = {
	action : "GAMESTART",
	userId : "casualPlayer"
};
var endGameParams = {
		action : "RESTART",
		userId : "casualPlayer"
	};
var betParams = {
	action : "PLACEBET",
	userId : "casualPlayer"
};
var balanceParams = {
	action : "GETBALANCE",
	userId : "casualPlayer"
};
var refillParams = {
	action : "REFILL",
	userId : "casualPlayer"
};
const REFILLMESSAGE="You have just refilled your wallet with 1000 coins";
const DUPLICATEGAMETEXT="You are trying to play more than 1 game at once! If you have a wish, you may contact us(you will be redirected in a few seconds)";
//var restAdress="http://black-jakk.rhcloud.com/rest";
var restAdress="http://localhost:8080/servletBlackJack/rest";
