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

public class ViewActivity extends AppCompatActivity {
    private Lista listaCodigos;
    private static boolean recuperacion = false;
    private EditText edtMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        edtMostrar = findViewById(R.id.edtMostrar);
        recuperacion = (boolean) getIntent().getBooleanExtra("recuperacion", false);
        if(recuperacion) {
            listaCodigos = (Lista) getIntent().getSerializableExtra("informacion");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Datos leídos.", Toast.LENGTH_SHORT).show();
                edtMostrar.setText(listaCodigos.buscar(intentResult.getContents()));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void mostrar(View view) {
        edtMostrar.setText(listaCodigos.mostrar());
    }
    public void buscar(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(ViewActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Lector de libros");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }
}