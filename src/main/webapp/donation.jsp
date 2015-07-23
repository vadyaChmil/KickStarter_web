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
					<a href="project?id=${projectId}">Back</a>
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
			<td colspan="2" align="center">
				<h3><em>Write in or choose one of the variant</em></h3>
			</td>
		</tr>
	</table>
	<table width="80%" border="0" align="center">
		<tr>
			<th width="50%" align="left">
				<form name="form" method="get" action="donate">
					<p>Input name:</p>
						<label><input type="text" name="name" size="30"></label>
					<p>Input card number:</p>
						<label>
							<input type="text" name="card" size="30" maxlength="16"
								onkeypress='return event.charCode >= 48 && event.charCode <= 57' >
						</label>
					<p>Input money (cents):</p>
						<c:if test='${not empty money}'>
							<label><input type="text" name="money" size="30"  value="${money}" readonly></label>
						</c:if>
						<c:if test='${empty money}'>
							<label>
								<input type="text" name="money" size="30" 
									onkeypress='return event.charCode >= 48 && event.charCode <= 57' >
							</label>
						</c:if>
					<p>
						<input type="reset" name="reset" value="Reset">
						<input type="submit" name="submit" value="Send">
					</p>
				</form>
			</th>
			<th>
				<c:if test='${not empty donations}'>
					<c:forEach var="donation" items="${donations}">
						<a href="donation?id=${projectId}&money=${donation.key}">
							<em>${donation.key/100} dollars</em></a>
						<p>
							<em>${donation.value}</em>
						</p>
					</c:forEach>
				</c:if>
			</th>
		</tr>
	</table>

</body>
</html>