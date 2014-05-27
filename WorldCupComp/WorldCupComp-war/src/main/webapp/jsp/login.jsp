<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <script src="https://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
        
        <script>
            $(function() {
 				if(supports_html5_storage() === true ) {
	 
	                $('#remember_me').click(function() {
	                    if ($('#remember_me').is(':checked')) {
	                        // save userId and password
	                        if($('#userIdInput').val() != undefined && $('#passwordInput').val() != undefined ) {
		                        localStorage.userId = $('#userIdInput').val();
		                        localStorage.password = $('#passwordInput').val();
		                        localStorage.chkbx = $('#remember_me').val();		                        
	                       	}
	                    } else {
	                        localStorage.userId = '';
	                        localStorage.password = '';
	                        localStorage.chkbx = '';
	                    }
	                });
	           	}
            });
 
        </script>		
	</head>  

	<body>  
		<!-- Header -->
		<header id="header">	
			<!-- Nav -->
			<nav id="nav">
				<ul>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#home">Home</a></li>
					<li><a href="javascript:verifySession();">Your Predictions</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#sponsors">Sponsors</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#charity">Laura Lynn Children's Hospice</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#rules">Rules</a></li>
				</ul>
			</nav>

		</header>
			
			
	 		<%-- <section id="login" class="main style2 left dark fullscreen">
				<div class="content container" align="center">
					<header>
						<h2>Please sign in</h2>
						<p><a href="/register">Click here to Create New Account</a></p>
					</header>
						<!--
							 Login Form
						-->
							<form:form method="post" action="/predictions" modelAttribute="user">  
								<div class="row half">
									<div class="6u"><label for="userIdInput">Email: </label></div>
						      		<div class="6u"><form:input type="email" path="userId" id="userIdInput" placeholder="Enter email address" /></div>						      		
									<div class="6u"><form:errors path="userId" /></div>	
								</div>
								
								<div class="row half">
									<div class="6u"><label for="passwordInput">Password: </label></div>
						      		<div class="6u"><form:password path="password" id="passwordInput" placeholder="Enter Password" /></div>
						      		<div class="6u"><form:errors path="password" /></div>	
								</div> 	
										 	
								<div class="row">
									<div class="12u">
										<ul class="actions">
											<li><input type="submit" class="button" value="Login" onclick="Javascript:return checkLoginForm();" /></li>
										</ul>
									</div>
								</div>
						   </form:form>  
				</div>
			</section>			 --%>
			
			<section id="login" class="main style2 left dark fullscreen">
				<div class="content container" align="center">
					<header>
						<h2>Please Register/Login</h2>
					</header>
					<!--
						 Login Form
					-->
					<table>
						<tr>
							
							<td style="text-align:center; vertical-align:middle;">
								<div class="row">
									<div class="12u">						
										<a href="/register" class="button style2 login">New Registration</a>
										<p><i>...if you have not already created an account</i></p>
										<hr style="width:75%">									
									</div>
								</div>
							</td>
							
						</tr>
						<tr>						
							<td align="center">
							
								<form:form method="post" action="/predictions" modelAttribute="user">  
									<table style="max-width:500px;">
										<tr>
											<td style="padding:1px 2px;">
												<label for="userIdInput" style="text-align:right">Email: </label>
											</td>
											<td style="padding:1px 2px;">
									      		<form:input type="email" path="userId" id="userIdInput" placeholder="Enter email address" />						      		
												<br><form:errors path="userId" style="color:red"/>	
												<span id='userIdClientError' style="color:red"></span>
											</td>
										</tr>
										<tr>
											<td style="padding:1px 2px">
												<label for="passwordInput" style="text-align:right">Password: </label>
											</td>
											<td style="padding:1px 2px">
									      		<form:password path="password" id="passwordInput" placeholder="Enter a Password" />
									      		<br><form:errors path="password" style="color:red"/>
									      		<span id='errorClientError' style="color:red"></span>
											</td>
										</tr>
										<tr>
											<td colspan="2" style="text-align:center; padding:0px 2px">
												<label class="checkbox">
								                <input type="checkbox" value="remember-me" id="remember_me"/> Remember me
								                </label>
							                </td>
							            </tr>
										<tr>
											<td colspan="2" style="text-align:center; padding:2px 2px">
												<input type="submit" class="button style2 login" value="Login" onclick="Javascript:return checkLoginForm();" />
												<p><i>...if you already have an account</i></p>
											</td>
										</tr>
									</table>
								</form:form>
						  	</td>
						  </tr>
					</table>
				</div>
			</section>		
	</body>  
</html>  
