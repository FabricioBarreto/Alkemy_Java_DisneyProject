package com.disney.personajes.dto;

import java.util.Date;

public class ErrorDetalles {

    private Date maracaDeTiempo;

    private String mensaje;

    private String detalles;

    public Date getMaracaDeTiempo() {
        return maracaDeTiempo;
    }

    public void setMaracaDeTiempo(Date maracaDeTiempo) {
        this.maracaDeTiempo = maracaDeTiempo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public ErrorDetalles() {
    }

    public ErrorDetalles(Date maracaDeTiempo, String mensaje, String detalles) {
        this.maracaDeTiempo = maracaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
}
