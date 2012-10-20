package echo.recorder.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import echo.recorder.EchoActivity;

public class MakeJson {
	private static JSONObject json = new JSONObject();
	public MakeJson(File fileobject) {
		String s = "file not loaded wtf";
		
		try {
			byte[] b = getBytesFromFile(fileobject);
			s = Base64.encodeToString(b, 0);
			
			Pair<Double,Double> loc = EchoActivity.gps.getLoc();
			
			json.put("whisper","test");
			//Log.v("file", s);
			
			json.put("latitude", "" + loc.first.floatValue());
			json.put("longitude", "" + loc.second.floatValue());
			
			//Log.v("gps","" + loc.first.floatValue() + ", " + loc.second.floatValue());
		}
		catch (Exception e) {
		}
		try{
			getServerData();
		}
		catch (Exception e) {
			
		}
	}
	
	// Returns the contents of the file in a byte array.
	public static byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    // Get the size of the file
	    long length = file.length();

	    // You cannot create an array using a long type.
	    // It needs to be an int type.
	    // Before converting to an int type, check
	    // to ensure that file is not larger than Integer.MAX_VALUE.
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }

	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];

	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
	
	public void getServerData() throws JSONException, ClientProtocolException, IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        ResponseHandler <String> resonseHandler = new BasicResponseHandler();
        HttpPost postMethod = new HttpPost("http://guarded-earth-9510.herokuapp.com/upload_geoaudio/");
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        //Log.v("JSON being sent", json.toString());
        nameValuePairs.add(new BasicNameValuePair("jsonString", json.toString()));
        postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        String response = httpClient.execute(postMethod,resonseHandler);
        Log.v("beginresponse","");
        JSONObject jsonResponse = new JSONObject(response);
        
        Log.v("server response", jsonResponse.toString());
        Log.v("endresponse","");
        //Log.v("msg to server", json.toString());
   }
	
}