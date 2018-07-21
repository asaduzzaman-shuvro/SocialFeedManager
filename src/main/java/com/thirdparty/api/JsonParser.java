package com.thirdparty.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonParser {
    public static JSONObject parseJSONFile(String filename) throws IOException, JSONException {
        InputStream in = null;
        JSONObject json = null;
        try {
            ClassLoader classLoader = JsonParser.class.getClassLoader();
            in = classLoader.getResourceAsStream(filename);

            if (in != null) {
                BufferedReader streamReader = new BufferedReader(
                        new InputStreamReader(in, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                json = new JSONObject(responseStrBuilder.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return json;
    }
}
