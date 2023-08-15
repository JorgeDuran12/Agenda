package com.example.agendavacia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agendavacia.db.DbContactos;
import com.example.agendavacia.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {
    EditText txtNombre,txtTelefono,txtCorreo;
    Button btnGuardar;
    Contactos contacto;
    int id = 0;

    FloatingActionButton fabEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.ver_nombre);
        txtTelefono = findViewById(R.id.ver_numero);
        txtCorreo = findViewById(R.id.ver_email);
        btnGuardar = findViewById(R.id.ver_btn_guardar);
        fabEditar = findViewById(R.id.fabeditar);

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id );
                startActivity(intent);
            }
        });


        if(savedInstanceState == null ){
            Bundle extras = getIntent().getExtras();
            if(extras == null ){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);

        }

    }
}