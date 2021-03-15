package com.example.projectcompis.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectcompis.R;
import com.example.projectcompis.crud.CRUD_Usuario;
import com.example.projectcompis.model.Usuario;

import io.realm.Realm;

public class RegistroActivity extends AppCompatActivity {
    private EditText etNombre, etEmail, etClave;
    private Button bRegistrar;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        realm = Realm.getDefaultInstance();
        configView();
    }

    private void configView() {
        Usuario usuarioNuevo = new Usuario();
        etNombre = findViewById(R.id.etRegistroNombre);
        etEmail = findViewById(R.id.etRegistroEmail);
        etClave = findViewById(R.id.etRegistroClave);
        bRegistrar = findViewById(R.id.bRegistroRegistrar);

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario comprobadorDeEmail = CRUD_Usuario.getUsuarioByEmail(etEmail.getText().toString());
                    if (comprobadorDeEmail.getEmail().equals(etEmail.getText().toString())) {
                        Toast.makeText(getApplicationContext(),"ERROR: Ya hay una cuenta registrada con ese email",Toast.LENGTH_SHORT).show();
                    } else {
                        usuarioNuevo.setNombre(etNombre.getText().toString());
                        usuarioNuevo.setEmail(etEmail.getText().toString());
                        usuarioNuevo.setClave(etClave.getText().toString());
                        CRUD_Usuario.addUsuario(usuarioNuevo);
                        CRUD_Usuario.getAllUsuario();
                        Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    usuarioNuevo.setNombre(etNombre.getText().toString());
                    usuarioNuevo.setEmail(etEmail.getText().toString());
                    usuarioNuevo.setClave(etClave.getText().toString());
                    CRUD_Usuario.addUsuario(usuarioNuevo);
                    CRUD_Usuario.getAllUsuario();
                    Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}