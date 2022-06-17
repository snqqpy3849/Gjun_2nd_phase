<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:useBean id="Fortune" class="JqueryGuessNumber.domain.GuessGameLogic" scope="session">
			<% Fortune.initialize(1, 10); %>
		</jsp:useBean>
		<%	int guessNumber = Integer.parseInt(request.getParameter("number"));
			if(Fortune.isCorrectGuess(guessNumber)){
				session.invalidate();
		%><jsp:forward page="bingo.jsp"/>
		<%
			}else{
				int remainder = Fortune.getRemainder();
				if(remainder > 0){
		%>The Number <%=guessNumber %> is not correct ! <br>
		  You still have <%=remainder %> chances. <br>
		  <%=Fortune.getHint() %><br>
		  <a href="JqueryGuessNumber/GuessNumber.html">Try again</a>
			<%	}else{
					session.invalidate();
			%><jsp:forward page="noChances.jsp"></jsp:forward>
		<%
				}
			}
		%>
	</body>
</html>