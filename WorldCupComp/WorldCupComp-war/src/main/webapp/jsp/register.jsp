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
						<li><a onclick="Javascript:verifySession();">Your Predictions</a></li>
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
							 Login Form
						-->
							<form:form method="post" action="/registerUser" modelAttribute="user">  
								<div class="row half">
									<div class="6u"><label for="userIdInput">Email: </label></div>
						      		<div class="6u"><form:input type="email" path="userId" id="userIdInput" placeholder="Enter email address" /></div>
						      		<div class="6u"><form:errors path="userId" /></div>	
								</div>
								
								<div class="row half">
									<div class="6u"><label for="passwordInput">Password: </label></div>
						      		<div class="6u"><form:password path="password" id="passwordInput" placeholder="Enter Password" /></div>						     
								</div> 	
										      
								<div class="row half">
									<div class="6u"><label for="password2Input">Confirm Password: </label></div>
						      		<div class="6u"><form:password path="password" id="password2Input" placeholder="Confirm Password" /></div>
								</div> 	
								
								<div class="row half">
									<div class="6u"><label for="nameInput">Name: </label></div>
						      		<div class="6u"><form:input path="name" id="nameInput" /></div>
								</div> 	
								
								<div class="row">
									<div class="12u">
										<ul class="actions">
											<li><input type="submit" class="button" value="Save" onclick="Javascript:return checkRegisterForm();" /></li>
										</ul>
									</div>
								</div>
						   </form:form>  
				</div>
			</section>			
	</body>  
</html>  
