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
			<h1 id="logo"><a href="https://worldcuppredictioncomp.appspot.com/#">World Cup Prediction Competition</a></h1>
				
			<!-- Nav -->
			<nav id="nav">
				<ul>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#home">Home</a></li>
					<li><a href=\"javascript:verifySession();\">Your Predictions</a></li>
					<!--<li><a onclick="verifySession()">Your Predictions</a></li>-->
					<li><a href="https://worldcuppredictioncomp.appspot.com/#sponsors">Sponsors</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#charity">Laura Lynn Children's Hospice</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#rules">Rules</a></li>
				</ul>
			</nav>

		</header>
			
	    <!-- Predictions -->
		<section id="predictions" class="main style2 left dark fullscreen">
			<div class="content container big">
				<header>
					<h2>Predictions</h2>
					<h3>Your current score is: ${model.score}</h3>
				</header>
				<form:form method="post" action="/updatePredictions" modelAttribute="fixtureResultList">   
					<table id="predictionsTable">
						<tr>
							<td>Game</td>
							<td>Date/Time</td>
							<td>Round</td>
							<td>Location</td>
							<td>Team 1</td>
							<td>Score</td>
							<td>Team 2</td>
							<td>Score</td>
							<td>Result</td>
							<td>Played</td>
						</tr>
				        <c:forEach items="${model.fixtureResultList.fixtureResults}" var="fixture" varStatus="loop">
				            <tr>
				            	<form:hidden path="fixtureResults[${loop.index}].userId" value="${fixture.userId}"/>
				            	<form:hidden path="fixtureResults[${loop.index}].gameId" value="${fixture.gameId}"/>
				            	<form:hidden path="fixtureResults[${loop.index}].eventId" value="${fixture.eventId}"/>
				            	
				            	<td>${loop.index}</td>
				            	
				                <td>${fixture.gameDate}</td>
				                
				                <td>${fixture.round}</td>
				                
				                <td>${fixture.gameLocation}</td>
				                
				                <td>${fixture.teamOne}</td>
				                
				                <c:if test="${fixture.active eq true}">
				                	<td>
				                	<form:input type="number" path="fixtureResults[${loop.index}].teamOneScore" 
				                		min="0" max="20" size="2" pattern="[0-9]*"
				                		value="${fixture.teamOneScore}" id="teamOneScore${loop.index}" 
				                		onBlur="javascript:calculateWinningTeam('${loop.index}', '${fixture.teamOne}', '${fixture.teamTwo}')"/>
				                	</td>
				                </c:if>		
				                <c:if test="${fixture.active eq false}">
				                	<td>${fixture.teamOneScore}</td>
				                </c:if>	
				                				                                		
				                <td>${fixture.teamTwo}</td>
				                
				                <c:if test="${fixture.active eq true}">
				                	<td>
				                	<form:input type="number" path="fixtureResults[${loop.index}].teamTwoScore"  
				                		min="0" max="20" size="2" pattern="[0-9]*"
				                		value="${fixture.teamTwoScore}" id="teamTwoScore${loop.index}" 
				                		onBlur="javascript:calculateWinningTeam('${loop.index}', '${fixture.teamOne}', '${fixture.teamTwo}')"/>
				                	</td>
				                </c:if>		
				                <c:if test="${fixture.active eq false}">
				                	<td>${fixture.teamTwoScore}</td>
				                </c:if>		
				                
				                				                			                
				                <td>
				                <c:if test="${fixture.active eq true}">
					                <form:select id="winningTeam${loop.index}" path="fixtureResults[${loop.index}].winningTeam" >
					                   <form:option value="" label=""/>
								      
								       <c:if test="${fixture.winningTeam eq fixture.teamOne}">
								      		<form:option selected="selected" value="${fixture.teamOne}" label="${fixture.teamOne}"/> 
								       </c:if>	
								       <c:if test="${fixture.winningTeam ne fixture.teamOne}">
								       		<form:option value="${fixture.teamOne}" label="${fixture.teamOne}"/> 
									   </c:if>  
									   
									   
									    <c:if test="${fixture.winningTeam eq fixture.teamTwo}">
								      		<form:option selected="selected" value="${fixture.teamTwo}" label="${fixture.teamTwo}"/> 
								       </c:if>	
								       <c:if test="${fixture.winningTeam ne fixture.teamTwo}">
								       		<form:option value="${fixture.teamTwo}" label="${fixture.teamTwo}"/> 
									   </c:if> 
									   
									   
									   <c:if test="${fixture.winningTeam eq 'Draw'}">
								      		<form:option selected="selected" value="Draw" label="Draw"/> 
								       </c:if>	
								       <c:if test="${fixture.winningTeam ne 'Draw'}">
								       		<form:option value="Draw" label="Draw"/> 
									   </c:if>  								    
						             </form:select>
						        </c:if>		
				                <c:if test="${fixture.active eq false}">
				                	${fixture.winningTeam}
				                </c:if>	
				                </td>
				                
				                <td>${fixture.gamePlayed}</td>
				            </tr>
						</c:forEach>							
					</table>
					<center>
						<input type="submit" class="button" value="Submit" />
						<p><i>You can re-enter predictions as often as you like until the game actually starts</i></p>
					</center>	
				</form:form>  		
			</div>
			<center><a href="https://worldcuppredictioncomp.appspot.com/#sponsors" class="button style2 down">Next</a></center>
		</section>
	</body>  
</html>  