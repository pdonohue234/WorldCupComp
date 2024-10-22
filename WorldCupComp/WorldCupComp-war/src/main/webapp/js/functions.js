
//check session is alive before predictions screen
function verifySession() {
	
	//1. Check does the user have a valid session
	var validSession = false;
	
	if(supports_html5_storage() === true ) {			
		if (localStorage.chkbx && localStorage.chkbx != '' && localStorage.userId != undefined && localStorage.password != undefined) {

			//Call login directly and redirect to predictions page
			/*
			 * Commented this out because although it loads the page, none of the links in header work wwithout refreshing after you click them
			$.post( "/predictions", { userId: localStorage.userId, password: localStorage.password }, 
					function( data ) {
						window.document.open();
						window.document.write(data);
						window.document.close();
						//window.location.reload();
					}, "html" );		
			*/
			validSession = true;
			
			var form = document.createElement("form");
			form.action = "https://worldcuppredictioncomp.appspot.com/predictions";
			form.method = "POST";
			  
			var usernode = document.createElement("input");
			usernode.name  = "userId";
			usernode.value = localStorage.userId;
			form.appendChild(usernode.cloneNode());
			    
			var passnode = document.createElement("input");
			passnode.name  = "password";
			passnode.value = localStorage.password;
			form.appendChild(passnode.cloneNode());
			
			// To be sent, the form needs to be attached to the main document.
			form.style.display = "none";
			document.body.appendChild(form);
			  
			form.submit();
			  
			// But once the form is sent, it's useless to keep it.
			document.body.removeChild(form);
		}
	}

	//Else If not then redirect to login/register page
	if(!validSession) {
		window.location.href = "/login";
	}
}

//check session is alive before predictions screen
function miniGroupTable(user, groupName) {		
	var form = document.createElement("form");
	form.action = "https://worldcuppredictioncomp.appspot.com/miniGroupTable";
	form.method = "POST";
			  
	var usernode = document.createElement("input");
	usernode.name  = "userId";
	usernode.value = user;
	form.appendChild(usernode.cloneNode());
			    
	var privateCompNamenode = document.createElement("input");
	privateCompNamenode.name  = "privateCompName";
	privateCompNamenode.value = groupName;
	form.appendChild(privateCompNamenode.cloneNode());
			
	// To be sent, the form needs to be attached to the main document.
	form.style.display = "none";
	document.body.appendChild(form);
	
	form.submit();
			  
	// But once the form is sent, it's useless to keep it.
	document.body.removeChild(form);
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
  var userIdClientError = document.getElementById('userIdClientError');
  var passwordClientError = document.getElementById('passwordClientError');  
  
  if(username.value == false) {
    username.setCustomValidity("Email is required!");
    username.validity.valid = false;
    userIdClientError.innerHTML = "Email is required!";
    username.focus();
    return false;
  }
  else {
	  username.validity.valid = true;	  
	  username.setCustomValidity("");
	  userIdClientError.innerHTML = "";
  }

  if(password.value == false) {
	  password.setCustomValidity("Password is required!");
	  password.validity.valid = false;
	  passwordClientError.innerHTML = "Password is required!";
	  password.focus();
	  return false;
  } 
  else {
	  password.validity.valid = true;
	  password.setCustomValidity("");
	  passwordClientError.innerHTML = "";	  
  }  
  
  return true;
}


function checkRegisterForm() {

	var username = document.getElementById('userIdInput');
	var password = document.getElementById('passwordInput');
	var password2 = document.getElementById('password2Input');
	var tx = document.getElementById('txInput');	
	
	var userIdClientError = document.getElementById('userIdClientError');
	var passwordClientError = document.getElementById('passwordClientError');  

	
	if(username.value == false) {
	    username.setCustomValidity("Email is required!");
	    username.validity.valid = false;
	    userIdClientError.innerHTML = "Email is required!";
	    username.focus();
		return false;
	}	
	else {
		username.validity.valid = true;	  
		username.setCustomValidity("");	
		userIdClientError.innerHTML = "";
	}
	
	if(password.value == false) {
		password.setCustomValidity("Password is required!");
		password.validity.valid = false;
		passwordClientError.innerHTML = "Password is required!";
		password.focus();
		return false;
	}
	else {
	    if(password.value.length < 6) {
	      password.setCustomValidity("Password must contain at least 6 chars!");
	      password.validity.valid = false;	   
	      passwordClientError.innerHTML = "Password must contain at least 6 chars!";
	      password.focus();
	      return false;
	    }
	    if(password.value == username.value) {
	      password.setCustomValidity("Password must be different from Email!");
	      password.validity.valid = false;	   
	      passwordClientError.innerHTML = "Password must be different from Email!";
	      password.focus();
	      return false;
	    }
	    re = /[0-9]/;
	    if(!re.test(password.value)) {
	      password.setCustomValidity("Password must contain at least one number (0-9)!");
	      password.validity.valid = false;	  
	      passwordClientError.innerHTML = "Password must contain at least one number (0-9)!";
	      password.focus();
	      return false;
	    }
	    /**
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
	    */
		if(password2.value == false) {
			password.setCustomValidity("Please confirm your password!");
			password.validity.valid = false;
			passwordClientError.innerHTML = "Please confirm your password!";
			password.focus();
			return false;
		}
		else {
			if(password.value != password2.value) {
				password.setCustomValidity("Two Passwords entered are not the same!");
				password.validity.valid = false;	
				passwordClientError.innerHTML = "Two Passwords entered are not the same!";
				password.focus();
				return false;
			}			
		}
		password.validity.valid = true;
		password.setCustomValidity("");
		passwordClientError.innerHTML = "";
	} 

	if(tx.value == false) {
		paymentClientError.innerHTML = "Please donate before registering!";
		return false;
	}	
	else {
		paymentClientError.innerHTML = "";
	}
	
/*	if(gup('auth') != undefined  && gup('auth') != null) {
		if(gup('transaction=') != "paid") {
		    paymentClientError.innerHTML = "Please make a donation before registering!";
			return false;
		}
		else {
			paymentClientError.innerHTML = "";
		}
	}
	else {
	    paymentClientError.innerHTML = "Please make a donation before registering!";
		return false;
	}*/
	return true;
}

function gup( name ) {
  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
  var regexS = "[\\?&]"+name+"=([^&#]*)";
  var regex = new RegExp( regexS );
  var results = regex.exec( window.location.href );
  if( results == null )
    return null;
  else
    return results[1];
}


function supports_html5_storage() {
	try {
		return 'localStorage' in window && window['localStorage'] !== null;
	} catch (e) {
		alert('returning false');
		return false;
	}
}

function toggle_visibility(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
 }


function logout() {
	if(supports_html5_storage() === true ) {
    	localStorage.userId = '';
        localStorage.password = '';
        localStorage.chkbx = '';
    }
    window.location.href = "https://worldcuppredictioncomp.appspot.com/#home";
}

function updateFailMessage(_msg) {
    displayMessage('failMessage', _msg)
}
  
function updateSuccessMessage(_msg) {
	displayMessage('successMessage', _msg);
}
  
function displayMessage(_id, _msg) {
	window.scrollTo(0,0);
	document.getElementById(_id).innerHTML = _msg;
	document.getElementById(_id).style.display = "block";
}

function removeMessages() {
    document.getElementById('failMessage').style.display = "none";
    document.getElementById('successMessage').style.display = "none";
}
