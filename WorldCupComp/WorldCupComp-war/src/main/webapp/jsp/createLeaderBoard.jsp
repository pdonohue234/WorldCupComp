<%--
Web Site: 
Product : Crowds - World Cup 2014 Application
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<script src="../js/oneSimpleTablePaging-1.0.js"></script>		
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
					<li><a href="javascript:verifySession();">Your Predictions</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#sponsors">Sponsors</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#charity">Laura Lynn</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#rules">Rules</a></li>
				</ul>
			</nav>

		</header>		
			
	    <!-- Predictions -->
		<section id="leaderBoardTable" class="main style2 left dark fullscreen">
			<div class="content container small" align="center">	
				<header>
					<h2>Full Leaderboard</h2>				
					<br>
				</header>	
				
				<script>
					$(document).ready(function(){
				       $('.leaderBoardTable').oneSimpleTablePagination({rowsPerPage: 12});
				   });
				</script>
				
				<style>
					
					table,th,td{
						border-style:solid;
						border-color: black;
						border-width: 0px 0px 1px 0px;
						text-align:center;
						background-color: rgba(192, 192, 192, 0.85);
					} 
					
				</style>
						 
					<table class="leaderBoardTable" id="leaderBoardTable" style="width:75%">
						<col width="60px">
						<col width="140px">
						<col width="70px">

						<thead>
							<tr>
								<th><label>#</label></th>
								<th><label>Name</label></th>
								<th><label>Score</label></th>
							</tr>
						</thead>
						
						<tbody>
					        <c:forEach items="${model.userResultList.userResults}" var="user" varStatus="loop">
					            <tr>        	
					            	<td><label>${loop.index + 1}</label></td>
	
					            	<td><label>${user.userName}</label></td>		              			        
	
					                <td><label>${user.score}</label></td>	
					            </tr>
							</c:forEach>	
						</tbody>					
					</table>
			</div>
		</section>
	</body>  
</html>  