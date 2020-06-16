package com.example.tesis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionarOrden extends AppCompatActivity implements View.OnClickListener {

    Cliente nuevo = new Cliente();
    EditText editText, editTextApellido, editTextDireccion;
    Button btnUbicacion, btnFoto;
    String absolutePath = "";
    final int Photo_CONST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_orden);

        nuevo.setNombre(getIntent().getStringExtra("Nombre"));
        nuevo.setApellido(getIntent().getStringExtra("Apellido"));
        nuevo.setDireccion(getIntent().getStringExtra("Direccion"));

        editText = findViewById(R.id.editTextNombre);
        editText.setText(nuevo.getNombre());

        editTextApellido = findViewById(R.id.editTextApellido);
        editTextApellido.setText(nuevo.getApellido());

        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextDireccion.setText(nuevo.getDireccion());

        btnUbicacion = findViewById(R.id.BtnUbicacion);
        btnFoto = findViewById(R.id.BtnTomarFoto);

        btnUbicacion.setOnClickListener(this);
        btnFoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnUbicacion:
                Intent i = new Intent(GestionarOrden.this, MapsActivity.class);
                startActivity(i);

                break;
            case R.id.BtnTomarFoto:
                TomarFoto();
                break;

        }
    }

    public void TomarFoto() {

        Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (tomarFotoIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createPhotoFile();

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (photoFile != null){
                Uri photoUri = FileProvider.getUriForFile(GestionarOrden.this,"com.example.tesis",photoFile);
                tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(tomarFotoIntent, Photo_CONST);
            }
        }
    }

    public File createPhotoFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String imageFileName = "Imagen" + timestamp;

        File storageFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoFile = File.createTempFile(imageFileName, ".jpg", storageFile);

        absolutePath = photoFile.getAbsolutePath();
        return photoFile;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultcode, @Nullable Intent data){

        if(requestCode == Photo_CONST && resultcode == RESULT_OK){
            Toast.makeText(this, "Se pudo tomar la foto", Toast.LENGTH_LONG).show();
        }
    }
}

