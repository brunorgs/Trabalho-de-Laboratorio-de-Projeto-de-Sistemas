package com.example.corretordegabarito;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> nomeimagem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
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
            radioGroup.setDividerDrawable(new ColorDrawable(Color.BLUE));
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
                    String datafoto = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                    String nomefoto = "PNG_" + datafoto + "_";
                    File caminho = new File(diretorio,nomefoto);
                    FileOutputStream fileOutputStream = null;
                    RadioButton radioButton3 = linearLayout.findViewById(radioGroup.getCheckedRadioButtonId());
                    String s = radioButton3.getText().toString();
                    if (s.equals("Branco"))
                    {
                        try {
                            fileOutputStream = new FileOutputStream(caminho);
                            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                            nomeimagem.add(nomefoto);
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
                            fileOutputStream = new FileOutputStream(caminho);
                            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                            nomeimagem.add(nomefoto);
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
                            fileOutputStream = new FileOutputStream(caminho);
                            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                            nomeimagem.add(nomefoto);
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
            File file = new File(diretorio,nomeimagem.get(nomeimagem.size()-1));
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
