<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>管理登录界面</title>
    <style type="">
    body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #1D3647;
}
</style>
<script    src="<%=path%>/js/jquery-1.8.2.min.js"></script>
<script    src="<%=path%>/js/utils/cookieUtils.js"></script>
<script src="<%=path%>/js/login/login.js"></script>
    <link href="<%=path%>/css/page/login.css" rel="stylesheet" type="text/css" />
<body>
<div id="maincentent" style="height: 100%; height: 100%; background-color: #1D3647;" >
<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="42" valign="top"><table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      <tr>
        <td width="1%" height="21">&nbsp;</td>
        <td height="42">&nbsp;</td>
        <td width="17%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      <tr>
        <td width="49%" align="right"><table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            <tr>
              <td height="138" valign="top"><table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="149">&nbsp;</td>
                </tr>
                <tr>
                  <td height="278" align="right" valign="top">
                  <img src="<%=path%>/images/login/company.jpg" height="230px" width="350px" alt="" />
                  </td>
                </tr>
              </table></td>
            </tr>
            
        </table></td>
        <td width="1%" >&nbsp;</td>
        <td width="50%" valign="bottom"><table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="4%">&nbsp;</td>
              <td width="96%" height="38"><span class="login_txt_bt">xxxxx管理系统</span></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  <tr>
                    <td height="164" colspan="2" align="middle">
                    <form name="myform" action="${pageContext.request.contextPath}/loginAction_gologin.action" method="post">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="13%" height="38" class="top_hui_text" ><span class="login_txt">用户名：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text" align="left"> <input name="username" class="editbox4" value="" size="20" />                            </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt"> 密 码： &nbsp;&nbsp; </span></td>
                            <td height="35" colspan="2" class="top_hui_text" align="left"><input class="editbox4" type="password" size="20" name="password" />
                              <img src="<%=path %>/images/login/luck.gif" width="19" height="18" /> </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" ><span class="login_txt">验证码：</span></td>
                            <td height="35" colspan="2" class="top_hui_text" align="left"><input class=wenbenkuang name=verifycode type=text value="" maxLength=4 size=10 />
                              </td>
                          </tr>
                          <tr>
                            <td height="35" ><input id="remenber" type="checkbox" /><span style="font-size:10px;">记住密码</span> </td>
                            <td width="20%" height="35" align="left"><input name="Submit" type="button" class="button" id="login" value="登 陆"/> </td>
                            <td width="67%" class="top_hui_text" align="left"><input name="cs" type="reset" class="button" id="cs" value="取 消" /></td>
                          </tr>
                        </table>
                        <br/>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="433" height="164" align="left" valign="top"><span style="color: red; size:12px;" id="error"></span></td>
                    <td width="57" align="right" valign="bottom">&nbsp;</td>
                  </tr>
              </table></td>
            </tr>
          </table>
          </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="20"><table width="100%"  border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      <tr>
        <td align="center"><span class="login-buttom-txt"></span></td>
      </tr>
    </table></td>
  </tr>
</table>
</div>
</body>
</html>
