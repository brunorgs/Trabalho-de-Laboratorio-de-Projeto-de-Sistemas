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
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.Core.subtract;

public class MainActivity extends AppCompatActivity {

    String s = "";
    ProgressBar progressBar;
    Bitmap branco;
    Bitmap oficial;
    Bitmap aluno;
    Aluno dadosFotoAluno;
    Branco dadosFotoBranco;
    Oficial dadosFotoOficial;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                    Mat imageMat=new Mat();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
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

    public void MostrarFotos(View view)
    {
        Intent intent = new Intent(this,mostrarfotos.class);
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
        File file1 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
        if (file.exists() || file1.exists() || file2.exists())
        {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Nenhuma foto inserida!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        progressBar.setVisibility(View.GONE);
        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d("OpenCV", "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    public void CarregarImagem()
    {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
        File file1 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
        ExifInterface exifInterface = null;
        if (file.exists()) {
            try {
                exifInterface = new ExifInterface(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Matrix matrix = new Matrix();
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            branco = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            dadosFotoBranco = new Branco(branco.getWidth(),branco.getHeight(),"Branco.jpg",branco.getByteCount(),file.getParent(),"JPEG");
        }
        if (file1.exists()) {
            try {
                exifInterface = new ExifInterface(file1.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Matrix matrix = new Matrix();
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath());
            oficial = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            dadosFotoOficial = new Oficial(oficial.getWidth(),oficial.getHeight(),"Oficial.jpg",oficial.getByteCount(),file1.getParent(),"JPEG");
        }
        if (file2.exists()) {
            try {
                exifInterface = new ExifInterface(file2.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Matrix matrix = new Matrix();
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
            aluno = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            dadosFotoAluno = new Aluno(aluno.getWidth(),aluno.getHeight(),"Aluno.jpg",aluno.getByteCount(),file2.getParent(),"JPEG");
        }
    }

    public void IrTelaProvaCorrigida(View view)
    {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
        File file1 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
        ExifInterface exifInterface = null;
        if (file.exists() && file1.exists() && file2.exists())
        {
            try {
                exifInterface = new ExifInterface(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Matrix matrix = new Matrix();
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            branco = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            try {
                exifInterface = new ExifInterface(file1.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }
            bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath());
            oficial = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            try {
                exifInterface = new ExifInterface(file2.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            orientacao = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            if (orientacao == ExifInterface.ORIENTATION_ROTATE_90) {
                matrix.postRotate(90);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_180) {
                matrix.postRotate(180);
            } else if (orientacao == ExifInterface.ORIENTATION_ROTATE_270) {
                matrix.postRotate(270);
            }
            bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
            aluno = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            Corrigir();
        }
        else
        {
            Toast.makeText(this,"Insira os 3 tipos de gabarito!",Toast.LENGTH_LONG).show();
        }
    }

    public void Corrigir (){
        Mat resultado_final = new Mat();
        Mat gabarito_mat = new Mat();
        Mat resposta_mat = new Mat();
        Mat branco_mat = new Mat();


        Utils.bitmapToMat(oficial, gabarito_mat);
        Utils.bitmapToMat(aluno, resposta_mat);
        Utils.bitmapToMat(branco, branco_mat);

        Imgproc.medianBlur(gabarito_mat, gabarito_mat, 9);
        Imgproc.medianBlur(resposta_mat, resposta_mat, 9);
        Mat gabarito_cinza = OpenCV.converterParaCinza(gabarito_mat);
        Mat aluno_cinza = OpenCV.converterParaCinza(resposta_mat);
        Mat gabarito_limiarizado = OpenCV.aplicarLimiarizacao(gabarito_cinza);
        Mat aluno_limiarizado = OpenCV.aplicarLimiarizacao(aluno_cinza);
        subtract(gabarito_limiarizado,aluno_limiarizado,gabarito_limiarizado);
        Imgproc.medianBlur(gabarito_limiarizado,resultado_final,9);
        List<MatOfPoint> contours = new ArrayList<>();
        resultado_final = OpenCV.converterParaCinza(resultado_final);
        Imgproc.Canny(resultado_final,resultado_final,75,200,3,true);
        Imgproc.findContours(resultado_final,contours,new Mat(),Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        int quant_circulos = 0;
        MatOfPoint2f polygon = new MatOfPoint2f();
        for (int x = 0; x < contours.size()-2;x++)
        {
            Imgproc.approxPolyDP(OpenCV.convert(contours.get(x)), polygon, 20, true);
            double area = Imgproc.contourArea(contours.get(x));
            if ((polygon.size().height > 1) & area > 45)
            {
                quant_circulos++;
            }
        }
        Toast.makeText(this,"Quantidade de erros:" + Integer.toString(quant_circulos),Toast.LENGTH_LONG).show();
    }
}
