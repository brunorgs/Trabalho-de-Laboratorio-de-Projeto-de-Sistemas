package com.example.corretordegabarito;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
        if (file.exists())
        {
            file.delete();
        }
        file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
        if (file.exists())
        {
            file.delete();
        }
        file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
        if (file.exists())
        {
            file.delete();
        }
    }

    public void abrirCamera(View view)
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
        alertdialog.setPositiveButton("PROSSEGUIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RadioButton radioButton3 = linearLayout.findViewById(radioGroup.getCheckedRadioButtonId());
                s = radioButton3.getText().toString();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (s.equals("Branco"))
                {
                    if (intent.resolveActivity(getPackageManager())!= null)
                    {
                        File foto = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
                        Uri uri = FileProvider.getUriForFile(getApplicationContext(),"com.example.corretordegabarito",foto);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                        startActivityForResult(intent, 1);
                    }
                }
                else if(s.equals("Oficial"))
                {
                    if (intent.resolveActivity(getPackageManager())!= null)
                    {
                        File foto = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
                        Uri uri = FileProvider.getUriForFile(getApplicationContext(),"com.example.corretordegabarito",foto);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                        startActivityForResult(intent, 1);
                    }
                }
                else if(s.equals("Aluno"))
                {
                    if (intent.resolveActivity(getPackageManager())!= null)
                    {
                        File foto = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
                        Uri uri = FileProvider.getUriForFile(getApplicationContext(),"com.example.corretordegabarito",foto);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                        startActivityForResult(intent, 1);
                    }
                }
            }
        });
        alertdialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            if (s.equals("Branco"))
            {
                Toast.makeText(getApplicationContext(),"Gabarito do tipo branco inserido com sucesso!",Toast.LENGTH_LONG).show();
            }
            else if (s.equals("Oficial"))
            {
                Toast.makeText(getApplicationContext(),"Gabarito do tipo oficial inserido com sucesso!",Toast.LENGTH_LONG).show();
            }
            else if (s.equals("Aluno"))
            {
                Toast.makeText(getApplicationContext(),"Gabarito do tipo aluno inserido com sucesso!",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void MostrarFotos(View view) throws Exception
    {
        Intent intent = new Intent(this,mostrarfotos.class);
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
        File file1 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
        if (file.exists() || file1.exists() || file2.exists())
        {
            startActivity(intent);
        }
    }
}
