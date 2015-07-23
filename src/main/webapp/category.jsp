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
					<a href="home">Home</a>
					 / 
					<a href="home">Back</a>
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
			<td colspan="2"><c:forEach items="${projects}" var="project">
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
						Project has <font color="sky blue"><b>${project.currentMoney/100}</b></font> dollars
					</p>
					<p>
						Days to go - <font color="sky blue"><b>${project.daysLeft}</b></font>
					</p>
					<p>
						Description: <em>${project.description}</em>
					</p>
				</c:forEach></td>
		</tr>
	</table>

</body>
</html>