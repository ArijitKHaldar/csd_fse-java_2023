function OrderCake(str) {
	//fill the code
	
	//Weight of Cake
	var weightGrams = Number(str.slice(0,4));
	var weightKg = Math.round(weightGrams / 1000);
	
	//Flavour
	var flavour = str.slice(4, str.length - 3);
	
	//Order id
	var orderId = Number(str.slice(str.length - 3, str.length));
	
	//Price
	var price = Math.round(450 * weightGrams / 1000);
	
	return 'Your order for '+weightKg.toString()+' kg '+flavour.toString()+' cake has been taken. You are requested to pay Rs. '+price.toString()+' on the order no '+orderId.toString();
}

console.log(OrderCake('5848ButterBlast485'));