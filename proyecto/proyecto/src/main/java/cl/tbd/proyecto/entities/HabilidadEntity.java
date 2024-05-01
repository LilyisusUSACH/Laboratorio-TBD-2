package cl.tbd.proyecto.entities;

public class HabilidadEntity {
    private Long id_habilidad;
    private String nombre;
    private String descripcion;
    private int certificacion_requerida;

    public HabilidadEntity(Long id, String nombre, String descripcion, int certificacion_requerida) {
        this.id_habilidad = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.certificacion_requerida = certificacion_requerida;
    }

    public Long getId() {
        return id_habilidad;
    }

    public void setId(Long id) {
        this.id_habilidad = id;
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

    public int getCertificacion_requerida() {
        return certificacion_requerida;
    }

    public void setCertificacion_requerida(int certificacion_requerida) {
        this.certificacion_requerida = certificacion_requerida;
    }

}
