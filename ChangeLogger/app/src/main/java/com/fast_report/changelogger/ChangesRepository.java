package com.fast_report.changelogger;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class ChangesRepository implements RepositoryInterface {

    @Override
    public void Create() {
    }

    @Override
    public void Update() {

    }

    @Override
    public void Delete() {

    }

    public void Get() {
        Log.d("Connection", "Get called");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Connection", "Async started");
                URL changeLoggerEndpoint = null;
                try {
                    changeLoggerEndpoint = new URL("https://changelogger20180606030154.azurewebsites.net/api/Changes/1");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                // Create connection
                try {
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) changeLoggerEndpoint.openConnection();
                    Log.d("Connection", "Connection created");
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        Log.d("Response", responseBody.toString());
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject(); // Start processing the JSON object
                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName();
                            if (key.equals("name")) { // Check if desired key
                                // Fetch the value as a String
                                String value = jsonReader.nextString();

                                Log.d("Value", value);

                                break; // Break out of the loop
                            } else {
                                jsonReader.skipValue(); // Skip values of other keys
                            }
                        }
                        jsonReader.close();
                        //Log.d("Connection", "Closed");
                    } else {
                        Log.d("Connection", "Failed");
                    }
                    myConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
