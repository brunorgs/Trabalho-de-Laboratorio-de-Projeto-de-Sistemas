package com.example.corretordegabarito;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class mostrarfotos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mostrarfotos);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView1 = findViewById(R.id.imageView2);
        ImageView imageView2 = findViewById(R.id.imageView3);
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
        if (file.exists())
        {
            ExifInterface exifInterface = null;
            try {
                exifInterface = new ExifInterface(file.getAbsolutePath());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            int orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,1);
            Matrix matrix = new Matrix();
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            }
            else if(orientacao == ExifInterface.ORIENTATION_ROTATE_180)
            {
                matrix.postRotate(180);
            }
            else if(orientacao == ExifInterface.ORIENTATION_ROTATE_270)
            {
                matrix.postRotate(270);
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            imageView.setImageBitmap(bitmap1);
        }
        else
        {
            TextView textView = findViewById(R.id.textView4);
            textView.setText("");
        }
        File file1 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
        if (file1.exists())
        {
            ExifInterface exifInterface = null;
            try {
                exifInterface = new ExifInterface(file1.getAbsolutePath());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            int orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,1);
            Matrix matrix = new Matrix();
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            }
            else if(orientacao == ExifInterface.ORIENTATION_ROTATE_180)
            {
                matrix.postRotate(180);
            }
            else if(orientacao == ExifInterface.ORIENTATION_ROTATE_270)
            {
                matrix.postRotate(270);
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath());
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            imageView1.setImageBitmap(bitmap1);
        }
        else
        {
            TextView textView = findViewById(R.id.textView5);
            textView.setText("");
        }
        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
        if (file2.exists())
        {
            ExifInterface exifInterface = null;
            try {
                exifInterface = new ExifInterface(file2.getAbsolutePath());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            int orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,1);
            Matrix matrix = new Matrix();
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            }
            else if(orientacao == ExifInterface.ORIENTATION_ROTATE_180)
            {
                matrix.postRotate(180);
            }
            else if(orientacao == ExifInterface.ORIENTATION_ROTATE_270)
            {
                matrix.postRotate(270);
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            imageView2.setImageBitmap(bitmap1);
        }
        else
        {
            TextView textView = findViewById(R.id.textView6);
            textView.setText("");
        }
    }
}
