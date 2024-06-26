package com.proyectosoftware.backend.database.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.SizeLimitExceededException;

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

    /**
     * Obtiene todos los juegos de Mentiroso disponibles para un usuario.
     * @param usuarioSesion El nombre de usuario que solicita la lista de juegos.
     * @param sessionToken El token de sesión del usuario.
     * @return ApiResponse con una lista de MentirosoEntidad.
     */
    @GetMapping("/getMentirosos")
    public ApiResponse<List<MentirosoEntidad>> getAllMentiroso(@RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            UsuarioEntidad usuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow();
            if(sessionService.getSession(usuario.getId()).orElseThrow().getSessionToken().equals(sessionToken)){
               return new ApiResponse<>(
                    "OK",
                    true,
                    mentirosoService.getAllMentiroso()
                        .stream()
                        .filter(
                            juego->{
                                return !juego.isPrivada() || mentirosoService.estaUsuarioEnPartida(usuario.getId(), juego.getId());
                            }
                        )
                        .toList()
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

    /**
     * Crea un nuevo juego de Mentiroso.
     * @param esPrivada Booleano que indica si el juego es privado o no.
     * @param usuarioSesion El nombre de usuario que crea el juego.
     * @param sessionToken El token de sesión del usuario.
     * @return ApiResponse con el juego creado.
     */
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
                //mentirosoService.saveMentiroso(mentirosoEntidad);
                //usuarioService.saveUsuario(usuario); 
                
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
    
    /**
     * Obtiene un juego de Mentiroso por su ID.
     * @param id El ID del juego a obtener.
     * @param usuarioSesion El nombre de usuario que solicita el juego.
     * @param sessionToken El token de sesión del usuario.
     * @return ApiResponse con el juego solicitado.
     */
    @GetMapping("/getMentiroso/{id}")
    public ApiResponse<MentirosoEntidad> getMentiroso(@PathVariable String id, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        
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

    /**
     * Agrega un usuario a un juego de Mentiroso.
     * @param idJuego El ID del juego al que se agrega el usuario.
     * @param nombreUsuario El nombre del usuario a agregar.
     * @param usuarioSesion El nombre de usuario que realiza la operación.
     * @param sessionToken El token de sesión del usuario.
     * @return ApiResponse con el juego actualizado.
     */
    @PostMapping("/{idJuego}/addUsuario")
    public ApiResponse<MentirosoEntidad> addUsuario(@PathVariable String idJuego, @RequestParam String nombreUsuario, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                try {
                    UsuarioEntidad usuario = usuarioService.getUsuarioByName(nombreUsuario).orElseThrow();
                    if(mentirosoService.estaUsuarioEnPartida(usuario.getId(), idJuego)){
                        return new ApiResponse<>(
                            "El usuario " + nombreUsuario + " ya pertenece al juego",
                            false
                        );
                    }
                    MentirosoEntidad mentirosoEntidad = mentirosoService.getMentiroso(idJuego).orElseThrow();
                    Mentiroso juego = new Mentiroso(mentirosoEntidad.toJSON());
                    juego.nuevoUsuario(usuario.getId());
                    mentirosoService.fromJSON(mentirosoEntidad, juego.guardar());
                    //usuarioService.saveUsuario(usuario);
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
                } catch (SizeLimitExceededException e){
                    return new ApiResponse<>(
                        "El juego esta lleno",
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

    /**
     * Realiza una jugada en un juego de Mentiroso.
     * @param idJuego El ID del juego en el que se realiza la jugada.
     * @param datos Mapa con los datos de la jugada.
     * @param usuarioSesion El nombre de usuario que realiza la jugada.
     * @param sessionToken El token de sesión del usuario.
     * @return ApiResponse con el juego actualizado.
     */
    @PostMapping("/{idJuego}/jugada")
    public ApiResponse<MentirosoEntidad> jugada(@PathVariable String idJuego, @RequestBody Map<String,String> datos, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            UsuarioEntidad usuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow();
            if(sessionService.getSession(usuario.getId()).orElseThrow().getSessionToken().equals(sessionToken)){
                Optional<MentirosoEntidad> mentirosoEntidad = mentirosoService.getMentiroso(idJuego);
                if(mentirosoEntidad.isEmpty()){
                    return new ApiResponse<>(
                        "El juego no existe",
                        false
                    );
                }else if(mentirosoEntidad.get().getGuarda().size() < 4){
                    return new ApiResponse<>(
                        "El juego no ha empezado",
                        false
                    );
                }

                if(!mentirosoService.estaUsuarioEnPartida(usuario.getId(), mentirosoEntidad.get().getId())){
                    return new ApiResponse<>(
                        "El usuario" + usuarioSesion + " no pertenece a esta parida",
                        false
                    );
                }

                if(!datos.keySet().containsAll(List.of("cartas","accion","numero"))){
                    return new ApiResponse<>(
                        "Faltan datos",
                        false
                    );
                }

                MentirosoEntidad juegoEntidad = mentirosoEntidad.get();
                if(juegoEntidad.getTurno() != mentirosoService.buscaUsuarioEnPartida(usuario.getId(), juegoEntidad.getId()).get().getTurnoEnPartida()){
                    return new ApiResponse<>(
                        "El usuario se ha equivocado de turno",
                        false
                    );
                }
                Mentiroso juego = new Mentiroso(juegoEntidad.toJSON());
                if(datos.get("accion").isEmpty()){
                    if(datos.get("numero").isEmpty()){
                        return new ApiResponse<>(
                            "Datos erroneos",
                            false
                        );
                    }
                    
                    juego.jugada(usuario.getId(), juego.parseCartas(datos.get("cartas")), Integer.parseInt(datos.get("numero")));
                } else{
                    if(datos.get("accion").equals("mentir")){
                        juego.jugada(usuario.getId(), juego.parseCartas(datos.get("cartas")), Mentiroso.Accion.MENTIR);
                    }else if (datos.get("accion").equals("levantar")){
                        juego.jugada(usuario.getId(), new ArrayList<>(), Mentiroso.Accion.LEVANTAR);
                    }else{
                        return new ApiResponse<>(
                            "Datos erroneos",
                            false
                        );
                    }
                }
                mentirosoService.fromJSON(juegoEntidad, juego.guardar());
                mentirosoService.saveMentiroso(juegoEntidad);
                return new ApiResponse<>(
                    "Nuevo estado",
                    true,
                    juegoEntidad
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
}