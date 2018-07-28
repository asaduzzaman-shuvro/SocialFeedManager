package com.cefalo.school.services;

import com.cefalo.school.model.Content;
import com.cefalo.school.model.ContentType;
import com.cefalo.school.model.FacebookFeedItem;
import com.cefalo.school.model.FeedItem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by atiqul on 7/28/2018.
 */
public class OutputGeneratorService {

  public static void outputToFile(List<FeedItem> items, File file){
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

        for (Content content : item.contents) {
          if(content.contentType.equals(ContentType.PICTURE)){
            writer.println("user upload a picture");
          }else if(content.contentType.equals(ContentType.VIDEO)){
            writer.println("user upload a video");
          }else if(content.contentType.equals(ContentType.URL)){
            writer.println("user shared a link");
          }else {
            writer.println("user post");
          }


          writer.println(df.format(item.publishedDate));
          String message = content.description;
          formatLines(message, writer);

          if(content.value != ""){
            message = content.value;
            writer.println();
            writer.println("Shared Item Info...");
            formatLines(message, writer);
          }
        }
        writer.println();

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
              String message = comment.contents.get(0).description;
              writer.println(df.format(comment.publishedDate));
              formatLines(message, writer);
            }
          }
          if(fbItem.comments.size()>0 || fbItem.reactions.size() > 0) {
            writer.println();
          }


        }catch (ClassCastException e){
          continue;
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

}
