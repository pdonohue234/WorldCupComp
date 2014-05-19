
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
				document.getElementById('winningTeam'+row).value = "4";
			}
			else if( result > 0 ) {
				document.getElementById('winningTeam'+row).value = "2";
			}
			else if( result < 0 ) {
				document.getElementById('winningTeam'+row).value = "3";
			}
		}
	}
	
	//var test = document.getElementById("predictionsTable").rows[1].cells[5].innerHTML;
}