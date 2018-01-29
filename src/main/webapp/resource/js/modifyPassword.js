/**
 * Created by 沈英逊 on 2017/7/13.
 */
function $(x){
    return document.getElementById(x);
}
function check01(){
    var a=$("mm1").value.length;
    if(a==0){
        document.getElementById('mm01').innerHTML="密码不能为空";
    }
}
function of(){
    var c=$("mm1").value.length;
    var d=$("mm1").value.length;
    if(c!=0){
        document.getElementById('mm01').innerHTML="";
    }
    if(d!=0){
        document.getElementById('mm02').innerHTML="";
    }

}
function check02(){
    var b=$("mm2").value.length;
    if(b==0){
        document.getElementById('mm02').innerHTML="密码不能为空";
    }
}

function check03(){
    var a=$("mm2").value.length;
    var b=$("mm3").value.length;
    var c=$("mm2").value==$("mm3").value
    if(a!=0 && b!=0){
        return c?true:alert("两次密码不一样，请输入正确的密码");
    }

}