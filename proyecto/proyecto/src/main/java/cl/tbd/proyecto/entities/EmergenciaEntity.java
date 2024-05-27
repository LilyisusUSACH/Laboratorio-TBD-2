package cl.tbd.proyecto.entities;

import java.util.Date;
import java.util.List;

public class EmergenciaEntity {
    private Long id_emergencia;
    private String nombre;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Long id_institucion;
    private Long id_estado;
    private double latitud;
    private double longitud;
    public EmergenciaEntity(Long id, String nombre, String descripcion, Date fecha_inicio, Date fecha_fin, Long id_institucion, Long id_estado, double latitud, double longitud) {
        this.id_emergencia = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.id_institucion = id_institucion;
        this.id_estado = id_estado;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Long getId() {
        return id_emergencia;
    }

    public void setId(Long id) {
        this.id_emergencia = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Long getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(Long id_institucion) {
        this.id_institucion = id_institucion;
    }

    public Long getId_estado() {
        return id_estado;
    }

    public void setId_estado(Long id_estado) {
        this.id_estado = id_estado;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


}
