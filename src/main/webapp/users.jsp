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
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/users.css">
<script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
<script type="text/javascript" src="js/users.js"></script>
</head>
<body>
  <%@include file="header.jsp"%>
  <div class="editing" style="display: none">
    <form action="#">
      Old password: <input type="password"><br/>
      repeat password: <input type="password"><br/>
    </form>
  </div>
  <div class="usersList">
<h1>Number of users: <%=users.size()%></h1>
<ul class="users">
<%
for (UserInfo u: users) {
  String userClass = "user";
  String msg = "";
  msg += "<div class=\"login\">" + u.getLogin() + "</div>";
  if (u.isDisabled()) {
    userClass += " disabled";
    msg += " " + enableLink(response, u.getLogin());;
  } else {
    msg += " " + disableLink(response, u.getLogin()) + " " + resetPwdLink(response, u.getLogin());
  }
  msg = "<li class=\"" + userClass + "\">" + msg + "</li>";
%>
<%=msg%>
<%
}
%>
</ul>
  </div>
<%@include file="footer.jsp"%>
</body>
</html>
