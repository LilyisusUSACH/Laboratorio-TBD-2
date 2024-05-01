package cl.tbd.proyecto.entities;

public class Usuario_RolEntity {
    private Long usuarioId;
    private Long rolId;

    public Usuario_RolEntity() {
    }

    // Constructor, getters y setters.

    public Usuario_RolEntity(Long usuarioId, Long rolId) {
        this.usuarioId = usuarioId;
        this.rolId = rolId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

}
