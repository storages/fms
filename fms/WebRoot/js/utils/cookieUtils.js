function getCookie(name)//ȡcookies����        
{
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null) return unescape(arr[2]); return null;
}





function delCookie(name)//ɾ��cookie
{
   document.cookie = name+"=;expires="+(new Date(0)).toGMTString();
}

function addCookie(name,value)//����������һ����cookie�����ӣ�һ����ֵ
{
    var Days = 30; //�� cookie �������� 30 ��
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}



//����var cookie_val = getCookie("username");