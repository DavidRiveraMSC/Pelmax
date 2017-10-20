package com.proyectos.davidrivera.pelmax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Nuevo, Listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nuevo = (Button) findViewById(R.id.btnNuevo);
        Listar = (Button) findViewById(R.id.btnListar);

        Nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Nuevo  = new Intent(MainActivity.this, Nuevo_Producto.class);
                startActivity(Nuevo);
            }
        });

        Listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Listar = new Intent(MainActivity.this,Listar_Productos.class);
                startActivity(Listar);
            }
        });
    }
}
