//Write Your jQuery Code Here
$(function() {
    $("#btnId").click(function() {
        const name = $("#txt").val();
        $("#address").text("\"Welcome " + name + "!\"");
    });
});