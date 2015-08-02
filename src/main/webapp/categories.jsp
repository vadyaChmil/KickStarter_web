<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
</head>

<body background="images/background.png">
	
	<table width="80%" height="150" border="0" align="center">
		<tr>
			<th align="left" width="20%">
				<h4>
					<a href="index.html">Home</a>
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
			<td colspan="2"><c:forEach items="${categories}" var="category">
					<ul type="circle">
						<li><h4>
								<a href="category?id=${category.id}">${category.name}</a>
							</h4></li>
					</ul>
				</c:forEach></td>
		</tr>
	</table>

</body>
</html>