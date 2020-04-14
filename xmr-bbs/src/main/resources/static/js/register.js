$(document).ready(function(){
    var ordertime=60   //设置再次发送验证码等待时间
    var timeleft=ordertime
    var btn=$(".yzm")
    var phone=$(".phone")
    var reg = /^1[0-9]{10}$/;  //电话号码的正则匹配式

    phone.keyup(function(){
        if (reg.test(phone.val())){
            btn.removeAttr("disabled")  //当号码符合规则后发送验证码按钮可点击
        }
        else{
            btn.attr("disabled",true)
        }
    })

    //计时函数
    function timeCount(){
        timeleft-=1
        if (timeleft>0){
            btn.val(timeleft+" 秒后重发");
            setTimeout(timeCount,1000)
        }
        else {
            btn.val("重新发送");
            timeleft=ordertime   //重置等待时间
            btn.removeAttr("disabled");
        }
    }

    //事件处理函数
    btn.on("click",function(){
        $(this).attr("disabled",true); //防止多次点击
        //此处可添加 ajax请求 向后台发送 获取验证码请求
        $.ajax({
            type: "POST", //用POST方式传输
            dataType: "text", //数据格式:JSON
            url: '/sendPhone', //目标地址
            data: "phone=" + $(".phone").val(), //post携带数据
            error: function () {alert("发送失败") }, //请求错误时的处理函数
            success: function (){alert("发送成功~")}, //请求成功时执行的函数
        });
        timeCount(this);
    })
    //弹出注册框
    $('.zc_btn').click(function () {
        $(".zc_modal").modal("show");
        return false;
    })
    $("#name").change(function () {
        var name=$("#name").val().trim();
        if(name==""&&name==null){
            return false;
        }
        nameisUsed(name);

    });
    function nameisUsed(name) {
        if(name==null||name==""){
            layer.msg("用户名不能为空", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }else if(!/^[a-z0-9]{6,16}$/.test(name)){
            layer.msg("用户名由6-16位字母或数字组成", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }
        //验证用户名是否可用
        $.get("/ajaxNameUsed",{"username":name,"time":new Date()},function (data) {
            if(data.code==1000){
                layer.msg("用户名可用", {time: 2000, icon: 1, shift: 2}, function () {
                });
                return true;
            }else{
                layer.msg("用户名已被占用", {time: 2000, icon: 5, shift: 6}, function () {
                });
                return false;
            }
        })
    }

    //点击注册
    $("#zc").click(function () {
        //验证用户名是否符合规
        var password1=$("#password1").val().trim();
        var password2=$("#password2").val().trim();
        var code=$("#code").val().trim();
        var phone=$("#phone").val().trim();
        var name=$("#name").val().trim();


        if(name==null||name==""){
            layer.msg("用户名不能为空", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }else if(!/^[a-z0-9]{6,16}$/.test(name)){
            layer.msg("用户名由6-16位字母或数字组成", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }
        if(password1==null||password1==''||password2==null||password2==''){
            layer.msg("密码不能为空", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }else if(password1!=password2){
            layer.msg("两次的密码不一致", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }else  if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/.test(password2)){
            layer.msg("密码长度要大于6位，由数字和字母组成", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }
        if(phone==null||phone==''){
            layer.msg("验证码不能为空", {time: 2000, icon: 5, shift: 6}, function () {
            });
            alert("电话号码不能为空");
            return false;
        }else if (!/^1[0-9]{10}$/.test(phone)){
            layer.msg("不是合法的电话号码", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }
        if(code==null||code==''){
            layer.msg("验证码不能为空", {time: 2000, icon: 5, shift: 6}, function () {
            });
            return false;
        }
        //验证通过提交表信息
        var args={"name":name,"password1":password1,"password2":password2,"code":code,"phone":phone,"time":new Date()}
        $.post("/register",args,function (data) {
            if(data.code==1000){

                swal("注册成功~", "稍作等待，将自动登入论坛~", "success",
                    {
                        buttons: false,
                        timer: 3000,
                    })
                    .then((value) => {
                    window.location.href="/";
            })
            }else{
                swal(data.message, data.message, "warning")
            }

        })
    })

})
