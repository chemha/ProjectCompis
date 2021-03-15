package com.example.projectcompis.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectcompis.R;
import com.example.projectcompis.crud.CRUD_Usuario;

import android.widget.Toast;

import com.example.projectcompis.model.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etClave;
    private Button bLogin, bRegistrar;
    private String emailUsuario;
    int idPiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etMainEmail);
        etClave = findViewById(R.id.etMainClave);
        bRegistrar = findViewById(R.id.bMainRegistrar);

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirRegistro();
            }
        });

        bLogin = findViewById(R.id.bMainLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuario = CRUD_Usuario.getUsuarioByEmail(etEmail.getText().toString());
                    emailUsuario = usuario.getEmail();
                    if (usuario.getClave().equals(etClave.getText().toString())) {
                        if (usuario.getIdPiso() == 0) {
                            abrirPiso();
                        } else {
                            idPiso = usuario.getIdPiso();
                            abrirTareas();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Datos erroneos",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Datos erroneos",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    //Abre "RegistroActivity"
    private void abrirRegistro() {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    //Abre "TareaActivity
    private void abrirTareas() {
        Intent intent = new Intent(this, TareaActivity.class);
        intent.putExtra("EMAIL_USUARIO", emailUsuario);
        intent.putExtra("ID_PISO", idPiso);
        startActivity(intent);
        this.finish();
    }

    //Abre "PisoActivity"
    private void abrirPiso() {
        Intent intent = new Intent(this, PisoActivity.class);
        intent.putExtra("EMAIL_USUARIO", emailUsuario);
        startActivity(intent);
        this.finish();
    }
}