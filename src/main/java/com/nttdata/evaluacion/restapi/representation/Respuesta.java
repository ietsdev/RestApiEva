package com.nttdata.evaluacion.restapi.representation;

public class Respuesta<T> {
    private String mensaje;
    private T respuesta;

    public Respuesta(String mensaje, T respuesta){
        this.mensaje = mensaje;
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public T getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(T respuesta) {
        this.respuesta = respuesta;
    }

}