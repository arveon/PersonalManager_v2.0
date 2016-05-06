package DB;

import java.util.ArrayList;

/**
 * Class used to check if the login attempt was successful or not
 * @author Aleksejs Loginovs
 *
 */
public class Login 
{
	/**
	 * Method that is used to check if the login attempt was successful or not
	 * @param login the username under which user is trying to log in
	 * @param password the password with which user is trying to log in
	 * @return true if the login attempt is successful, false if unsuccessful
	 */
	public static boolean login(String login, String password)
	{
		//initialise all the variables that are going to be used
		boolean result = false;
		ArrayList<String> response = new ArrayList<String>();
		String[] data = new String[2];
		
		//set the message to the server
		data[0] = "username="+login;
		data[1] = "&password="+password;
		//send and receive data to and from the server
		response = DBInteraction.communicate(data, DBInteraction.connect(ActionURLs.LOGIN_URL));
		
		//check the response and set the variable value accordingly
		if(response.get(0).equals("successfull"))
		{
			result = true;
		}
		else if(response.get(0).equals("failed"))
		{
			result = false;
		}
		
		return result;
	}
}
