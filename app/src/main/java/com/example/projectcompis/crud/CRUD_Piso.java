package com.example.projectcompis.crud;

import android.util.Log;

import com.example.projectcompis.model.Piso;
import com.example.projectcompis.model.Piso;
import com.example.projectcompis.model.Tarea;
import com.example.projectcompis.model.Usuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CRUD_Piso {
    private final static int calculatedID() {
        int nextId;
        Realm realm = Realm.getDefaultInstance();
        Number currentId = realm.where(Piso.class).max("idPiso");
        if (currentId == null) {
            nextId = 1;
        } else {
            nextId = currentId.intValue() + 1;
        }
        return nextId;
    }

    public final static int addPiso(final Piso piso) {
        Realm realm = Realm.getDefaultInstance();
        final int[] aux = new int[1];
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int index = CRUD_Piso.calculatedID();
                aux[0] = index;
                Piso realmPiso = (Piso) realm.createObject(Piso.class, index);
            }
        });
        return aux[0];
    }

    public static final List<Piso> getAllPiso() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Piso> pisos = realm.where(Piso.class).findAll();

        for (Piso piso: pisos) {
            Log.d("ITEM", "ID: " + piso.getIdPiso());
        }
        return pisos;
    }

    public static final Piso getPisoByID(int idPiso){
        Realm realm = Realm.getDefaultInstance();
        Piso piso = realm.where(Piso.class).equalTo("idPiso", idPiso).findFirst();

        if(piso != null){
            Log.d("BUSCAR: ", "Id: " + piso.getIdPiso());
        }else{
            Log.d("BUSCAR: ", "No existe ningún piso con ese nombre");
        }
        return piso;
    }
}
