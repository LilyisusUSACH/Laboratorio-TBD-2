package cl.tbd.proyecto.entities;

public class Eme_HabilidadEntity {
    private Long id_eme_habilidad;
    private Long id_emergencia;
    private Long id_habilidad;

    public Eme_HabilidadEntity(Long id, Long id_emergencia, Long id_habilidad) {
        this.id_eme_habilidad = id;
        this.id_emergencia = id_emergencia;
        this.id_habilidad = id_habilidad;
    }

    public Long getId() {
        return id_eme_habilidad;
    }

    public void setId(Long id) {
        this.id_eme_habilidad = id;
    }

    public Long getId_emergencia() {
        return id_emergencia;
    }

    public void setId_emergencia(Long id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    public Long getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(Long id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    @Override
    public String toString() {
        return "Eme_HabilidadEntity{" +
                "id_eme_habilidad=" + id_eme_habilidad +
                ", id_emergencia=" + id_emergencia +
                ", id_habilidad=" + id_habilidad +
                '}';
    }

}
