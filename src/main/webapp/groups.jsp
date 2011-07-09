<%-- 
    Document   : groups.jsp
    Created on : Jul 9, 2011, 9:08:35 AM
    Author     : mnova
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.atex.milan.svnuser.app.SvnApp"%>
<%@page import="com.atex.milan.svnuser.users.AuthInfo"%>
<%@page import="com.atex.milan.svnuser.users.GroupInfo"%>
<%
SvnApp app = SvnApp.getInstance();
AuthInfo info = app.getInfo();
List<GroupInfo> groups = info.getGroups();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Page</title>
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/groups.css">
<script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="groupsList">
<h1>Number of groups: <%=groups.size()%></h1>
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
<%=g.getName() %>
  <div class="members">
<% for(String m: g.getMembers()) { %>
  <span class="member"><%=m%></span>
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
