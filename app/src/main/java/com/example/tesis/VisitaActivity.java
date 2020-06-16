package com.example.tesis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VisitaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listOrden;
    List<String> OrdenesList;
    List<Cliente> listaCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita);

        listOrden = findViewById(R.id.idListView);
        listaCliente = new ArrayList<>();

        OrdenesList = new ArrayList<>();
        Cliente x = new Cliente("Ricardo","von","4to anillo alemana");
        Cliente y = new Cliente("Luis","copa","Alto san pedro");
        Cliente z = new Cliente("Marco", "Siñaniz","4to anillo beni");

        listaCliente.add(x);
        listaCliente.add(y);
        listaCliente.add(z);
        //Toast.makeText(this, listaCliente.size(), Toast.LENGTH_LONG).show();

        for(int i = 0; i < listaCliente.size(); i++)
        {
            OrdenesList.add(listaCliente.get(i).getNombre() + " "+ listaCliente.get(i).getApellido()+" "+ listaCliente.get(i).getDireccion());
        }
        ArrayAdapter adapterOrdenesList = new ArrayAdapter(this, android.R.layout.simple_list_item_1, OrdenesList);

        listOrden.setAdapter(adapterOrdenesList);

        listOrden.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(VisitaActivity.this, GestionarOrden.class);
        Cliente nuevo = listaCliente.get(position);
        i.putExtra("Nombre", nuevo.getNombre());
        i.putExtra("Apellido", nuevo.getApellido());
        i.putExtra("Direccion", nuevo.getDireccion());
        startActivity(i);
    }
}
