//check session is alive before predictions screen
function verifySession() {
	//1. Check does the user have a valid session
	var validSession = false;
	
	//2. If not then redirect to login/register page
	if(!validSession) {
		window.location.href = "/login";
	}
}