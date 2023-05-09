package com.example.examenparcial2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Lista listaCodigos;
    private static boolean recuperacion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recuperacion = (boolean) getIntent().getBooleanExtra("recuperacion", false);
        if(recuperacion) {
            listaCodigos = (Lista) getIntent().getSerializableExtra("informacion");
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.miRegistrar:
                intent= new Intent(getApplicationContext(), LectorActivity.class);
                if(recuperacion) {
                    intent.putExtra("recuperacion", true);
                    intent.putExtra("informacion", listaCodigos);
                    recuperacion = false;
                }
                startActivity(intent);
                break;

            case R.id.miMostrar:
                intent = new Intent(getApplicationContext(), ViewActivity.class);
                if(recuperacion) {
                    intent.putExtra("recuperacion", true);
                    intent.putExtra("informacion", listaCodigos);
                    recuperacion = false;
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No hay nada almacenado", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}