package com.example.corretordegabarito;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.opencv.core.Mat;

import java.io.File;

public class AdapterMostrarFotos extends RecyclerView.Adapter<ViewHolderMostrarFotos> {

    File [] file;
    int x = 0;

    public AdapterMostrarFotos (File file1,File file2,File file3)
    {
        file = new File[3];
    }

    @Override
    public ViewHolderMostrarFotos onCreateViewHolder (ViewGroup viewGroup, int x)
    {
        ViewHolderMostrarFotos viewHolderMostrarFotos = new ViewHolderMostrarFotos(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_view_holder_mostrar_fotos,viewGroup,false));
        return (viewHolderMostrarFotos);
    }

    @Override
    public void onBindViewHolder(ViewHolderMostrarFotos viewHolderMostrarFotos,int position)
    {
        if (file[position] != null)
        {
            ExifInterface exifInterface = null;
            try {
                exifInterface = new ExifInterface(file[position].getAbsolutePath());
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
            Bitmap bitmap = BitmapFactory.decodeFile(file[position].getAbsolutePath());
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            Mat colorida = OpenCV.convertBitmap2Mat(bitmap1);
            Mat cinza = OpenCV.converterParaCinza(colorida);
            Mat limiarizada = OpenCV.aplicarLimiarizacao(cinza);
            bitmap1 = OpenCV.convertMat2Bitmap(limiarizada);
            viewHolderMostrarFotos.foto.setImageBitmap(bitmap1);
            if (file[position].getName().equals("Branco.jpg"))
            {
                viewHolderMostrarFotos.nome.setText("Branco");
            }
            else if(file[position].getName().equals("Aluno.jpg"))
            {
                viewHolderMostrarFotos.nome.setText("Aluno");
            }
            else if(file[position].getName().equals("Oficial.jpg"))
            {
                viewHolderMostrarFotos.nome.setText("Oficial");
            }
        }
    }

    @Override
    public int getItemCount()
    {
        int tamanho = 0;
        if (file [0] != null)
        {
            tamanho++;
        }
        if (file [1] != null)
        {
            tamanho++;
        }
        if (file [2] != null)
        {
            tamanho++;
        }
        return (tamanho);
    }

    public void Inserir(File file3)
    {
        file[x] = file3;
        notifyItemInserted(x);
        x++;
    }
}
