package com.example.corretordegabarito;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import java.io.File;

public class mostrarfotos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_mostrarfotos);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        File file1 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Branco.jpg");
        File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Oficial.jpg");
        File file3 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),"Aluno.jpg");
        Intent intent = getIntent();
        char tipo = intent.getCharExtra("tipo",' ');
        AdapterMostrarFotos adapterMostrarFotos = new AdapterMostrarFotos(file1,file2,file3,tipo);
        recyclerView.setAdapter(adapterMostrarFotos);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        if (file1.exists())
        {
            adapterMostrarFotos.Inserir(file1);
        }
        if (file2.exists())
        {
            adapterMostrarFotos.Inserir(file2);
        }
        if (file3.exists())
        {
            adapterMostrarFotos.Inserir(file3);
        }
    }
}
