package com.proyectos.davidrivera.pelmax;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listar_Productos extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;


    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__productos);
        listView = (ListView) findViewById(R.id.listView);
        CargarListado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Listar_Productos.this,listado.get(i), Toast.LENGTH_SHORT).show();

                String id = listado.get(i).split(" ")[0];
                String producto = listado.get(i).split(" ")[1];
                String precio = listado.get(i).split(" ")[2];
                String cantidad = listado.get(i).split(" ")[3];

                Intent intent = new Intent(Listar_Productos.this, Modificar.class);
                intent.putExtra("ID",id);
                intent.putExtra("PRODUCTO",producto);
                intent.putExtra("PRECIO",precio);
                intent.putExtra("CANTIDAD",cantidad);
                startActivity(intent);
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void CargarListado(){
        listado = ListarProductos();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListarProductos(){
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this,"Pelmax",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT ID, PRODUCTO, PRECIO, CANTIDAD FROM PRODUCTOS";
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getInt(2) + " " + c.getInt(3);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }
}
