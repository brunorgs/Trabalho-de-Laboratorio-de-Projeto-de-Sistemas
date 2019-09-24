package com.example.corretordegabarito;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File diretorio = contextWrapper.getDir("imageDir",Context.MODE_PRIVATE);
        File file = new File(diretorio,"Branco.png");
        if (file.exists())
        {
            file.delete();
        }
        file = new File(diretorio,"Oficial.png");
        if (file.exists())
        {
            file.delete();
        }
        file = new File(diretorio,"Aluno.png");
        if (file.exists())
        {
            file.delete();
        }
    }

    public void abrirCamera(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!= null)
        {
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,final Intent data)
    {
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
            alertdialog.setTitle("Tipo do gabarito a ser inserido");
            final RadioGroup radioGroup = new RadioGroup(this);
            RadioButton radioButton = new RadioButton(this);
            RadioButton radioButton1 = new RadioButton(this);
            RadioButton radioButton2 = new RadioButton(this);
            radioButton.setText("Branco");
            radioButton.setTextColor(Color.BLACK);
            radioButton1.setText("Oficial");
            radioButton1.setTextColor(Color.BLACK);
            radioButton2.setText("Aluno");
            radioButton2.setTextColor(Color.BLACK);
            radioGroup.addView(radioButton);
            radioGroup.addView(radioButton1);
            radioGroup.addView(radioButton2);
            ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(radioGroup);
            alertdialog.setView(linearLayout);
            alertdialog.setCancelable(false);
            alertdialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertdialog.setPositiveButton("INSERIR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
                    File diretorio = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE);
                    FileOutputStream fileOutputStream = null;
                    RadioButton radioButton3 = linearLayout.findViewById(radioGroup.getCheckedRadioButtonId());
                    String s = radioButton3.getText().toString();
                    if (s.equals("Branco"))
                    {
                        try {
                            File caminho = new File(diretorio,"Branco.png");
                            fileOutputStream = new FileOutputStream(caminho);
                            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                            Toast.makeText(MainActivity.this,"Gabarito do tipo branco inserido com sucesso!",Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        finally {
                            try {
                                fileOutputStream.close();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(s.equals("Oficial"))
                    {
                        try {
                            File caminho = new File(diretorio,"Oficial.png");
                            fileOutputStream = new FileOutputStream(caminho);
                            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                            Toast.makeText(MainActivity.this,"Gabarito do tipo oficial inserido com sucesso!",Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        finally {
                            try {
                                fileOutputStream.close();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(s.equals("Aluno"))
                    {
                        try {
                            File caminho = new File(diretorio,"Aluno.png");
                            fileOutputStream = new FileOutputStream(caminho);
                            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                            Toast.makeText(MainActivity.this,"Gabarito do tipo aluno inserido com sucesso!",Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        finally {
                            try {
                                fileOutputStream.close();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            alertdialog.show();
        }
    }

    public void CarregarImagem()
    {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File diretorio = contextWrapper.getDir("imageDir",Context.MODE_PRIVATE);
        try
        {
            File file = new File(diretorio,"Branco.png");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imageView = new ImageView(this);
            imageView.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e)
        {
            imageView = null;
            e.printStackTrace();
        }
        try
        {
            File file = new File(diretorio,"Oficial.png");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imageView1 = new ImageView(this);
            imageView1.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e)
        {
            imageView1 = null;
            e.printStackTrace();
        }
        try
        {
            File file = new File(diretorio,"Aluno.png");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            imageView2 = new ImageView(this);
            imageView2.setImageBitmap(bitmap);
        }
        catch (FileNotFoundException e)
        {
            imageView2 = null;
            e.printStackTrace();
        }
    }

    public void MostrarFotos(View view)
    {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        CarregarImagem();
        if (imageView != null) {
            imageView.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 300, getResources().getDisplayMetrics());
            imageView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 300, getResources().getDisplayMetrics());
        }
        if (imageView1 != null) {
            imageView1.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 300, getResources().getDisplayMetrics());
            imageView1.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 300, getResources().getDisplayMetrics());
        }
        if (imageView2 != null) {
            imageView2.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 300, getResources().getDisplayMetrics());
            imageView2.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 300, getResources().getDisplayMetrics());
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        if (imageView != null) {
            linearLayout.addView(imageView);
        }
        if (imageView1 != null) {
            linearLayout.addView(imageView1);
        }
        if (imageView2 != null) {
            linearLayout.addView(imageView2);
        }
        alertdialog.setView(linearLayout);
        alertdialog.setNeutralButton("FECHAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        if (imageView != null || imageView1 != null || imageView2 != null) {
            alertdialog.show();
        }
    }
}
