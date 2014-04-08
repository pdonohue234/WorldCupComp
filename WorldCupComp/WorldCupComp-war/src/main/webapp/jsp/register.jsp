<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>  
	<head>  
		<title>World Cup Charity Competition | Registration Form</title>  
		<style>  
			body {  
			 font-size: 20px;  
			 color: teal;  
			 font-family: Calibri;  
			}  
			  
			td {  
			 font-size: 15px;  
			 color: black;  
			 width: 100px;  
			 height: 22px;  
			 text-align: left;  
			}  
			  
			.heading {  
			 font-size: 18px;  
			 color: white;  
			 font: bold;  
			 background-color: orange;  
			 border: thick;  
			}  
		</style>  
	</head>  

	<body>  
 		<center>  
		<b>World Cup Charity Competition | Registration Form </b>   
  		<div>  
	   	<form:form method="post" action="/register" modelAttribute="user">  
	    	<table>  
			     <tr>  
			      <td>User Name :</td>  
			      <td><form:input path="userId" /></td>  
			     </tr> 
			     <tr>  
			      <td>Password :</td>  
			      <td><form:input path="password" /></td>  
			     </tr> 			      
			     <tr>  
			      <td>Email :</td>  
			      <td><form:input path="email" /></td>  
			     </tr>  
			     <tr>  
			      <td>Name :</td>  
			      <td><form:input path="name" /></td>  
			     </tr>  
			     <tr>  
			      <td>Name4 :</td>  
			      <td><form:input path="name4" /></td>  
			     </tr> 			     
			     <tr>  
			      <td> </td>  
			      <td><input type="submit" value="Save" /></td>  
			     </tr>  
		    </table>  
	   </form:form>  
	  </div>  
	 </center>  
	</body>  
</html>  