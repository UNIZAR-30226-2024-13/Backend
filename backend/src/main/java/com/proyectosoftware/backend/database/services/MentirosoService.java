package com.proyectosoftware.backend.database.services;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectosoftware.backend.database.entidades.Guarda;
import com.proyectosoftware.backend.database.entidades.MentirosoEntidad;
import com.proyectosoftware.backend.database.entidades.Partida;
import com.proyectosoftware.backend.database.entidades.PartidaId;
import com.proyectosoftware.backend.database.entidades.UsuarioEntidad;
import com.proyectosoftware.backend.database.repository.MentirosoRepository;
import com.proyectosoftware.backend.database.repository.PartidaRepository;
import com.proyectosoftware.backend.database.repository.UsuarioRepository;

@Service
public class MentirosoService {
    
    @Autowired
    private MentirosoRepository mentirosoRepository;

    @Autowired
    private UsuarioRepository UsuarioRepository;

    public MentirosoService() {}

    public List<MentirosoEntidad> getAllMentiroso(){
        return mentirosoRepository.findAll();
    }

    public MentirosoEntidad saveMentiroso(MentirosoEntidad mentiroso){
        return mentirosoRepository.save(mentiroso);
    }

    public Optional<MentirosoEntidad> getMentiroso(String idMentiroso){
        return mentirosoRepository.findById(idMentiroso);
    }

    public void fromJSON(MentirosoEntidad mentirosoEntidad, JSONObject estado) {
        mentirosoEntidad.setId((String) estado.get("ID"));
        mentirosoEntidad.setTurno((Integer) estado.get("turno"));
        mentirosoEntidad.setCartasMesa((String) estado.get("cartas_mesa"));
        mentirosoEntidad.setUltimasCartas((Integer) estado.get("ultimas_cartas"));
        mentirosoEntidad.setNumero((Integer) estado.get("numero_actual"));

        mentirosoEntidad.setActiva((boolean)estado.get("activa"));
        mentirosoEntidad.setPrivada((boolean)estado.get("es_privada"));

        JSONArray usuarioArray = (JSONArray)estado.get("usuarios");
        for (Object object : usuarioArray) {
            JSONObject infoUsuario = (JSONObject) object;
            
            String id = (String) infoUsuario.get("ID");
            UsuarioEntidad usuarioEntidad = UsuarioRepository.findById(id).orElseThrow();
            
            Guarda guarda = new Guarda();
            guarda.setUsuario(usuarioEntidad);
            guarda.setPartida(mentirosoEntidad);

            guarda.setCartas((String) infoUsuario.get("cartas"));
            guarda.setTurnoEnPartida((Integer) infoUsuario.get("turno_en_juego"));
            
            usuarioEntidad.getPartidas().add(guarda);
            mentirosoEntidad.getGuarda().add(guarda);

            UsuarioRepository.save(usuarioEntidad);
        }
    }
}
