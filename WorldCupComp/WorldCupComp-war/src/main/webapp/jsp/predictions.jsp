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
					<li><a href="https://worldcuppredictioncomp.appspot.com/#charity">Laura Lynn Children's Hospice</a></li>
					<li><a href="https://worldcuppredictioncomp.appspot.com/#rules">Rules</a></li>
				</ul>
			</nav>

		</header>		
			
	    <!-- Predictions -->
		<section id="predictions" class="main style2 left dark fullscreen">
			<div class="content container big">
				<header>
					<div style="float:left;"><h2>Predictions</h2></div>				
					<div style="float:right;padding:12px 0px;word-wrap:break-word;"><h3>Score: ${model.score}</h3></div>
					<br>
				</header>	
				
				<style>
											
						/*   Styling for small screens. Split table rows into boxes   */
							/*	 http://css-tricks.com/responsive-data-tables/   */		
							@media 
							only screen and (max-width: 480px){
								
								/* Force table to not be like tables anymore */
								table, thead, tbody, th, td, tr { 
									display: block;
								}
								
								
								/* Hide table headers (but not display: none;, for accessibility) */
								thead tr { 
									position: absolute;
									top: -9999px;
									left: -9999px;
								}
								
								/* Zebra striping */
								tbody tr:nth-of-type(odd) { 
									background: rgba(240, 240, 240, 0.50); 
								}
													
								tr { border: 1px solid #ccc; }
								
								td { 
									/* Behave  like a "row" */
									border: none;
									border-bottom: 1px solid #eee; 
									position: relative;
									padding-left: 50%; 
								}
								
								td:before { 
									/* Now like a table header */
									position: absolute;
									/* Top/left values mimic padding */
									top: 6px;
									left: 6px;
									width: 45%; 
									padding-right: 10px; 
									white-space: nowrap;
								}
								
								/*
								Label the data
								*/
								td:nth-of-type(1):before { content: "Game"; }
								td:nth-of-type(2):before { content: "Round"; }
								td:nth-of-type(3):before { content: "Date/Time"; }
								td:nth-of-type(4):before { content: "Team 1"; }
								td:nth-of-type(5):before { content: "Score"; }
								td:nth-of-type(6):before { content: "Team 2"; }
								td:nth-of-type(7):before { content: "Score"; }
								td:nth-of-type(8):before { content: "Predicted Result"; }
								td:nth-of-type(9):before { content: "Actual Result"; }
							}
						
						table,th,td{
							border-style:solid;
							border-color: black;
							border-width: 0px 0px 1px 0px;
							text-align:center;
						} 
						
										
				</style>
				
				<script>
					$(document).ready(function(){
				       $('.predictionsTable').oneSimpleTablePagination({rowsPerPage: 8});
				   });  
				</script>
						
											
				<form:form method="post" action="/updatePredictions" modelAttribute="fixtureResultList">   
					<table class="predictionsTable" id="predictionsTable">
						<col width="60px">
						<col width="70px">
						<col width="135px">
						<col width="250px">
						<col width="70px">
						<col width="250px">
						<col width="70px">
						<col width="130px">
						<col width="80px">

						<thead>
						<tr>
							<th><label>Game</label></th>
							<th><label>Round</label></th>
							<th><label>Date <div style="font-size:0.8em">Ire</div></label></th>
							<th><label>Team 1</label></th>
							<th><label>Score</label></th>
							<th><label>Team 2</label></th>
							<th><label>Score</label></th>
							<th><label>Prediction</label></th>
							<th><label>Result</label></th>
						</tr>
						</thead>
						<tbody>
				        <c:forEach items="${model.fixtureResultList.fixtureResults}" var="fixture" varStatus="loop">
				            <tr>
				            	<form:hidden path="fixtureResults[${loop.index}].userId" value="${fixture.userId}"/>
				            	<form:hidden path="fixtureResults[${loop.index}].gameId" value="${fixture.gameId}"/>
				            	<form:hidden path="fixtureResults[${loop.index}].eventId" value="${fixture.eventId}"/>
				            	
				            	<td><label>${loop.index + 1}</label></td>

				            	<td><label>${fixture.round}</label></td>	

				                <td><label style="width:125px">${fixture.gameDateAsString}</label></td>			              			        

				                <td><label>${fixture.teamOneFullName}</label></td>	
				                
				                <c:if test="${fixture.active eq true}">
				                	<td>
				                	<form:input type="number" path="fixtureResults[${loop.index}].teamOneScore" 
				                		min="0" max="20" size="2" pattern="[0-9]*"
				                		value="${fixture.teamOneScore}" id="teamOneScore${loop.index}" 
				                		onBlur="javascript:return calculateWinningTeam('${loop.index}', '${fixture.teamOne}', '${fixture.teamTwo}')"/>
				                	</td>
				                </c:if>		
				                <c:if test="${fixture.active eq false}">
				                	<td><label>${fixture.teamOneScore}</label></td>
				                </c:if>	
				                				                                		
				                <td>${fixture.teamTwoFullName}</td>
				                
				                <c:if test="${fixture.active eq true}">
				                	<td>
				                	<form:input type="number" path="fixtureResults[${loop.index}].teamTwoScore"  
				                		min="0" max="20" size="2" pattern="[0-9]*"
				                		value="${fixture.teamTwoScore}" id="teamTwoScore${loop.index}" 
				                		onBlur="javascript:return calculateWinningTeam('${loop.index}', '${fixture.teamOne}', '${fixture.teamTwo}')"/>
				                	</td>
				                </c:if>		
				                <c:if test="${fixture.active eq false}">
				                	<td><label>${fixture.teamTwoScore}</label></td>
				                </c:if>		
				                
				                				                			               
				                <c:if test="${fixture.active eq true}">
				                	<td>
					                <form:select id="winningTeam${loop.index}" path="fixtureResults[${loop.index}].winningTeam" type="text">
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
						             </td>
						        </c:if>		
				                <c:if test="${fixture.active eq false}">
				                	<td><label>${fixture.winningTeam}</label></td>
				                </c:if>	
				                
				                <td><label>${fixture.result}</label></td>	
				               
				            </tr>
						</c:forEach>	
						</tbody>						
					</table>
					
					<div style="text-align: center">
						<input type="submit" class="button style2 login" value="Submit" />
					</div>
					
					<div class="Grid">
                    	<div class="Grid-cell-left" style="text-align:right;padding-right:20px;">
							<c:if test="${model.userLoggedIn ne undefined && model.userLoggedIn.name ne undefined && model.userLoggedIn.name ne ''}">
								<a href="#" onclick="javascript:logout();"><u>${model.userLoggedIn.name} log out</u></a>
							</c:if>	
							<c:if test="${model.userLoggedIn eq undefined || model.userLoggedIn.name eq undefined || model.userLoggedIn.name eq ''}">
								<a href="#" onclick="javascript:logout();"><u>${model.userLoggedIn.name} log out</u></a>
							</c:if>
						</div>
						<div class="Grid-cell-right" style="text-align:left;">
							<a href="#" onclick="toggle_visibility('hiddenText');"><u>Instructions Show/Hide</u></a>
                        </div>
					</div>
					<div id="hiddenText" class="Grid-cell-center" style="display:none; text-align:center; font-style:italic; font-size:0.8em;">
						<br>You can re-enter predictions as often as you before the game starts
						<br>2 points are awarded for each correct prediction of a team's score (max of 4pts available in each game)
						<br>1 point is awarded for a predicting the correct winning team (or draw)
						<br>You do not have to enter score predictions and can just predict the correct winning team (max 1pt available)
						<br>Predictions are for the 90 minutes of normal play. Extra time or penalties not included
					</div>
					
				</form:form>  		
			</div>
			<!--<center><a href="https://worldcuppredictioncomp.appspot.com/#sponsors" class="button style2 down">Next</a></center>-->
		</section>
	</body>  
</html>  