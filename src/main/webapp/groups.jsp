<%@page import="com.atex.milan.svnuser.users.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.atex.milan.svnuser.app.SvnApp"%>
<%@page import="com.atex.milan.svnuser.users.AuthInfo"%>
<%@page import="com.atex.milan.svnuser.users.GroupInfo"%>
<%@page import="com.atex.milan.svnuser.users.UserInfo"%>
<%
SvnApp app = SvnApp.getInstance();
AuthInfo info = app.getInfo();
List<GroupInfo> groups = info.getGroups();
List<UserInfo> users = info.getUsers();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Page</title>
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/groups.css">
<%@include file="itf.jsp"%>
<script type="text/javascript" src="js/groups.js"></script>
<script type="text/javascript">
var users = [];
<%
for (UserInfo u: users) {
%>
  users.push('<%=u.getLogin()%>');
<%
}
%>
</script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="adduser_dialog ui none" title="Add user" style="display: none">
  <form action="#">
    new user:
    <select id="addusers" name="users" multiple="multiple">
    </select>
  </form>
</div>
<div class="addgroup_dialog ui none" title="Add new group" style="display: none">
  <form action="#">
    Groupname: <input type="text" name="name" class="name"><br/>
  </form>
</div>
<div class="groupsList">
<h1>Number of groups: <%=groups.size()%></h1>
<div class="topbuttons">
  <span class="addgroup button">+ Add Group</span>
</div>
<ul class="users">
<%
for (GroupInfo g: groups) {
  String groupClass = "group";
  if (g.isDisabled()) {
    groupClass += " disabled";
  }
%>
<li class="<%=groupClass%>">
<div class="groupname">
  <span class="gname"><%=g.getName() %></span>
  <div class="members">
  <span class="addmember">+</span>
  <span class="usersep">|</span>
<% for(String m: g.getMembers()) { %>
  <div class="umember">
  <span class="member"><%=m%></span>
  <span class="removemember">-</span>
  <span class="usersep">|</span>
  </div>
<% } %>
  </div>
</div>
</li>
<%
}
%>
</ul>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
