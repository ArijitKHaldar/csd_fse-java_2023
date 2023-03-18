const feedbackInputTextArea = document.getElementById("feedback");
const result = document.getElementById("result");
var feedbacksArray = [];

function addFeedback(){
 //Fill the required logic   
 var feedback = feedbackInputTextArea.value;
 feedbacksArray.push(feedback);
 result.innerHTML = "<h2>Feedback Details</h2><p><strong>Successfully Added Feedback Details!</strong></p>";
 feedbackInputTextArea.value = "";
}

function displayFeedback(){
    //Fill the required logic
    var feedbackText = "";
    for (var i = 0; i < feedbacksArray.length; i++) {
        if (i !== 0) {
            feedbackText += "<br>";
        }
        feedbackText += "Feedback " + (i+1) + "<br>" + feedbacksArray[i] + "";
    }
    result.innerHTML = "<h2>Feedback Details</h2>" + "<p>" + feedbackText + "</p>";
    feedbacksArray = [];
}