package cl.tbd.proyecto.service;

import cl.tbd.proyecto.controllers.DTO.LoginDTO;
import cl.tbd.proyecto.entities.UsuarioEntity;
import cl.tbd.proyecto.entities.VoluntarioEntity;
import cl.tbd.proyecto.repositories.UsuarioRepository;
import cl.tbd.proyecto.repositories.VoluntarioRepository;
import cl.tbd.proyecto.utils.JwtUtils;
import cl.tbd.proyecto.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    VoluntarioRepository voluntarioRepository;

    @Autowired
    JwtUtils jwtUtils;

    public UsuarioEntity registerUser(UsuarioEntity usuario, String actualUser) {
        // Verificar si el usuario ya está registrado
        UsuarioEntity existingUser = usuarioRepository.findByUsername(usuario.getUsername());
        if (existingUser != null) {
            return null;
        }
        // Hashear la contraseña antes de guardar el usuario
        String hashedPassword = EncryptUtils.hashPassword(usuario.getPassword());
        usuario.setPassword(hashedPassword); // Setear la contraseña hasheada

        return usuarioRepository.create(usuario, actualUser); // Asumiendo que el método create() existe y es apropiado
    }

    public String login(LoginDTO loginRequest){
        UsuarioEntity user = usuarioRepository.findByUsername(loginRequest.getUsername());
        if(user != null && EncryptUtils.comparePassword(user.getPassword(), loginRequest.getPassword())){
            // Si las credenciales son correctas, generar y devolver el token JWT
            return jwtUtils.generateToken(user);
        }else{
            // Si las credenciales no son correctas, devolver null o manejar el error TODO
            return null;
        }
    }

    public String getUser(String token){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            if (jwtUtils.validateToken(jwtToken)) {
                return jwtUtils.extractUsername(jwtToken);
            }
        }
        return null;
    }

    public String getRutByUser(String token){
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            if (jwtUtils.validateToken(jwtToken)) {
                return voluntarioRepository.findByRut(jwtUtils.extractUsername(jwtToken)).getNombre();
            }
        }
        return null;
    }
}
