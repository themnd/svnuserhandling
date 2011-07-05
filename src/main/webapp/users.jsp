<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.atex.milan.svnuser.app.SvnApp"%>
<%@page import="com.atex.milan.svnuser.users.UserInfo"%>
<%
SvnApp app = SvnApp.getInstance();
List<UserInfo> users = app.getUsers();
%>
<%!
String disableLink(HttpServletResponse response, String login)
{
  return "<a class=\"disableLink\" href=\"#\" onclick=\"disableUser('" + login + "');\">disable</a>";
}
String enableLink(HttpServletResponse response, String login)
{
  return "<a class=\"enableLink\" href=\"#\" onclick=\"enableUser('" + login + "');\">enable</a>";
}
String resetPwdLink(HttpServletResponse response, String login)
{
  return "<a class=\"resetLink\" href=\"#\" onclick=\"resetUserPwd('" + login + "');\">reset</a>";
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Page</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript">
function processAjax(data, f) {
  if (data && data.code == 1) {
    alert("error processing the request: " + data.err);
  } else {
    f(data);
  }
}
function doAjax(url, f) {
  $.getJSON(url, function(data) {
    processAjax(data, f)
  });
}
function disableUser(u)
{
  var url = 'disableuser?login=' + u;
 doAjax(url, function(data) {
    alert(data.msg);
    location.reload();
 });
}
function enableUser(u)
{
  var url = 'enableuser?login=' + u;
  doAjax(url, function(data) {
    alert(data.msg);
    location.reload();
  });
}
function resetUserPwd(u)
{
  var url = 'resetpassword?login=' + u;
  doAjax(url, function(data) {
    alert(data.msg);
    location.reload();
  });
}
</script>
</head>
<body>
<h1>Number of users: <%=users.size()%></h1>
<ul>
<%
for (UserInfo u: users) {
  String msg = "<li>";
  msg += u.getLogin();
  if (u.isDisabled()) {
    msg += " - DISABLED " + enableLink(response, u.getLogin());;
  } else {
    msg += " " + disableLink(response, u.getLogin()) + " " + resetPwdLink(response, u.getLogin());
  }
  msg += "</li>";
%>
<%=msg%>
<%
}
%>
</ul>
</body>
</html>
