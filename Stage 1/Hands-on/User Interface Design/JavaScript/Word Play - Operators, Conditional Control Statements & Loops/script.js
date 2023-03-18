function wordPlay(number){
    //fill the code
    if(number > 50)
    	return "Range is High";
    else if(number < 1)
    	return "Not Valid";
    else
    {
		let str = "";
		for(let i=1; i <= number; i++)
		{
			if(i%5 == 0 && i%3 == 0)
				str+=" Jump";
			else if(i%3==0)
				str+=" Tap";
			else if(i%5==0)
				str+=" Clap";
			else
				str+=" "+String(i);
		}
		return str;
	}
}