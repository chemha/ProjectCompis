package com.example.projectcompis.crud;

import android.util.Log;

import com.example.projectcompis.model.Piso;
import com.example.projectcompis.model.Tarea;
import com.example.projectcompis.model.Usuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CRUD_Tarea {

    public final static void addTarea(int idPiso, final Tarea tarea) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Piso piso = realm.where(Piso.class).equalTo("idPiso", idPiso).findFirst();
                piso.getTareas().add(tarea);
                realm.insertOrUpdate(piso);
                Log.d("ITEM", "Nombre: " + tarea.getNombreTarea() + " Encargado: " + tarea.getEmailUsuario()
                        + " Prioridad: " + tarea.getPrioridad());
            }
        });
    }
}
