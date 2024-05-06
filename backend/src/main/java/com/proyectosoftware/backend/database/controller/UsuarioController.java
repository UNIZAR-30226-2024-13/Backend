package com.proyectosoftware.backend.database.controller;

import java.security.KeyException;

import java.util.List;
import java.util.Map;
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
import com.proyectosoftware.backend.database.services.UsuarioService;
import com.proyectosoftware.backend.modelo.Usuario;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private static final String BY_ID = "byId";    
    private static final String BY_NOMBRE = "byNombre";
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/getUsuarios")
    @ResponseBody
    public ApiResponse<List<UsuarioEntidad>> getAllUsuarios() {
        return new ApiResponse<>(
            "OK",
            true,
            usuarioService.getAllUsuarios()
        );
    }

    @SuppressWarnings("unchecked")
	@PostMapping("/newUsuario")
    @ResponseBody
    public ApiResponse<UsuarioEntidad> saveUsuario(@RequestBody Map<String, Object> datos) {
        try {
            UsuarioEntidad usuarioEntidad = UsuarioEntidad.newInstance();
            Usuario usuario = null;

            if(!datos.keySet().containsAll(List.of("login", "usuario"))){
                throw new KeyException();
            }
            
            Map<String, Object> mapaUsuario = (Map<String, Object>)datos.get("usuario");
            String hashPasswd = (String)((Map<String, Object>)datos.get("login")).get("hashPasswd");
            
            if(!mapaUsuario.keySet().containsAll(List.of("nombre","email","pais"))){
                throw new KeyException();
            }
            
            if(hashPasswd.isEmpty() || hashPasswd == null){
                throw new KeyException();
            }
            
            usuario = new Usuario(
                (String)mapaUsuario.get("nombre"),
                (String)mapaUsuario.get("email"),
                (String)mapaUsuario.get("pais")
            );
            usuarioEntidad.setId(usuario.getID());
        
            usuarioEntidad.setNombre((String)mapaUsuario.get("nombre"));
            usuarioEntidad.setEmail((String)mapaUsuario.get("email"));
            usuarioEntidad.setPais((String)mapaUsuario.get("pais"));

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
    public ApiResponse<UsuarioEntidad> getUsuario(@RequestParam String tipo, @RequestParam String value) {
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
    } 

    @PostMapping("/addAmigo")
    @ResponseBody
    public ApiResponse<UsuarioEntidad> agregarAmigo(@RequestParam String nombreUsuario, @RequestParam String nombreAmigo) {
        try {
            UsuarioEntidad usuario = usuarioService.agregarAmigo(nombreUsuario, nombreAmigo);
            return new ApiResponse<>(
                "Amigo '" + nombreAmigo + "' agregado con exito en la lista de amigos de '" + nombreUsuario + "'",
                true,
                usuario
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
    public ApiResponse<UsuarioEntidad> deleteAmigo(@RequestParam String nombreUsuario, @RequestParam String nombreAmigo) {
        try {
            UsuarioEntidad usuario = usuarioService.borrarAmigo(nombreUsuario, nombreAmigo);
            return new ApiResponse<>(
                "Amigo '" + nombreAmigo + "' borrado de la lista de amigos de '" + nombreUsuario + "'",
                true,
                usuario
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
    public ApiResponse<UsuarioEntidad> deleteUsuario(@RequestParam String tipo, @RequestParam String value){
        try {
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
        } catch (Exception e) {
            return new ApiResponse<>(
                "Usuario '" + value + "' no existe",
                false
            );
        }
    }
}
