package com.casaa.jsonparser;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private InputStream i=null;

    JsonParser(String lien){

        try
        {

            URL li = new URL(lien);//recupurer le lien

            HttpURLConnection cnx =(HttpURLConnection) li.openConnection();

            cnx.setRequestMethod("GET");

            i=cnx.getInputStream();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      public List<Imges> Read_images()
      {
          List<Imges> li=new ArrayList<Imges>();
          JsonReader reader=new JsonReader(new InputStreamReader(i));
          String title=null,image=null;
          try {
              reader.beginObject();
              while (reader.hasNext()){

                  String tempo=reader.nextName();

                  if(tempo.equals("items")){

                      reader.beginArray();

                      while (reader.hasNext()){

                          reader.beginObject();

                          while (reader.hasNext()) {

                              tempo = reader.nextName();

                              if (tempo.equals("title")) {

                                  title = reader.nextString();
                              } else if (tempo.equals("media")) {
                                  reader.beginObject();
                                  while (reader.hasNext()){
                                      tempo= reader.nextName();
                                      if(tempo.equals("m")){
                                          image = reader.nextString();
                                      }else
                                      {
                                          reader.skipValue();
                                      }
                                  }
                                 reader.endObject();
                              } else {
                                  reader.skipValue();
                              }
                          }

                          li.add(new Imges(title,image));

                          reader.endObject();

                      }
                      reader.endArray();
                  }else{
                      reader.skipValue();
                  }
              }
              reader.endObject();
              reader.close();

          } catch (IOException e) {
              e.printStackTrace();
          }
          return li;
      }






}
