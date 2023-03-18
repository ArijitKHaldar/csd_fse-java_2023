function validateEmail(email) {
   //fill the code
   var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
   
   if(email.match(validRegex))
       return 'Valid email address!';
   else
       return 'Invalid email address!'
    
}
console.log(validateEmail('abc-defmail.com'));

