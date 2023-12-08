function postResponse(){
    var questionId=$("#questionId").val();
    var commentContent=$("#commentContent").val();
    $.ajax({
        contentType:'application/json',
        type:"POST",
        url:"/comment",
        data:{
           "parentId":questionId,
           "commentContent":commentContent,
           "type":1
        },
        dataType:"json"
    });
}