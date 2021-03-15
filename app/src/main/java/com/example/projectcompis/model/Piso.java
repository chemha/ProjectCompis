package com.example.projectcompis.model;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Piso extends RealmObject {
    @PrimaryKey
    private int idPiso;
    private RealmList<Tarea> tareas;

    public RealmList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(RealmList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public int getIdPiso() {
        return idPiso;
    }

    public void setIdPiso(int idPiso) {
        this.idPiso = idPiso;
    }
}
