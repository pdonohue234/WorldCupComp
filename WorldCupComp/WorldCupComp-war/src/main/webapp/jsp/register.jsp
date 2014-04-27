<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>  
	<head>  
		<title>World Cup Charity Competition | Registration Form</title>  
		<link type="text/css" rel="stylesheet" href="../css/main.css"/>
	</head>  

	<body>  
 		<center>  
		<b>World Cup Charity Competition | Registration Form </b>   
  		<div>  
	   	<form:form method="post" action="/registerUser" modelAttribute="user">  
			<label for="userIdInput">User Name: </label>
      		<form:input path="userId" id="userIdInput" /> 
			<br/>
			 
			<label for="passwordInput">Password: </label>
      		<form:input path="password" id="passwordInput" /> 
			<br/> 	
					      
			<label for="emailInput">Email: </label>
      		<form:input path="email" id="emailInput" /> 
			<br/> 	
			
			<label for="nameInput">Name: </label>
      		<form:input path="name" id="nameInput" /> 
			<br/> 	 
			
			<input type="submit" value="Save" />
	   </form:form>  
	  </div>  
	 </center>  
	</body>  
</html>  
