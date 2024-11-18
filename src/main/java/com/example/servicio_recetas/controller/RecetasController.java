package com.example.servicio_recetas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.servicio_recetas.DTO.ComentarioDTO;
import com.example.servicio_recetas.model.Comentarios;
import com.example.servicio_recetas.model.RecetaDetalle;
import com.example.servicio_recetas.model.RecetaMedia;
import com.example.servicio_recetas.model.Recetas;
import com.example.servicio_recetas.service.ComentariosService;
import com.example.servicio_recetas.service.RecetasService;

@RestController
@RequestMapping("/recetas")
public class RecetasController {

    private final RecetasService recetasService;
    private final ComentariosService comentariosService;

    public RecetasController(RecetasService recetasService, ComentariosService comentariosService) {
        this.recetasService = recetasService;
        this.comentariosService = comentariosService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recetas> getReceta(@PathVariable Long id) {
        Optional<Recetas> recetaExistente = recetasService.getRecetaById(id);

        if (recetaExistente.isPresent()) {
            return ResponseEntity.ok(recetaExistente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Recetas>> getAllRecetas() {
        List<Recetas> libros = recetasService.getAllRecetas();
        return ResponseEntity.ok(libros);
    }   
    
    
    @GetMapping("/{id}/detalle")
    public RecetaDetalle getRecetaDetalle(@PathVariable Long id) {
        Optional<RecetaDetalle> detalle = recetasService.getDetalleById(id);
        if (detalle.isPresent()) {
            return detalle.get();
        } else {
            throw new RuntimeException("Receta no encontrada con el ID: " + id);
        }
    } 

    @PostMapping("/{id}/media")
    public ResponseEntity<String> uploadMedia(
            @PathVariable Long id,
            @RequestParam("tipo") String tipo,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "youtubeUrl", required = false) String youtubeUrl) {

        try {
            if (tipo.equals("foto") || tipo.equals("video")) {
                if (file == null) {
                    return ResponseEntity.badRequest().body("Debe subir un archivo para el tipo 'foto' o 'video'.");
                }
                recetasService.saveMedia(id, tipo, file);
            } else if (tipo.equals("youtube")) {
                if (youtubeUrl == null || youtubeUrl.isEmpty()) {
                    return ResponseEntity.badRequest().body("Debe proporcionar un enlace de YouTube.");
                }
                recetasService.saveYoutubeLink(id, youtubeUrl);
            } else {
                return ResponseEntity.badRequest().body("El tipo debe ser 'foto', 'video' o 'youtube'.");
            }

            return ResponseEntity.ok("Media subida con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir el archivo: " + e.getMessage());
        }
    }


    @GetMapping("/{recetaId}/media")
    public ResponseEntity<Map<String, List<String>>> obtenerMedios(@PathVariable Long recetaId) {
        List<RecetaMedia> medios = recetasService.getMediaByRecetaId(recetaId);

        // Filtrar imágenes
        List<String> imagenes = medios.stream()
                .filter(media -> media.getTipo().equalsIgnoreCase("foto"))
                .map(media -> "http://localhost:8087/recetas/" + recetaId + "/media/" + media.getMediaId() + "/imagen")
                .toList();

        // Filtrar videos
        List<String> videos = medios.stream()
                .filter(media -> media.getTipo().equalsIgnoreCase("video"))
                .map(media -> "http://localhost:8087/recetas/" + recetaId + "/media/" + media.getMediaId() + "/video")
                .toList();

        // Filtrar enlaces de YouTube (usando la nueva columna youtubeUrl)
        List<String> youtubeLinks = medios.stream()
                .filter(media -> media.getTipo().equalsIgnoreCase("youtube") && media.getYoutubeUrl() != null)
                .map(RecetaMedia::getYoutubeUrl)
                .toList();

        // Crear un mapa con los resultados
        Map<String, List<String>> resultado = new HashMap<>();
        resultado.put("imagenes", imagenes);
        resultado.put("videos", videos);
        resultado.put("youtubeLinks", youtubeLinks);

        return ResponseEntity.ok(resultado);
    }




    @GetMapping("/{recetaId}/media/{mediaId}/imagen")
    public ResponseEntity<byte[]> obtenerImagenPorMediaId(@PathVariable Long recetaId, @PathVariable Long mediaId) {
        // Busca el media con el ID proporcionado
        Optional<RecetaMedia> mediaOpt = recetasService.getMediaById(mediaId);

        // Verifica si el media pertenece a la receta y si es una imagen
        if (mediaOpt.isPresent() && mediaOpt.get().getRecetaId().equals(recetaId) && 
            "foto".equalsIgnoreCase(mediaOpt.get().getTipo())) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Ajusta según el tipo de contenido
                    .body(mediaOpt.get().getContenido());
        } else {
            return ResponseEntity.notFound().build(); // No se encontró o no es válido
        }
    }


    @PostMapping("/{id}/comentarios")
    public ResponseEntity<String> agregarComentario(
        @PathVariable Long id,
        @Validated @RequestBody ComentarioDTO comentarioDto) {

        // Convierte el DTO en una entidad de dominio
        Comentarios nuevoComentario = new Comentarios();
        nuevoComentario.setRecetaId(id); // ID de la receta desde la URL
        nuevoComentario.setUsuario(comentarioDto.getUsuario());
        nuevoComentario.setComentario(comentarioDto.getComentario());
        nuevoComentario.setPuntuacion(comentarioDto.getPuntuacion());

        // Llama al servicio para guardar el comentario
        comentariosService.crearComentario(nuevoComentario);

        return ResponseEntity.ok("Comentario agregado exitosamente");
    }


    // Obtener comentarios de una receta
    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<Comentarios>> obtenerComentarios(@PathVariable Long id) {
        List<Comentarios> comentarios = comentariosService.obtenerComentariosPorReceta(id);
        return ResponseEntity.ok(comentarios);
    }

}

