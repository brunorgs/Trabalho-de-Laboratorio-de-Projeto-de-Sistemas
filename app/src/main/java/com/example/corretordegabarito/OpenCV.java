package com.example.corretordegabarito;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.List;

public class OpenCV {
    public static Mat aplicarLimiarizacao(Mat imagem)
    {
        Mat limiarizada = new Mat();

        Imgproc.adaptiveThreshold(imagem, limiarizada, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                Imgproc.THRESH_BINARY, 81, 20);

        return limiarizada;
    }


    public static Mat converterParaCinza(Mat imagem)
    {
        Mat cinza = imagem.clone();

        // So converte se a imagem for colorida (tres canais - RGB)
        if(imagem.channels() == 4)
        {
            Imgproc.cvtColor(imagem, cinza, Imgproc.COLOR_BGR2GRAY);
        }

        return cinza;
    }


    public static Mat subtrairImagens(Mat imagemA, Mat imagemB)
    {
        Mat resultado = new Mat();
        if(imagemA.channels() != imagemB.channels())
        {
            return null;

        }

        Core.subtract(imagemA, imagemB, resultado);
        return resultado;
    }


    public static Mat convertBitmap2Mat(Bitmap imagemBitmap)
    {
        Mat mat = new Mat();
        Bitmap bitmap = imagemBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bitmap, mat);
        return mat;
    }


    public static Bitmap convertMat2Bitmap(Mat imagem)
    {
        try
        {
            //Imgproc.cvtColor(imagem, imagem, Imgproc.COLOR_GRAY2BGRA);
            Bitmap bitmap = Bitmap.createBitmap(imagem.cols(), imagem.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(imagem, bitmap);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static MatOfPoint2f convert(MatOfPoint mat) {
        return new MatOfPoint2f(mat.toArray());
    }

    public static MatOfPoint convert(MatOfPoint2f mat) {
        return new MatOfPoint(mat.toArray());
    }
}
