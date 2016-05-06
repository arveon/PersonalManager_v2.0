package DB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Logics.Appointment;
import Logics.AppointmentList;

/**
 * Class handles saving the appointments to the database
 * @author arveon
 *
 */
public class SaveAppointmentsDB 
{
	/**
	 * Method used to send given appointment list and username to the server
	 * @param list list of of appointments to be saved
	 * @param user user to save appointments to
	 */
	public static void saveApps(AppointmentList list, String user)
	{
		try
		{
			JSONArray jsonlist = new JSONArray();
			if(list.getSize()!=0)
			{
				//create a json array out of appointments list
				for(int i = 0; i < list.getSize(); i++)
				{
					//create a json appointment object
					JSONObject jsonapp = new JSONObject();
					Appointment tempapp = list.getAppointmentAt(i);
					jsonapp.put("place", tempapp.getPlace());
					jsonapp.put("date", tempapp.getDate());
					jsonapp.put("time", tempapp.getTime());
					jsonapp.put("description", tempapp.getDescription());
					//add json appointment object to a json array
					jsonlist.put(jsonapp);
				}
				//create a string containing a json appointment array and the username
				String[] msg = {"jsonlist="+jsonlist.toString(), "&user="+user};
				
				//send the list to the server
				DBInteraction.communicate(msg, DBInteraction.connect(ActionURLs.UPLOAD_URL));
			}
			else
			{
				System.out.println("empty!");
			}
			
		}
		catch(JSONException e)
		{
			System.out.println("JSONEXCEPTION: " + e.getMessage());
		}
	}
	
	
}
