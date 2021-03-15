package com.example.projectcompis.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.projectcompis.ui.fragment.OnTareaInteractionListener;
import com.example.projectcompis.R;
import com.example.projectcompis.model.TareaAntigua;
import com.example.projectcompis.crud.CRUD_Usuario;
import com.example.projectcompis.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TareaActivity extends AppCompatActivity implements OnTareaInteractionListener {
    private Toolbar tbTarea;
    private FloatingActionButton bTarea;
    private String emailUsuario;
    private int idPiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        tbTarea = findViewById(R.id.toolbarTareas);
        bTarea = findViewById(R.id.bTarea);

        idPiso = getIntent().getIntExtra("ID_PISO", 0);


        /*
        File outputDir = this.getCacheDir(); // context being the Activity pointer

        try {
            File outputFile = File.createTempFile("idPisoTemp", null, outputDir);
            fw = new FileWriter(outputFile);
            fw.write(idPiso);
            if (outputFile.exists()) {
                Log.d("ITEM", "El fichero " + outputFile.getName() + " existe");
                Log.d("ITEM", "El fichero " + outputFile.getAbsolutePath() + " existe");
            }
            idPisoTemp4660481153662124763.tmp
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


        File f = null;
        FileWriter fw = null;

        try {
            f = new File("idPiso");
            if (f.exists()) System.out.println("El fichero existe");
            if (!f.exists()) System.out.println("El fichero NO existe");
            fw = new FileWriter(f);
            fw.write(idPiso);
            fw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        emailUsuario = getIntent().getStringExtra("EMAIL_USUARIO");
        Usuario usuario = CRUD_Usuario.getUsuarioByEmail(emailUsuario);
        tbTarea.setTitle("Bienvenido " + usuario.getNombre());
        setSupportActionBar(tbTarea);

        bTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirNuevaTarea();
            }
        });
    }

    private void crearCache() {
        FileWriter fw = null;
        File outputDir = this.getCacheDir(); // context being the Activity pointer

        try {
            File outputFile = File.createTempFile("idPisoTemp", null, outputDir);
            fw = new FileWriter(outputFile);
            fw.write(idPiso);
            if (outputFile.exists()) {
                Log.d("ITEM", "El fichero " + outputFile.getName() + " existe");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Abre "NuevaTareaAvtivity"
    private void abrirNuevaTarea() {
        Intent intentPerfilRegistro = new Intent(this, NuevaTareaActivity.class);
        intentPerfilRegistro.putExtra("ID_PISO", idPiso);
        startActivity(intentPerfilRegistro);
    }

    //Añade las opciones a la Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tareas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Maneja las opciones de la Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_perfil:
                Intent intentPerfil = new Intent(this, PerfilActivity.class);
                intentPerfil.putExtra("EMAIL_USUARIO", emailUsuario);
                startActivity(intentPerfil);
                return true;

            case R.id.action_cerrarSesion:
                Intent intentCerrar = new Intent(this, MainActivity.class);
                startActivity(intentCerrar);
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Hace algo cuando se pulsa una tareaAntigua

    /*
    @Override
    public void onTareaClick(TareaAntigua tareaAntigua) {
    }

     */

    @Override
    public void onTareaClick(TareaAntigua tareaAntigua) {

    }
}