package com.thirdparty.api;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

/**
 * Created by atiqul on 7/17/2018.
 */
public class FacebookApi {

  public static JSONObject getFeeds() {
    String filename = "Facebook.json";
    JSONObject facebookFeeds = null;
    try {
      facebookFeeds = parseJSONFile(filename);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return facebookFeeds;

  }

  public static JSONObject parseJSONFile(String filename) throws IOException, JSONException {
    InputStream in = null;
    JSONObject json = null;
    try {
      ClassLoader classLoader = FacebookApi.class.getClassLoader();
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
