package com.d2dautowash.smsaction;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClientGetCall extends AsyncTask {

    String url;
    String queryParams;
    RestClientGetCall(String url, String queryParams){
        this.url = url;
        this.queryParams = queryParams;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        HttpURLConnection urlConnection = null;
        String result = null;
        try {

            String inputLine;
            URL URL = new URL(url+"?"+ queryParams);
            urlConnection = (HttpURLConnection) URL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(30000);
            urlConnection.setConnectTimeout(30000);
            urlConnection.connect();

            InputStreamReader streamReader = new
                    InputStreamReader(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result;
    }
}
