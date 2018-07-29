package com.cefalo.school.services;

import com.cefalo.school.application.SocialFeedManager;
import com.cefalo.school.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by atiqul on 7/28/2018.
 */
public class SFMUtils {

  public static void outputToFile(List<FeedItem> items, File file, SocialFeedManager manager){
    PrintStream writer = null;

    try {
      writer = new PrintStream(file);
      DateFormat df = new SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a");
      df.setTimeZone(TimeZone.getDefault());
      for (FeedItem item : items) {
        writer.println();
        for(int row=0; row < 99; row++){
          writer.print("-");
        }
        writer.println("-");

        int i = 0;

        for (Content content : item.contents) {
          if(i == 0) {
            if (content.contentType.equals(ContentType.PICTURE)) {
              writer.println(String.format("%s upload a picture on %s", item.displayName, manager.getApplicationName(item.applicationIdentifier)));
            } else if (content.contentType.equals(ContentType.VIDEO)) {
              writer.println(String.format("%s upload a video on %s", item.displayName, manager.getApplicationName(item.applicationIdentifier)));
            } else if (content.contentType.equals(ContentType.URL)) {
              writer.println(String.format("%s shared a link on %s", item.displayName, manager.getApplicationName(item.applicationIdentifier)));
            } else {
              writer.println(String.format("%s post on %s", item.displayName, manager.getApplicationName(item.applicationIdentifier)));
            }

            writer.println(df.format(item.publishedDate));
            String message = content.description;
            formatLines(message, writer);
          }else {
            if (content.value != "") {
              String message = content.value;
              writer.println();
              writer.println();
              writer.println("Shared Item Info...");
              formatLines(message, writer);
              writer.println();
              writer.println();
            }
          }
          i++;
        }
        writer.println();

        // Facebook specific
        FacebookFeedItem fbItem = null;
        try {
          fbItem = (FacebookFeedItem) item;
          if(fbItem.reactions.size() > 0){
            for (Map.Entry<String, Integer> entry : fbItem.reactions.entrySet()) {
              String msg = String.format("%s: %s", entry.getKey(), entry.getValue());
              writer.print(String.format("|%-10s|", msg));
            }
          }
          if(fbItem.comments.size()>0){
            String msg = String.format("comments: %s", fbItem.comments.size());
            writer.print(String.format("|%-10s|", msg));
            writer.println();
            writer.println("comment details:");
            for (FeedItem comment : fbItem.comments) {

              String name = comment.displayName;
              formatLines(name, writer);
              writer.print( " " + df.format(comment.publishedDate));
              writer.println();

              String message = comment.contents.get(0).description;
              formatLines(message, writer);
              writer.println();
            }
          }
          if(fbItem.comments.size()>0 || fbItem.reactions.size() > 0) {
            writer.println();
          }


        }catch (ClassCastException e){

        }
        // Twitter specific
        TwitterFeedItem tweetItem = null;
        try {
          tweetItem = (TwitterFeedItem) item;

          String rtMsg = "retweet count "+tweetItem.retweetCount;
          writer.print(String.format("|%-10s|", rtMsg));

          String fvtCountMsg = "favorite count "+tweetItem.favoriteCount;
          writer.print(String.format("|%-10s|", fvtCountMsg));

          if(tweetItem.comments.size()>0){
            String msg = String.format("comments: %s", tweetItem.comments.size());
            writer.print(String.format("|%-10s|", msg));
            writer.println();
            writer.println("comment details:");
            for (Comment comment : tweetItem.comments) {
              String name = comment.commenterDisplayName;
              formatLines(name, writer);
              writer.print( " " + df.format(comment.publishDate));
              writer.println();

              String message = comment.text;
              formatLines(message, writer);
              writer.println();
            }
          }
            writer.println();

        }catch (ClassCastException e){

        }
      }
      writer.println();
      for(int row=0; row < 99; row++){
        writer.print("-");
      }
      writer.println("-");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if(writer != null){
        writer.flush();
        writer.close();
      }
    }
  }

  private static void formatLines(String message, PrintStream writer){
    BreakIterator boundary = BreakIterator.
        getLineInstance(Locale.ENGLISH);

    boundary.setText(message);
    int start = boundary.first();
    int end = boundary.next();
    int lineLength = 0;

    while (end != BreakIterator.DONE) {
      String word = message.substring(start,end);
      lineLength = lineLength + word.length();
      if (lineLength >= 100) {
        writer.println();
        lineLength = word.length();
      }
      writer.print(word);
      start = end;
      end = boundary.next();
    }
  }

  public static JSONObject parseJSONFile(String filename) throws IOException, JSONException {
    InputStream in = null;
    JSONObject json = null;
    try {
      ClassLoader classLoader = SFMUtils.class.getClassLoader();
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
