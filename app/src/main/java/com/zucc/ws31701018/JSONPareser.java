package com.zucc.ws31701018;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONPareser {
    static InputStream is = null;
    static JSONObject sReturnJsonObject = null;
    static String sRawJsonString = "";
    public JSONPareser(){}

    public JSONObject getJSONFromUrl(String url) throws MalformedURLException {
        try{
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int res = conn.getResponseCode();
            if (res == HttpURLConnection.HTTP_OK){
                is = conn.getInputStream();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is,"iso-8859-1"),8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            is.close();
            sRawJsonString = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sReturnJsonObject = new JSONObject(sRawJsonString);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return sReturnJsonObject;
    }
}
