package cl.tbd.proyecto.controllers;

import cl.tbd.proyecto.entities.TareaEntity;
import cl.tbd.proyecto.service.TareaService;
import cl.tbd.proyecto.service.UsuarioService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tarea")
public class TareaController {
    @Autowired
    TareaService tareaService;
    @Autowired
    UsuarioService usuarioService;
    @GetMapping("")
    public ResponseEntity<?> getAllTareas(
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestHeader(value = "Authorization", required = false) String token
    ){

        String actualUser= usuarioService.getUser(token);


        if(size!=null){
            return ResponseEntity.ok(tareaService.getPageTareas(size, Objects.requireNonNullElse(page, 1)));
        }
        return ResponseEntity.ok(tareaService.getAllTareas());
    }

    @GetMapping("/id")
    public ResponseEntity<?> getTareaById(
            @RequestParam(value = "id") Long id,
            @RequestHeader(value = "Authorization", required = false) String token
    ){
        String actualUser= usuarioService.getUser(token);
        return ResponseEntity.ok(tareaService.getTareaByID(id));
    }


    @PutMapping("/addHabilidades")
    public ResponseEntity<?> addHabilidadesToTarea(
            @RequestParam("id_tarea") Long id_tarea,
            @RequestBody JsonNode id_habilidades,
            @RequestHeader(value = "Authorization",required = false) String token){

        String actualUser= usuarioService.getUser(token);
        List<String> numerosCadenas = Arrays.asList(id_habilidades.get("id_habilidades").asText().split(","));
        List<Long> numerosLong = numerosCadenas.stream()
                .map(Long::parseLong)
                .toList();

        return ResponseEntity.ok( tareaService.addHabilidadesToTarea(tareaService.getTareaByID(id_tarea), numerosLong,actualUser) );
    }

    @PostMapping("")
    public ResponseEntity<?> postTarea(
            @RequestBody TareaEntity tarea,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        TareaEntity tareaEntity = tareaService.createTarea(tarea,actualUser);
        if (tareaEntity!=null)
            return ResponseEntity.ok(tareaEntity);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("")
    public ResponseEntity<TareaEntity> updateTarea(
            @RequestBody TareaEntity tarea,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser = usuarioService.getUser(token);
        TareaEntity updatedTarea = tareaService.updateTarea(tarea,actualUser);
        if (updatedTarea != null) {
            return ResponseEntity.ok(updatedTarea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteTarea(
            @RequestParam("id") Long id,
            @RequestHeader(value = "Authorization",required = false) String token) {

        String actualUser= usuarioService.getUser(token);


        if(tareaService.deleteTarea(id,actualUser)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/emergencia")
    public ResponseEntity<?> getTareasByEmergencia(
            @RequestParam("id_emergencia") Long id_emergencia,
            @RequestHeader(value = "Authorization",required = false) String token){

        String actualUser= usuarioService.getUser(token);


        return ResponseEntity.ok(tareaService.getTareasByEmergencia(id_emergencia));
    }
}
