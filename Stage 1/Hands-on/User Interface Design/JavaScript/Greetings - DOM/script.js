function display()
{
    const studentName = document.getElementById("sname").value;
    const courseName = document.getElementById("course").value;
    
    if (!studentName)
        document.getElementById("greet").innerHTML = "Name cannot be empty";
    else
        document.getElementById("greet").innerHTML = "Hi, " + studentName + ". You have successfully registered for the " + courseName + " course.";
}