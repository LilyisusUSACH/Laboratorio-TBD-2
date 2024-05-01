package cl.tbd.proyecto.entities;

public class Vol_HabilidadEntity {

    private Long id_vol_habilidad;
    private Long id_voluntario;
    private Long id_habilidad;
    private Long nivel_proficiencia;

    public Vol_HabilidadEntity(Long id, Long id_voluntario, Long id_habilidad, Long nivel_proficiencia) {
        this.id_vol_habilidad = id;
        this.id_voluntario = id_voluntario;
        this.id_habilidad = id_habilidad;
        this.nivel_proficiencia = nivel_proficiencia;
    }

    public Long getId() {
        return id_vol_habilidad;
    }

    public void setId(Long id) {
        this.id_vol_habilidad = id;
    }

    public Long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(Long id_voluntario) {
        this.id_voluntario = id_voluntario;
    }

    public Long getId_habilidad() {
        return id_habilidad;
    }

    public void setId_habilidad(Long id_habilidad) {
        this.id_habilidad = id_habilidad;
    }

    public Long getNivel_proficiencia() {
        return nivel_proficiencia;
    }

    public void setNivel_proficiencia(Long nivel_proficiencia) {
        this.nivel_proficiencia = nivel_proficiencia;
    }

}
