package com.example.projectcompis.crud;

import android.util.Log;

import com.example.projectcompis.model.Usuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CRUD_Usuario {

    private final static int calculatedID() {
        int nextId;
        Realm realm = Realm.getDefaultInstance();
        Number currentId = realm.where(Usuario.class).max("idUsuario");

        if (currentId == null) {
            nextId = 0;
        } else {
            nextId = currentId.intValue() + 1;
        }
        return nextId;
    }

    public final static void addUsuario(final Usuario usuario) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int index = CRUD_Usuario.calculatedID();
                Usuario realmUsuario = realm.createObject(Usuario.class, index);
                realmUsuario.setEmail(usuario.getEmail());
                realmUsuario.setNombre(usuario.getNombre());
                realmUsuario.setClave(usuario.getClave());
            }
        });
    }

    public static final List<Usuario> getAllUsuario() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Usuario> usuarios = realm.where(Usuario.class).findAll();

        for (Usuario usuario: usuarios) {
            Log.d("ITEM", "ID: " + usuario.getIdUsuario() + " Email: " + usuario.getEmail() + " Clave: " + usuario.getEmail());
        }
        return usuarios;
    }

    public static final Usuario getUsuarioByEmail(String email){
        //Como siempre obtenemos la instancia de Realm
        Realm realm = Realm.getDefaultInstance();

        //Usamos de Real where, equalto y findFirst para obtener la primera ocurrencia en nuestra BBDD
        Usuario usuario = realm.where(Usuario.class).equalTo("email", email).findFirst();

        if(usuario != null){
            //Lo mostramos en el Log
            Log.d("BUSCAR_NOMBRE", "Id: " + usuario.getIdUsuario() + " Email: "
                    + usuario.getEmail() + " Clave: " + usuario.getClave());
        }else{
            Log.d("BUSCAR_NOMBRE", "No existe ningún usuario con ese nombre");
        }
        return usuario;
    }

    public static final void updatePisoUsuarioById(int idUsuario, int idPiso) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Usuario usuario = realm.where(Usuario.class).equalTo("idUsuario", idUsuario).findFirst();
        usuario.setIdPiso(idPiso);
        realm.commitTransaction();
    }
}
