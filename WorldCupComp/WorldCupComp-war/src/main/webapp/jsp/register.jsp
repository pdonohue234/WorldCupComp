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
		
        <script src="https://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="../js/jquery.tools.min.js"></script>
        
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
            
            
				$("#privateCompNameInput").tooltip({
				 
				      // place tooltip on the right edge
				      position: "center right",
				 
				      // a little tweaking of the position
				      offset: [-2, 10],
				 
				      // use the built-in fadeIn/fadeOut effect
				      effect: "fade",
				 
				      // custom opacity setting
				      opacity: 0.7
				 
				      });  
				      
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
						<li><a href="https://worldcuppredictioncomp.appspot.com/#charity">Laura Lynn</a></li>
						<li><a href="https://worldcuppredictioncomp.appspot.com/#rules">Rules</a></li>
					</ul>
				</nav>
			</header>
				
			<section id="register" class="main style2 left dark fullscreen">
				<div class="content container" align="center">
					<header>
						<h2>Please Register</h2>
						<a href="/login">Click here if you already have an account</a>
					</header>
					
					<div style="height: 3px; background-color: black; text-align: center">
					  <span style="font-family: Calibri; color:white; background-color: black; position: relative; top: -0.5em;">
					    Step 1
					  </span>
					</div>
					
					<!-- Code for payments. Can move to wherever needed -->
					<!-- https://developer.paypal.com/docs/classic/paypal-payments-standard/integration-guide/formbasics/ -->
					<br>
					<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
						<input type="hidden" name="cmd" value="_s-xclick">
						<input type="hidden" name="hosted_button_id" value="88FCJXL7J55WC">
						<input type="hidden" name="cbt" value="Return to World Cup Predictions to Complete Registration">
						<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
						<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
					</form>
					
					<br>
					<div style="height: 3px; background-color: black; text-align: center">
					  <span style="font-family: Calibri; color:white; background-color: black; position: relative; top: -0.5em;">
					    Step 2
					  </span>
					</div>
					<br>
					
					<span id='paymentClientError' style="color:red"></span>

						<!--
							 Register Form
						-->
							<form:form method="post" action="/registerUser" modelAttribute="user">  
								<form:hidden path="transId" value="${tx}"  id="txInput"/>
								<table style="max-width:500px;">
									<tr>
										<td style="padding:1px 2px">
											<label for="nameInput" style="text-align:right">Name: </label>
										</td>
										<td>
								      		<form:input path="name" id="nameInput" placeholder="Your Name" />
							      		</td>
						      		</tr>
									<tr>
										<td style="padding:1px 2px;">
											<label for="userIdInput" style="text-align:right">Email: </label>
										</td>
										<td style="padding:1px 2px;">
							      			<form:input type="email" path="userId" id="userIdInput" placeholder="Enter email address" />
							      			<br><form:errors path="userId" style="color:red" />
							      			<span id='userIdClientError' style="color:red"></span>
										</td>
									</tr>
									<tr>
										<td style="padding:1px 2px">
											<label for="passwordInput" style="text-align:right">Password: </label>
										</td>
										<td style="padding:1px 2px">
						      				<form:password path="password" id="passwordInput" placeholder="Enter Password" />	
						      				<br><form:errors path="password" style="color:red" />
						      				<span id='passwordClientError' style="color:red"></span>				     
										</td>
									</tr>
									<tr>
										<td style="padding:1px 2px">
											<label for="password2Input" style="text-align:right">Confirm Password: </label> 
						      			</td>
						      			<td>	
						      				<input type="password" id="password2Input" placeholder="Confirm Password" /> 	
										</td>
									</tr>

									<tr>
										<td style="padding:1px 2px">
											<label for="privateCompNameInput" style="text-align:right">Mini Competition: </label>
										</td>
										<td>
								      		<form:input path="privateCompName" id="privateCompNameInput" placeholder="Optional" title="Create a new Mini Competition and pass on name for others to join. Or enter the name you have been given to join an existing competition" />
								      		<label class="checkbox">
								            	<form:checkbox id="newPrivateCompNameInput" path="newPrivateCompName" /> New
								            </label>
							      		</td>		
							      		<br><form:errors path="privateCompName" style="color:red" />				      			
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
											<input type="submit" class="button style2 login" value="Register" onclick="Javascript:return checkRegisterForm();" />
										</td>
									</tr>
								</table>
						   </form:form> 
				</div>
			</section>			
	</body>  
</html>  
