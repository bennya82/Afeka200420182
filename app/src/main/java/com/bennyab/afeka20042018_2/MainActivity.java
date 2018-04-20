package com.bennyab.afeka20042018_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    final String IMAGE_URL = "http://1.bp.blogspot.com/-kr469uEQAi8/VbzWuKyG3oI/AAAAAAAABVw/n2XrQP0HePA/s320/Android-eating-apple.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get File From Url And Save it
        AsyncTask<Void,Void,Bitmap> task = new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... voids) {
                Bitmap bmp = null;
                try {

                    //check if the image is on local cache?

                    //load from cache or load from url

                    //return bitmap


                    URL url = new URL(IMAGE_URL);
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    if (bmp != null){
                        String filename = "myfile";
                        FileOutputStream outputStream;
                        try {
                            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            outputStream.write(byteArray);
                            outputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception ex){
                    Log.e("download image",ex.getMessage());
                }
                return bmp;
            }


            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);

            }
        };
        task.execute();
    }
}
