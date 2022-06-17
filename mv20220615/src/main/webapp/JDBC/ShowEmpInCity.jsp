<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="java.util.* , JDBC.model.*" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="BIG5">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Hello World!</h1>
        <table border="1">
        <%
            ArrayList<SimpleEmp> list=new ArrayList<SimpleEmp>() ;
            // list=(ArrayList<SimpleEmp>) session.getAttribute("emps");
            list = (ArrayList<SimpleEmp>) request.getAttribute("emps");
            for(SimpleEmp ep :list){
			%>   
			<tr>
				<td><%= ep.getId()%></td>
				<td><%= ep.getFname()%></td>
				<td><%= ep.getLname()%></td>
				<td><%= ep.getEmail()%></td></tr>
			<% 
			}           
            
        %>
        </table>
		
	</body>
</html>