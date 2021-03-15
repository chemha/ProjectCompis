package com.example.projectcompis.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projectcompis.R;
import com.example.projectcompis.crud.CRUD_Usuario;
import com.example.projectcompis.model.Usuario;

public class PerfilActivity extends AppCompatActivity {
    private TextView tvNombre, tvEmail, tvID;
    private String emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emailUsuario = getIntent().getStringExtra("EMAIL_USUARIO");
        setContentView(R.layout.activity_perfil);
        tvEmail = findViewById(R.id.tvPerfilEmail);
        tvNombre = findViewById(R.id.tvPerfilNombre);
        tvID = findViewById(R.id.tvPerfilPiso);
        Usuario usuario = CRUD_Usuario.getUsuarioByEmail(emailUsuario);
        tvEmail.setText("Email: " + emailUsuario);
        tvNombre.setText("Nombre: " + usuario.getNombre());
        tvID.setText("ID del piso: " + usuario.getIdPiso());
    }
}