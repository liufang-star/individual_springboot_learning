$(function () {
    $("#validateCode").keyup(function(){

        checkLoginValidateCode($(this).val());
    });

});

function uploadLoginValidateCode() {
    //点击验证码刷新验证码
    $("#loginValidateCode").attr("src","/loginValidateCode?random="+new Date().getMilliseconds());
}


function checkLoginValidateCode(validateCode) {
    var error = $("#validateCode").parent().next();
    if(validateCode != null && validateCode != ""){
        $.ajax({
            type: "POST",
            async:false,
            url: "/checkLoginValidateCode?validateCode="+validateCode,
            success : function(json) {
                if(json != null && json.code == 200 && json.status != null) {
                    if (json.status == true) {
                        error.html("恭喜你验证码，正确！！！");
                    } else if(json.status == false){
                        error.html("验证码错误，请重新输入");
                    }else{
                        error.html("验证码过期，请重新输入");
                        uploadLoginValidateCode();
                    }
                }
                return false;
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
                alert("服务器错误！状态码："+XMLHttpRequest.status);
                // 状态
                console.log(XMLHttpRequest.readyState);
                // 错误信息
                console.log(textStatus);
                return false;
            }
        });
    }else{
        error.html("请输入验证码！");
    }
}
