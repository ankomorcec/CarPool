package air.foi.ws;

/**
 * Created by antoniok on 29.8.2016..
 */
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncWS extends AsyncTask <WebServiceParams, Void, AsyncTaskInnerResults>{


    @Override
    protected AsyncTaskInnerResults doInBackground(WebServiceParams... webServiceParamses) {
        String url = "http://192.168.1.2:887/CarpoolWS/getAllUsers";
        HttpURLConnection c = null;

        AsyncTaskInnerResults asyncTaskInnerResult = new AsyncTaskInnerResults();

        asyncTaskInnerResult.wsResult = "";

        try {
            c = (HttpURLConnection)(new URL(url)).openConnection();
            c.setDoInput(true);
            c.setRequestProperty("charset", "utf-8");
            //reading web service response
            InputStream is = new BufferedInputStream(c.getInputStream());
            BufferedReader dr = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = dr.readLine()) != null)
            {
                Log.i("HTTP", "Status: " + line);//get place details here += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (c != null)
                c.disconnect();
        }

        return asyncTaskInnerResult;
    }
}

