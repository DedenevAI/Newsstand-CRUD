<%@ page import="com.alexproject.dao.Dao" %>
<%@ page import="com.alexproject.Config" %>
<%@ page import="com.alexproject.model.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Публикация</title>
</head>
<%
	Dao dao = Config.getDao();
	String idValue = request.getParameter("id");
	Publication pub = null;
	if(idValue!=null && !idValue.trim().isEmpty()) {
		long id = Long.parseLong(idValue);
		pub = dao.get(id);
	}
	PublicationType type = null;
	String fieldName = "Value";
	String name  = "";
	String value = "";
	if(pub!=null) {
		type = (pub != null) ? pub.getType() : null;
		switch (type) {
			case BOOK: fieldName="Author"; value=((Book)pub).getAuthor(); break;
			case MAGAZINE: fieldName="IssueNumber"; value=((Magazine)pub).getPublishingHouse(); break;
			case NEWSPAPER: fieldName="publishingHouse"; value=""+((Newspaper)pub).getIssueNumber(); break;
			default: fieldName="Value";
		}
		name = pub.getName();
	}
%>

<body>
	<center>
		<h1>Публикация</h1>
	</center>
	<div align="center">
		<form action="store" method="post">
		<input type="hidden" name="id" value='<%=pub!=null ? ""+pub.getId() : "" %>'>
		<table style="width: 70%">
			<tr>
				<th style="width: 20%">Type</th>
				<td>
					<select name="type" style="width: 100%">
						<option value="book" <%=PublicationType.BOOK==type ? "selected" : "" %>>Book</option>
						<option value="magazine" <%=PublicationType.MAGAZINE==type ? "selected" : "" %>>Magazine</option>
						<option value="newspaper" <%=PublicationType.NEWSPAPER==type ? "selected" : "" %>>Newspaper</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>Name</th>
				<td><input type="text" name="name" style="width: 100%" value="<%=name %>"></td>
			</tr>
			<tr>
				<th><%=fieldName %>
				</th>
				<td><input type="text" name="value" style="width: 100%" value="<%=value %>"></td>
			</tr>
		</table>
		<br>
		<input type="submit">

	</form>

	</div>
</body>
</html>
