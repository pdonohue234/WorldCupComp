
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

		if(document.getElementById('teamTwoScore'+row) != 'undefined' && document.getElementById('teamTwoScore'+row).value != '') {
			var team2Score = document.getElementById('teamTwoScore'+row).value;

			var result = team1Score.localeCompare(team2Score);

			if( result === 0 ) {
				document.getElementById('winningTeam'+row).value = "Draw";
			}
			else if( result > 0 ) {
				document.getElementById('winningTeam'+row).value = team1;
			}
			else if( result < 0 ) {
				document.getElementById('winningTeam'+row).value = team3;
			}
		}
	}
	
	//var test = document.getElementById("predictionsTable").rows[1].cells[5].innerHTML;
}
/*
function checkLoginForm() {
    var username = $('#userIdInput'),
        password = $('#passwordInput');

    $('#login').submit(function(ev){
        // check if all fields is not empty
        if(username.val() === '' || password.val() === '') {
            ev.preventDefault(); // prevent form submit
            alert('Both fields are required.'); //alert message
            //check if password and confirm password is equal
        } else {
            return true; // submit form if validation has passed.
        }
    });
}
*/

function checkLoginForm() {
	
  var username = document.getElementById('userIdInput');
  var password = document.getElementById('passwordInput');
  /*alert("User: "+ username);
  alert("Password: "+ password);
  alert("User: "+ document.getElementById("userIdInput"));
  alert("Password: "+ document.getElementById("passwordInput"));
  alert("User: "+ document.getElementById("user"));
  alert("Password: "+ document.getElementById("password"));*/
  if(username == false) {
    alert("Error: Email cannot be blank!");
    username.focus();
    return false;
  }

  if(password = true) {
    if(password.length < 6) {
      alert("Error: Password must contain at least six characters!");
      password.focus();
      return false;
    }
  } else {
    alert("Error: Password cannot be blank!");
    password.focus();
    return false;
  }
  return true;
}


function checkForm(form)
{
  if(form.userId.value == "") {
    alert("Error: Username cannot be blank!");
    form.userId.focus();
    return false;
  }

  if(form.password.value != "") {
    if(form.password.value.length < 6) {
      alert("Error: Password must contain at least six characters!");
      form.password.focus();
      return false;
    }
    if(form.pwd1.value == form.username.value) {
      alert("Error: Password must be different from Username!");
      form.pwd1.focus();
      return false;
    }
    re = /[0-9]/;
    if(!re.test(form.pwd1.value)) {
      alert("Error: password must contain at least one number (0-9)!");
      form.pwd1.focus();
      return false;
    }
    re = /[a-z]/;
    if(!re.test(form.pwd1.value)) {
      alert("Error: password must contain at least one lowercase letter (a-z)!");
      form.pwd1.focus();
      return false;
    }
    re = /[A-Z]/;
    if(!re.test(form.pwd1.value)) {
      alert("Error: password must contain at least one uppercase letter (A-Z)!");
      form.pwd1.focus();
      return false;
    }
  } else {
    alert("Error: Please check that you've entered and confirmed your password!");
    form.pwd1.focus();
    return false;
  }

  alert("You entered a valid password: " + form.pwd1.value);
  return true;
}