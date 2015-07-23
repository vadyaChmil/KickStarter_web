<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page</title>
</head>

<body>

	<table width="80%" height="150" border="0" align="center">
		<tr>
			<th align="left" width="20%">
				<h4>
					<a href="home">Home</a> / <a
						href="category?id=${project.categoryId}">Back</a> / <a
						href="donation?id=${project.id}">Donation</a>
				</h4>
			</th>
			<th align="right">
				<h3>
					<em>${quote.quote}</em>
				</h3>
				<h4>
					<em>${quote.author}</em>
				</h4>
			</th>
		</tr>
		<tr>
			<td colspan="2">
				<ul type="circle">
					<li><h4>
							<a href="project?id=${project.id}"> <c:out
									value="${project.name}" /></a>
						</h4></li>
				</ul>
				<p>
					Project needs <font color="sky blue"><b><c:out
								value="${project.needMoney/100}" /></b></font> dollars
				</p>
				<p>
					Project has <font color="sky blue"><b>${project.currentMoney}</b></font>
					dollars
				</p>
				<p>
					Days to go - <font color="sky blue"><b>${project.daysLeft}</b></font>
				</p>
				<p>
					Description: <em>${project.description}</em>
				</p>
				<p>
					History of project: <em>${project.history}</em>
				</p>
				<p>
					<a href="${project.urlVideo}" target="youtube"><em>Video</em></a>
				</p>
			</td>
		</tr>
		<c:if test='${not empty project.questions}'>
			<tr align="left">
				<td width="50%"><h4>
						<font color="sky blue"><em>Question</em></font>
					</h4></td>
				<td width="50%"><h4>
						<font color="sky blue"><em>Answer</em></font>
					</h4></td>
			</tr>
			<c:forEach var="questions" items="${project.questions}">
				<tr>
					<td width="50%" align="left"><em>${questions.key}</em></td>
					<td width="50%" align="left"><em>${questions.value}</em></td>
				</tr>
			</c:forEach>
		</c:if>
		<tr align="left">
			<td colspan="2">
				<p></p>
				<form name="form" method="get" action="question">
					<textarea name="text" cols="50" rows="6" id="text" placeholder="Input question..."></textarea>
					<p>
						<input type="reset" name="reset" value="Reset">
						<input type="submit" name="submit" value="Send">
					</p>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>