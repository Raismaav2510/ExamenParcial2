package com.example.examenparcial2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LectorActivity extends AppCompatActivity {
    private EditText edtResultado, edtNombre, edtDescripcion;
    private Lista listaCodigos = new Lista();
    private static boolean recuperacion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector);
        edtResultado = findViewById(R.id.edtResultado);
        edtNombre = findViewById(R.id.edtNombre);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        recuperacion = (boolean) getIntent().getBooleanExtra("recuperacion", false);
        if(recuperacion) {
            listaCodigos = (Lista) getIntent().getSerializableExtra("informacion");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void escanearCodigoBarra(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(LectorActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Lector de libros");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Datos leídos.", Toast.LENGTH_SHORT).show();
                edtResultado.setText(intentResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void guardar(View view) {
        if(edtResultado.getText().toString().isEmpty() && edtNombre.getText().toString().isEmpty() && edtDescripcion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Algun dato esta vacío", Toast.LENGTH_SHORT).show();
        } else {
            String c = edtResultado.getText().toString();
            String n = edtNombre.getText().toString();
            String d = edtDescripcion.getText().toString();
            listaCodigos.insertarFinal(c, n, d);
            Toast.makeText(this, "Datos almacenados", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("recuperacion", true);
                intent.putExtra("informacion", listaCodigos);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}