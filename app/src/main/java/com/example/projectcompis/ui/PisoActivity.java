package com.example.projectcompis.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcompis.R;
import com.example.projectcompis.crud.CRUD_Piso;
import com.example.projectcompis.crud.CRUD_Usuario;
import com.example.projectcompis.model.Piso;
import com.example.projectcompis.model.Usuario;

public class PisoActivity extends AppCompatActivity {
    private String emailUsuario;
    private Button bCrearPiso, bUnirPiso;
    private TextView tvSaludo;
    private EditText etPiso;
    int idPiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piso);
        emailUsuario = getIntent().getStringExtra("EMAIL_USUARIO");

        String nombreUsuario;
        tvSaludo = findViewById(R.id.tvPisoSaludo);
        bUnirPiso = findViewById(R.id.bPisoUnir);
        etPiso = findViewById(R.id.etPisoCodigo);
        Usuario usuario = CRUD_Usuario.getUsuarioByEmail(emailUsuario);

        nombreUsuario = usuario.getNombre();
        tvSaludo.setText("Bienvenido " + nombreUsuario);


        bUnirPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    idPiso = Integer.parseInt(etPiso.getText().toString());
                    Piso piso = CRUD_Piso.getPisoByID(idPiso);

                    if (piso.getIdPiso() != 0) abrirTareas();
                    else Toast.makeText(getApplicationContext(),"ERROR: El piso solicitado no existe",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"ERROR: El piso solicitado no existe",Toast.LENGTH_SHORT).show();
                }
            }
        });
        configView();
    }

    private void configView() {
        Piso piso = new Piso();
        Usuario usuario = CRUD_Usuario.getUsuarioByEmail(emailUsuario);
        etPiso = findViewById(R.id.etPisoCodigo);
        bCrearPiso = findViewById(R.id.bPisoCrear);

        bCrearPiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idPiso = CRUD_Piso.addPiso(piso);
                CRUD_Usuario.updatePisoUsuarioById(usuario.getIdUsuario(), idPiso);
                Toast.makeText(getApplicationContext(),"Piso creado",Toast.LENGTH_SHORT).show();
                abrirTareas();
            }
        });
    }

    private void abrirTareas() {
        Intent intent = new Intent(this, TareaActivity.class);
        intent.putExtra("EMAIL_USUARIO", emailUsuario);
        intent.putExtra("ID_PISO", idPiso);
        startActivity(intent);
        this.finish();
    }

}