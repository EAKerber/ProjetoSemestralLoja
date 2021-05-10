package com.example.projetosemestralloja;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader {
    private Handler handler = new Handler();
    public void loadImg(String urlPhoto, ImageView imageView){
        new Thread(){
            public void run(){
                Bitmap img = null;
                try {
                    URL url = new URL(urlPhoto);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    img = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Bitmap imgAux = img;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ImageView iv = imageView;
                        iv.setImageBitmap(imgAux);
                    }
                });
            }
        }.start();
    }
}
