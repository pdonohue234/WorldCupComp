
//$('#predictions').click(function(){ verifySession(); return false; });

//check session is alive before predictions screen
function verifySession() {
	//1. Check does the user have a valid session
	var validSession = false;
	
	//2. If not then redirect to login/register page
	if(!validSession) {
		window.location.href = "/login";
	}
}

//Determine who the winning team is based on users inputted scores
function calculateWinningTeam(row, team1, team2) {
	
	if(document.getElementById('teamOneScore'+row) != 'undefined' && document.getElementById('teamOneScore'+row).value != '') {
		var team1Score = document.getElementById('teamOneScore'+row).value;

		if(isNaN(team1Score)) {
			alert('Error: Must enter a number for team score one.');
			document.getElementById('teamOneScore'+row).focus();
			return false;
		}
		else {			
			if(document.getElementById('teamTwoScore'+row) != 'undefined' && document.getElementById('teamTwoScore'+row).value != '') {
				var team2Score = document.getElementById('teamTwoScore'+row).value;
	
				if(isNaN(team2Score)) {
					alert('Error: Must enter a number for team score two.');
					document.getElementById('teamTwoScore'+row).focus();
					return false;
				}
				else {
					var result = team1Score.localeCompare(team2Score);
		
					if( result === 0 ) {
						document.getElementById('winningTeam'+row).value = "Draw";
					}
					else if( result > 0 ) {
						document.getElementById('winningTeam'+row).value = team1;
					}
					else if( result < 0 ) {
						document.getElementById('winningTeam'+row).value = team2;
					}
				}
			}
		}
		return true;
	}
	
	//var test = document.getElementById("predictionsTable").rows[1].cells[5].innerHTML;
}

function checkLoginForm() {
	
  var username = document.getElementById('userIdInput');
  var password = document.getElementById('passwordInput');
  
  if(username.value == false) {
    alert("Error: Email cannot be blank!");
    username.focus();
    return false;
  }

  if(password.value == false) {
	  alert("Error: Password cannot be blank!");
	  password.focus();
	  return false;
  } 
  
  return true;
}


function checkRegisterForm() {

	var username = document.getElementById('userIdInput');
	var password = document.getElementById('passwordInput');
	var password2 = document.getElementById('password2Input');

	
	if(username.value == false) {
		alert("Error: Email cannot be blank!");
		username.focus();
		return false;
	}	
	
	if(password.value == false) {
		alert("Error: Password cannot be blank!");
		password.focus();
		return false;
	}
	else {
	    if(password.value.length < 6) {
	      alert("Error: Password must contain at least six characters!");
	      password.focus();
	      return false;
	    }
	    if(password.value == username.value) {
	      alert("Error: Password must be different from Email!");
	      password.focus();
	      return false;
	    }
	    re = /[0-9]/;
	    if(!re.test(password.value)) {
	      alert("Error: password must contain at least one number (0-9)!");
	      password.focus();
	      return false;
	    }
	    re = /[a-z]/;
	    if(!re.test(password.value)) {
	      alert("Error: password must contain at least one lowercase letter (a-z)!");
	      password.focus();
	      return false;
	    }
	    re = /[A-Z]/;
	    if(!re.test(password.value)) {
	      alert("Error: password must contain at least one uppercase letter (A-Z)!");
	      password.focus();
	      return false;
	    }
	    
		if(password2.value == false) {
			alert("Error: Please confirm your password!");
			password.focus();
			return false;
		}
		else {
			if(password.value != password2.value) {
				alert("Error: Two Passwords entered are not the same!");
				password.focus();
				return false;
			}			
		}
	} 
	password2.value = "";
	return true;
}