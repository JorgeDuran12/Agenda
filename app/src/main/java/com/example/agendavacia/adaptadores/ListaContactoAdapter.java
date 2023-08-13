package com.example.agendavacia.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendavacia.R;
import com.example.agendavacia.entidades.Contactos;

import java.util.ArrayList;

public class ListaContactoAdapter extends RecyclerView.Adapter<ListaContactoAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContacto;

    public  ListaContactoAdapter(ArrayList<Contactos> listaContacto){
        this.listaContacto = listaContacto;

    }
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return  new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaContacto.get(position).getNombre());
        holder.viewTelefono.setText(listaContacto.get(position).getTelefono());
        holder.viewCorreo.setText(listaContacto.get(position).getCorreo_electronico());


    }

    @Override
    public int getItemCount() {
        return  listaContacto.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewTelefono, viewCorreo;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
        }
    }
}
