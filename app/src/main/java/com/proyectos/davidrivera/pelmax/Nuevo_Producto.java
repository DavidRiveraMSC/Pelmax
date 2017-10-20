package com.proyectos.davidrivera.pelmax;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Nuevo_Producto extends AppCompatActivity {

    EditText Producto, Precio, Cantidad;
    Button Guardar, Volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo__producto);

        Producto = (EditText) findViewById(R.id.txtProducto);
        Precio = (EditText) findViewById(R.id.txtPrecio);
        Cantidad = (EditText) findViewById(R.id.txtCantidad);
        Guardar = (Button) findViewById(R.id.btnGuardar);
        Volver = (Button) findViewById(R.id.btnVolver);

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guardar(Producto.getText().toString(),Integer.parseInt(Precio.getText().toString()),Integer.parseInt(Cantidad.getText().toString()));
            }
        });

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(Nuevo_Producto.this, MainActivity.class);
                startActivity(volver);
            }
        });
    }

    private void Guardar(String Producto, Integer Precio, Integer Cantidad){
        BaseHelper helper = new BaseHelper(this,"Pelmax",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("PRODUCTO",Producto);
            c.put("PRECIO",Precio);
            c.put("CANTIDAD",Cantidad);
            db.insert("PRODUCTOS",null,c);
            db.close();
            Toast.makeText(this,"Producto Guardado con Éxito",Toast.LENGTH_SHORT).show();

            Intent listado = new Intent(Nuevo_Producto.this,Listar_Productos.class);
            startActivity(listado);


        }catch(Exception ex){
            Toast.makeText(this,"Error:" + ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}
