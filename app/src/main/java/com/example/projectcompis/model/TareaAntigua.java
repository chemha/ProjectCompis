package com.example.projectcompis.model;

public class TareaAntigua {
    private String nombreTarea, nombreUsuario, prioridad;

    public TareaAntigua() {
    }

    public TareaAntigua(String nombreTarea, String nombreUsuario, String prioridad) {
        this.nombreTarea = nombreTarea;
        this.nombreUsuario = nombreUsuario;
        this.prioridad = prioridad;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
}
