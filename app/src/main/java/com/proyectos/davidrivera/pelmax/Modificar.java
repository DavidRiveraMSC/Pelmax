package com.proyectos.davidrivera.pelmax;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {

    EditText Id, Producto, Precio, Cantidad;
    Button Modificar, Volver;
    String producto,id,precio,cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b= getIntent().getExtras();
        if(b!=null){

            id = b.getString("ID");
            producto = b.getString("PRODUCTO");
            precio = b.getString("PRECIO");
            cantidad = b.getString("CANTIDAD");

        }

        Id = (EditText) findViewById(R.id.txtID);
        Producto = (EditText) findViewById(R.id.txtProducto);
        Precio = (EditText) findViewById(R.id.txtPrecio);
        Cantidad = (EditText) findViewById(R.id.txtCantidad);

        Id.setText(id);
        Producto.setText(producto);
        Precio.setText(precio);
        Cantidad.setText(cantidad);


        Modificar = (Button) findViewById(R.id.btnModificar);
        Volver = (Button) findViewById(R.id.btnVolver);

        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(Producto.getText().toString(),Integer.parseInt(Precio.getText().toString()),Integer.parseInt(Cantidad.getText().toString()),Integer.parseInt(Id.getText().toString()));
                onBackPressed();
            }
        });
    }

    private void modificar(String Producto, Integer Precio, Integer Cantidad,Integer Id){
        BaseHelper helper = new BaseHelper(this,"Pelmax",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "UPDATE PRODUCTOS SET PRODUCTO = '"+Producto+"', PRECIO = '"+Precio+"', CANTIDAD = '"+Cantidad+"' where ID = '"+Id+"'";
        db.execSQL(sql);
        db.close();
    }
}
