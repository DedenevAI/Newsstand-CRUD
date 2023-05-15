<%@ page import="com.alexproject.model.Publication" %>
<%@ page import="com.alexproject.dao.Dao" %>
<%@ page import="com.alexproject.Config" %>
<%@ page import="java.util.List" %>
<%@ page import="com.alexproject.model.Book" %>
<%@ page import="com.alexproject.model.Newspaper" %>
<%@ page import="com.alexproject.model.Magazine" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<html>
<head>
	<title>Список публикаций</title>
</head>
<body>
	<center>
		<h1>Список публикаций</h1>
	</center>

    <div align="center">
<%
	Dao dao = Config.getDao();
	List<Publication> list = dao.getAll();
%>
		<table border="1" cellpadding="5">
			<tr>
				<th>ID</th>
				<th>Type</th>
				<th>Name</th>
				<th>Author</th>
				<th>IssueNumber</th>
				<th>publishingHouse</th>
				<td></td>
				<td></td>
			</tr>
			<%
				for(Publication pub: list) {
			%>
			<tr>
				<td><%=pub.getId()%></td>
				<td><%=pub.getType().toString()%></td>
				<td><%=pub.getName()%></td>
				<td><%=(pub instanceof Book) ? ((Book)pub).getAuthor() : ""%></td>
				<td><%=(pub instanceof Newspaper) ? ((Newspaper)pub).getIssueNumber() : ""%></td>
				<td><%=(pub instanceof Magazine) ? ((Magazine)pub).getPublishingHouse() : ""%></td>
				<td><a href="<%="edit?id="+pub.getId()%>">Edit</a></td>
				<td><a href="<%="del?id="+pub.getId()%>">Del</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<br>
		<a href="pub_form.jsp">добавить публикацию</a>
	</div>

</body>

</html>