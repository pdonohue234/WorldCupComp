<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>  
	<head>  
		<title>World Cup Predictions Competition</title> 
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />		
		<link type="text/css" rel="stylesheet" href="../css/skel-noscript.css"/>
		<link type="text/css" rel="stylesheet" href="../css/style.css"/>		
		<script src="../js/functions.js" type="text/javascript"></script>
		<script src="../js/jquery.min.js" type="text/javascript"></script>
		<script src="../js/jquery.poptrox.min.js" type="text/javascript"></script>
		<script src="../js/skel.min.js" type="text/javascript"></script>
		<script src="../js/init.js" type="text/javascript"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel-noscript.css" />
			<link rel="stylesheet" href="css/style.css" />
		</noscript>	
	</head>  

	<body>  
		<!-- Header -->
			<header id="header">
				<!-- Nav -->
				<nav id="nav">
					<ul>

						<li><a href="https://worldcuppredictioncomp.appspot.com/#home">Home</a></li>
						<li><a onclick="verifySession()">Your Predictions</a></li>
						<li><a href="https://worldcuppredictioncomp.appspot.com/#sponsors">Sponsors</a></li>
						<li><a href="https://worldcuppredictioncomp.appspot.com/#charity">Laura Lynn Children's Hospice</a></li>
						<li><a href="https://worldcuppredictioncomp.appspot.com/#rules">Competition Rules T&Cs</a></li>
					</ul>
				</nav>
			</header>
				
	 		<section id="register" class="main style2 left dark fullscreen">
				<div class="content container" align="center">
					<header>
						<h2>Please Register</h2>
						<p><a href="/login">Click here if you already have an account</a></p>
					</header>
						<!--
							 Register Form
						-->
							<form:form method="post" action="/registerUser" modelAttribute="user">  
								<table style="max-width:500px;">
									<tr>
										<td style="padding:1px 2px;">
											<label for="userIdInput" style="text-align:right">Email: </label>
										</td>
										<td style="padding:1px 2px;">
							      			<form:input type="email" path="userId" id="userIdInput" placeholder="Enter email address" />
							      			<form:errors path="userId" />
										</td>
									</tr>
									<tr>
										<td style="padding:1px 2px">
											<label for="passwordInput" style="text-align:right">Password: </label>
										</td>
										<td style="padding:1px 2px">
						      				<form:password path="password" id="passwordInput" placeholder="Enter Password" />					     
										</td>
									</tr>
									<tr>
										<td style="padding:1px 2px">
											<label for="password2Input" style="text-align:right">Confirm Password: </label>
						      			</td>
						      			<td>	
						      				<form:password path="password" id="password2Input" placeholder="Confirm Password" /> 	
										</td>
									</tr>
									<tr>
										<td style="padding:1px 2px">
											<label for="nameInput" style="text-align:right">Name: </label>
										</td>
										<td>
								      		<form:input path="name" id="nameInput" placeholder="Your Name" />
							      		</td>
						      		</tr>
						      		<tr>
										<td colspan="2" style="text-align:center; padding:2px 2px">
											<input type="submit" class="button style2 login" value="Register" onclick="Javascript:return checkRegisterForm();" />
										</td>
									</tr>
								</table>
						   </form:form>  
				</div>
			</section>			
	</body>  
</html>  
