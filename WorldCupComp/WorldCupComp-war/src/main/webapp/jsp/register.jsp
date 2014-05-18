<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 

<html>  
	<head>  
		<title>World Cup Charity Competition | Registration Form</title>  
		
		<link type="text/css" rel="stylesheet" href="../css/skel-noscript.css"/>
		<link type="text/css" rel="stylesheet" href="../css/style.css"/>
		
		<script src="../js/functions.js" type="text/javascript"></script>
		<script src="../js/jquery.min.js" type="text/javascript"></script>
		<script src="../js/jquery.poptrox.min.js" type="text/javascript"></script>
		<script src="../js/skel.min.js" type="text/javascript"></script>
		<script src="../js/init.js" type="text/javascript"></script>
	
	</head>  

	<body>  
		<!-- Header -->
			<header id="header">

				<!-- Logo -->
					<h1 id="logo"><a href="#">The World Cup Prediction Competition</a></h1>
				
				<!-- Nav -->
					<nav id="nav">
						<ul>

							<li><a href="https://worldcupcomp2014.appspot.com/#home">Home</a></li>
							<li><a onclick="verifySession()">Your Predictions</a></li>
							<li><a href="https://worldcupcomp2014.appspot.com/#sponsors">Sponsors</a></li>
							<li><a href="https://worldcupcomp2014.appspot.com/#charity">Laura Lynn Children's Hospice</a></li>
							<li><a href="https://worldcupcomp2014.appspot.com/#rules">Competition Rules T&Cs</a></li>

						</ul>
					</nav>
			</header>
			
			
	 		<section id="register" class="main style3 secondary">
				<div class="content container">
					<header>
						<h2>Please sign in</h2>
						<p><a href="/login">Click here if you already have an account</a></p>
					</header>
					<div class="box container small">
					
						<!--
							 Login Form
						-->
							<form:form method="post" action="/registerUser" modelAttribute="user">  
								<div class="row half">
									<div class="6u"><label for="userIdInput">User Name: </label></div>
						      		<div class="6u"><form:input path="userId" id="userIdInput" /></div>
								</div>
								
								<div class="row half">
									<div class="6u"><label for="passwordInput">Password: </label></div>
						      		<div class="6u"><form:input path="password" id="passwordInput" /></div>
								</div> 	
										      
								<div class="row half">
									<div class="6u"><label for="emailInput">Email: </label></div>
						      		<div class="6u"><form:input path="email" id="emailInput" /></div>
								</div> 	
								
								<div class="row half">
									<div class="6u"><label for="nameInput">Name: </label></div>
						      		<div class="6u"><form:input path="name" id="nameInput" /></div>
								</div> 	
								<div class="row">
									<div class="12u">
										<ul class="actions">
											<li><input type="submit" class="button" value="Save" /></li>
										</ul>
									</div>
								</div>
						   </form:form>  

					</div>
				</div>
			</section>			
	</body>  
</html>  
