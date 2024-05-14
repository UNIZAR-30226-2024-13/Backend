package com.proyectosoftware.backend.database.controller;

import java.security.KeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyectosoftware.backend.database.ApiResponse;
import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;
import com.proyectosoftware.backend.database.services.LoginService;
import com.proyectosoftware.backend.database.services.SessionService;
import com.proyectosoftware.backend.database.services.UsuarioService;
import com.proyectosoftware.backend.modelo.Usuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private static final String BY_ID = "byId";    
    private static final String BY_NOMBRE = "byNombre";
    private static final String LOGIN = "login";
    private static final String USUARIO = "usuario";
    private static final String NOMBRE = "nombre";
    private static final String EMAIL = "email";
    private static final String PAIS = "pais";
    private static final String HASH_PASSWD = "hashPasswd";
    private static final String SESSION_TOKEN = "sessionToken";

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse<Map<String, Object>> login(@RequestBody Map<String, String> datos){
        try {
            if(!datos.keySet().containsAll(List.of(USUARIO, HASH_PASSWD))){
                throw new KeyException();
            }        
            String nombreUsuario = datos.get(USUARIO);
            String hashPasswd = datos.get(HASH_PASSWD);

            UsuarioEntidad usuario = usuarioService.getUsuarioByName(nombreUsuario)
                .orElseThrow(
                    () -> new NoSuchElementException("El usuario no existed")
                );

            if(loginService.isValid(usuario.getId(), hashPasswd)){
                Map<String, Object> respuesta = new HashMap<>();

                respuesta.put(SESSION_TOKEN, usuario.crearSesion());
                respuesta.put(USUARIO, usuarioService.saveUsuario(usuario));
                
                return new ApiResponse<>(
                    "Session iniciada",
                    true,
                    respuesta
                );
            }else{
                return new ApiResponse<>(
                    "Los credenciales no son correctos",
                    false
                );
            }
            
        } catch (KeyException e) {
            return new ApiResponse<>(
                "Faltan datos",
                false
            );
        } catch (NoSuchElementException e){
            return new ApiResponse<>(
                e.getMessage(),
                false
            );
        } catch (Exception e){
            System.err.println(e.getMessage());
            System.err.println(e.getClass().getName());
            e.printStackTrace();
            return new ApiResponse<>(
                "Error en los datos",
                false
            );
        }
    }

    @DeleteMapping("/logout")
    @ResponseBody
    public ApiResponse<String> logout(@RequestParam String usuarioSesion, @RequestParam String sessionToken){
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                sessionService.deleteSession(idUsuario);
                return new ApiResponse<>(
                    "OK",
                    true
                );
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

    @GetMapping("/getUsuarios")
    @ResponseBody
    public ApiResponse<List<UsuarioEntidad>> getAllUsuarios(@RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                return new ApiResponse<>(
                    "OK",
                    true,
                    usuarioService.getAllUsuarios()
                );
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
        }
    }

    @SuppressWarnings("unchecked")
	@PostMapping("/newUsuario")
    @ResponseBody
    public ApiResponse<UsuarioEntidad> saveUsuario(@RequestBody Map<String, Object> datos) {
        try {
            UsuarioEntidad usuarioEntidad = UsuarioEntidad.newInstance();
            Usuario usuario = null;

            if(!datos.keySet().containsAll(List.of(LOGIN, USUARIO))){
                throw new KeyException();
            }
            
            Map<String, Object> mapaUsuario = (Map<String, Object>)datos.get(USUARIO);
            String hashPasswd = (String)((Map<String, Object>)datos.get(LOGIN)).get(HASH_PASSWD);
            
            if(!mapaUsuario.keySet().containsAll(List.of(NOMBRE,EMAIL,PAIS))){
                throw new KeyException();
            }
            
            if(hashPasswd.isEmpty() || hashPasswd == null){
                throw new KeyException();
            }
            
            usuario = new Usuario(
                (String)mapaUsuario.get(NOMBRE),
                (String)mapaUsuario.get(EMAIL),
                (String)mapaUsuario.get(PAIS)
            );
            usuarioEntidad.setId(usuario.getID());
        
            usuarioEntidad.setNombre((String)mapaUsuario.get(NOMBRE));
            usuarioEntidad.setEmail((String)mapaUsuario.get(EMAIL));
            usuarioEntidad.setPais((String)mapaUsuario.get(PAIS));

            usuarioEntidad.getLogin().setUserID(usuario.getID());
            usuarioEntidad.getLogin().setHashPasswd(hashPasswd);
            
            //loginService.saveLogin(usuarioEntidad.getLogin());
            return new ApiResponse<>(
                "Usuario aniadido con exito",
                true,
                usuarioService.saveUsuario(usuarioEntidad)
            );    
        } catch (DataIntegrityViolationException e) {
            System.err.println(e.getMessage());
            return new ApiResponse<>(
                "El usuario ya existe",
                false
            );
        }
        catch (KeyException e){
            System.err.println(e.getMessage());
            System.err.println(e.getClass().getName());
            return new ApiResponse<>(
                "Faltan datos para crear el usaurio",
                false
            );
        } 
        catch (Exception e){
            System.err.println(e.getMessage());
            System.err.println(e.getClass().getName());
            return new ApiResponse<>(
                "Error en los datos",
                false
            );
        }
    }
                
    @GetMapping("/getUsuario")
    @ResponseBody
    public ApiResponse<UsuarioEntidad> getUsuario(@RequestParam String tipo, @RequestParam String value, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                Optional<UsuarioEntidad> usuario = null;
                if(tipo.equals(BY_ID)){
                    usuario = usuarioService.getUsuarioById(value);
                }else if(tipo.equals(BY_NOMBRE)){
                    usuario = usuarioService.getUsuarioByName(value);
                }else{
                    return new ApiResponse<>(
                        "El tipo de busqueda solo puede se 'byId' o 'byNombre'",
                        false
                    );
                }
            
                if (!usuario.isPresent()) {
                    return new ApiResponse<>(
                        "No existe el usuario'" + value + "'",
                        false
                    );
                }
                return new ApiResponse<>(
                    "Usuario'" + value + "'",
                    true,
                    usuario.get()
                );
            } else {
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
        }
    } 

    @PostMapping("/addAmigo")
    @ResponseBody
    public ApiResponse<UsuarioEntidad> agregarAmigo(@RequestParam String nombreUsuario, @RequestParam String nombreAmigo, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                UsuarioEntidad usuario = usuarioService.agregarAmigo(nombreUsuario, nombreAmigo);
                return new ApiResponse<>(
                    "Amigo '" + nombreAmigo + "' agregado con exito en la lista de amigos de '" + nombreUsuario + "'",
                    true,
                    usuario
                    );
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
                e.getMessage(),
                false
            );
        }
    }

    @DeleteMapping("/deleteAmigo")
    @ResponseBody
    public ApiResponse<UsuarioEntidad> deleteAmigo(@RequestParam String nombreUsuario, @RequestParam String nombreAmigo, @RequestParam String usuarioSesion, @RequestParam String sessionToken) {
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                UsuarioEntidad usuario = usuarioService.borrarAmigo(nombreUsuario, nombreAmigo);
                return new ApiResponse<>(
                    "Amigo '" + nombreAmigo + "' borrado de la lista de amigos de '" + nombreUsuario + "'",
                    true,
                    usuario
                );
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
                e.getMessage(),
                false
            );
        }
    }

    @DeleteMapping("/deleteUsuario")
    @ResponseBody
    public ApiResponse<UsuarioEntidad> deleteUsuario(@RequestParam String tipo, @RequestParam String value, @RequestParam String usuarioSesion, @RequestParam String sessionToken){
        try {
            String idUsuario = usuarioService.getUsuarioByName(usuarioSesion).orElseThrow().getId();
            if(sessionService.getSession(idUsuario).orElseThrow().getSessionToken().equals(sessionToken)){
                if(tipo.equals(BY_ID)){
                    usuarioService.deleteUsuarioById(value);
                }else if(tipo.equals(BY_NOMBRE)){
                    usuarioService.deleteUsuarioByNombre(value);
                }else{
                    return new ApiResponse<>(
                        "El tipo de busqueda solo puede se 'byId' o 'byNombre'",
                        false
                    );
                }
                return new ApiResponse<>(
                    "Usuario '" + value + "' borrado correctamente",
                    true
                );
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
                "Usuario '" + value + "' no existe",
                false
            );
        }
    }
}
