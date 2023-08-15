package com.example.agendavacia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendavacia.adaptadores.ListaContactoAdapter;
import com.example.agendavacia.db.DbContactos;
import com.example.agendavacia.entidades.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        Toolbar toolbar;

        RecyclerView listaContactos;

      ArrayList<Contactos> listarArrayContactos;
    private int REQUEST_CODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         listaContactos = findViewById(R.id.ListaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
        DbContactos dbContactos = new DbContactos(MainActivity.this);

        listarArrayContactos = new ArrayList<>();
        ListaContactoAdapter  adapter = new ListaContactoAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuNuevo) {
            nuevoRegistro();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro() {
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
    }

}