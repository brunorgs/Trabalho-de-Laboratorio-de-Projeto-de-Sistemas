package com.example.corretordegabarito;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolderMostrarFotos extends RecyclerView.ViewHolder {

    TextView nome;
    ImageViewDinamica foto;

    public ViewHolderMostrarFotos(View view)
    {
        super(view);
        nome = view.findViewById(R.id.textView4);
        foto = view.findViewById(R.id.imageViewDinamica);
    }

}
