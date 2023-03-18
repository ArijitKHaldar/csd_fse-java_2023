function modifyString(str)
{
//fill code here. Remove all spaces and convert string to lower case
    var strNoSpaces = str.replace(/\s/g,'');
    return strNoSpaces.toLowerCase();
}

function uniqueCharacters(str)
{
//fill code here. Remove all duplicate chars
    var strNoSpaces = modifyString(str);
    
    var len=strNoSpaces.length, char, charCount = {}, newStr = [], resultStr='';
    for(var i=0; i<len; i++)
    {
        char = strNoSpaces[i];
        if(charCount[char])
        {
            charCount[char]++;
        }
        else
            charCount[char] = 1;
    }
    for(var j in charCount)
        resultStr+=j;
    
    return resultStr;
}