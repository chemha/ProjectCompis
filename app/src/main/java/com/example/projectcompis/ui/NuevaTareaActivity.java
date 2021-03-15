package com.example.projectcompis.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.projectcompis.R;
import com.example.projectcompis.crud.CRUD_Tarea;
import com.example.projectcompis.model.Tarea;
import com.example.projectcompis.ui.fragment.DatePickerFragment;

public class NuevaTareaActivity extends AppCompatActivity {
    private EditText etNombre, etEmail, etFecha;
    private RadioButton rbPrioridadAlta, rbPrioridadMedia, rbPrioridadBaja;
    private Button bCrear;
    private int idPiso, day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        idPiso = getIntent().getIntExtra("ID_PISO", 0);

        bCrear = findViewById(R.id.bNuevaTarea);

        etFecha = findViewById(R.id.etNuevaTareaFecha);

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNombre = findViewById(R.id.etNuevaTareaNombre);
                etEmail = findViewById(R.id.etNuevaTareaEmail);
                rbPrioridadAlta = findViewById(R.id.rbPrioridadAlta);
                rbPrioridadMedia = findViewById(R.id.rbPrioridadMedia);
                rbPrioridadBaja = findViewById(R.id.rbPrioridadBaja);


                Tarea tarea = new Tarea();
                tarea.setNombreTarea(etNombre.getText().toString());
                tarea.setEmailUsuario(etNombre.getText().toString());
                tarea.setFechaTarea(etFecha.getText().toString());
                if (rbPrioridadAlta.isChecked()) tarea.setPrioridad("Alta");
                if (rbPrioridadMedia.isChecked()) tarea.setPrioridad("Media");
                if (rbPrioridadBaja.isChecked()) tarea.setPrioridad("Baja");
                CRUD_Tarea.addTarea(idPiso, tarea);
            }
        });


    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etFecha.setText(selectedDate);
            }
        });
        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

}