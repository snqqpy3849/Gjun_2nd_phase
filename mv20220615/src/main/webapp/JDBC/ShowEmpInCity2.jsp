<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Insert title here</title>
	</head>
	<body>
		<table border="1">
			<c:forEach var="emp" items="${emps}">
				<tr>
					<td><c:out value="${emp.id }"></c:out></td>
					<td><c:out value="${emp.fname }"></c:out></td>
					<td><c:out value="${emp.lname }"></c:out></td>
					<td><c:out value="${emp.email }"></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>