package com.casaa.jsonparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ascTasck().execute("");

    }

    class ascTasck extends AsyncTask<String ,Void , List<Imges>> {


        @Override
        protected List<Imges> doInBackground(String... strings) {
            return new JsonParser("https://api.flickr.com/services/feeds/photos_public.gne?tags=car&format=json&nojsoncallback=?").Read_images();
        }

        @Override
        protected void onPostExecute(List<Imges> Images) {

            CustomAdapter cu = new CustomAdapter(Images);
            RecyclerView arc = (RecyclerView) findViewById(R.id.arc);
            arc.setAdapter(cu);

        }
    }



}