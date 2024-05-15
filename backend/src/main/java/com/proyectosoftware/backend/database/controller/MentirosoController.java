package com.proyectosoftware.backend.database.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.proyectosoftware.backend.database.ApiResponse;
import com.proyectosoftware.backend.database.entidades.Guarda;
import com.proyectosoftware.backend.database.entidades.MentirosoEntidad;
import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;
import com.proyectosoftware.backend.database.services.MentirosoService;
import com.proyectosoftware.backend.database.services.PartidaService;
import com.proyectosoftware.backend.database.services.SessionService;
import com.proyectosoftware.backend.database.services.UsuarioService;
import com.proyectosoftware.backend.modelo.juegos.Mentiroso;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/juegos/mentiroso")
public class MentirosoController{
    
    @Autowired
    private MentirosoService mentirosoService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getMentirosos")
    public List<MentirosoEntidad> getAllMentiroso() {
        return mentirosoService.getAllMentiroso();
    }

    @PostMapping("/addMentiroso")
    public ApiResponse<MentirosoEntidad> newMentiroso(@RequestParam boolean esPrivada, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            UsuarioEntidad usuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow();
            if(sessionService.getSession(usuario.getId()).orElseThrow().getSessionToken().equals(sessionToken)){
                Mentiroso juego = new Mentiroso();
                MentirosoEntidad mentirosoEntidad = new MentirosoEntidad(juego.guardar());
                
                mentirosoEntidad.setActiva(true);
                mentirosoEntidad.setPrivada(esPrivada);

                Guarda guarda = new Guarda();
                guarda.setUsuario(usuario);
                guarda.setPartida(mentirosoEntidad);
                usuario.getPartidas().add(guarda);
                mentirosoEntidad.getGuarda().add(guarda);
                
                partidaService.savePartida(mentirosoEntidad);
                mentirosoService.saveMentiroso(mentirosoEntidad);
                usuarioService.saveUsuario(usuario); 
                
                return new ApiResponse<>(
                    "OK",
                    true,
                    mentirosoEntidad
                );
            } else{
                return new ApiResponse<>(
                    "Credenciales malos",
                    false
                );
            }
        } catch (NoSuchElementException e) {
            return new ApiResponse<>(
                "El usuario ha iniciado sesion",
                false
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(
                e.getMessage(),
                false
            );
        }
    }
    
    @GetMapping("/getMentiroso/{id}")
    public ApiResponse<MentirosoEntidad> getMentiroso(@PathVariable String id, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        /**
         * JSONObject estado = juego.guardar();
                
                estado.put("ID", this.id);
                estado.put("turno", this.turno);
                estado.put("usuarios", usuariosArray);
                estado.put("cartas_mesa", cartasToString(this.cartasMesa));
                estado.put("ultimas_cartas", this.cartasUltimaJugada);
                estado.put("numero_actual", this.numeroActual);

                _____
            JSONArray usuarioArray = (JSONArray)estado.get("usuarios");
                for (Object object : usuarioArray) {
                    JSONObject infoUsuario = (JSONObject) object;
                
                    mentirosoEntidad.getGuarda().
                    String id = (String) infoUsuario.get("ID");
                    int orden = (Integer) infoUsuario.get("turno_en_juego");
                    String cartasString = (String) infoUsuario.get("cartas");

                    this.usuarios.put(orden, null);
                    this.cartasUsuarios.put(orden, baraja.parsearCartas(cartasString));
                }
         */
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                try {
                    return new ApiResponse<>(
                        "OK",
                        true,
                        mentirosoService.getMentiroso(id).orElseThrow()
                    );
                    
                } catch (NoSuchElementException e) {
                    return new ApiResponse<>(
                        "El juego " + id + " no existe",
                        false
                    );
                }
            } else{
                return new ApiResponse<>(
                    "Credenciales malos",
                    false
                );
            }
        } catch (NoSuchElementException e) {
            return new ApiResponse<>(
                "El usuario no ha iniciado sesion",
                false
            );
        } catch (Exception e) {
            return new ApiResponse<>(
                "El usuario ha iniciado sesion",
                false
            );
        }
    }

    @PostMapping("/{idJuego}/addUsuario")
    public ApiResponse<MentirosoEntidad> addUsuario(@PathVariable String idJuego, @RequestParam String nombreUsuario, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            UsuarioEntidad usuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow();
            if(sessionService.getSession(usuario.getId()).orElseThrow().getSessionToken().equals(sessionToken)){
                try {
                    usuario = usuarioService.getUsuarioByName(nombreUsuario).orElseThrow();
                    MentirosoEntidad mentirosoEntidad = mentirosoService.getMentiroso(idJuego).orElseThrow();
                    Mentiroso juego = new Mentiroso(mentirosoEntidad.toJSON());
                    juego.nuevoUsuario(usuario.getId());
                    mentirosoService.fromJSON(mentirosoEntidad, juego.guardar());
                    usuarioService.saveUsuario(usuario);
                    mentirosoService.saveMentiroso(mentirosoEntidad);

                    return new ApiResponse<>(
                        "Usuario añadido",
                        true,
                        mentirosoEntidad
                    );
                } catch (NoSuchElementException e) {
                    return new ApiResponse<>(
                        "Juego no encontrado",
                        false
                    );
                }
            } else{
                return new ApiResponse<>(
                    "Credenciales malos",
                    false
                );
            }
        } catch (NoSuchElementException e) {
            return new ApiResponse<>(
                "El usuario no ha iniciado sesion",
                false
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse<>(
                e.getMessage(),
                false
            );
        }
    }
    



    @GetMapping("/test")
    public String getTest() {
        return "Yes";
    }
}