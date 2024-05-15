package com.proyectosoftware.backend.database.entidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.proyectosoftware.backend.database.repository.UsuarioRepository;
import com.proyectosoftware.backend.database.services.UsuarioService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "mentiroso")
@JsonInclude(value = Include.NON_NULL)
public class MentirosoEntidad extends Partida{
    
    
    @Column(name = "cartas_mesa")
    private String cartasMesa;
    
    @Column(name = "numero")
    private int numero;
    
    @Column(name = "ultimasCartas")
    private int cartasUltimaJugada;

    public MentirosoEntidad() {}

    public MentirosoEntidad(String id, String cartasMesa, int numero, int ultimasCartas) {
        super(id);
        this.cartasMesa = cartasMesa;
        this.numero = numero;
        this.cartasUltimaJugada = ultimasCartas;
    }

    public MentirosoEntidad(JSONObject estado) {
        this.setId((String) estado.get("ID"));
        this.setTurno((Integer) estado.get("turno"));
        this.setCartasMesa((String) estado.get("cartas_mesa"));
        this.setUltimasCartas((Integer) estado.get("ultimas_cartas"));
        this.setNumero((Integer) estado.get("numero_actual"));
	}
/* 
    public void fromJSON(JSONObject estado){
        
        this.setId((String) estado.get("ID"));
        this.setTurno((Integer) estado.get("turno"));
        this.setCartasMesa((String) estado.get("cartas_mesa"));
        this.setUltimasCartas((Integer) estado.get("ultimas_cartas"));
        this.setNumero((Integer) estado.get("numero_actual"));

        this.setActiva((boolean)estado.get("activa"));
        this.setPrivada((boolean)estado.get("es_privada"));

        JSONArray usuarioArray = (JSONArray)estado.get("usuarios");
        for (Object object : usuarioArray) {
            JSONObject infoUsuario = (JSONObject) object;
            
            String id = (String) infoUsuario.get("ID");
            UsuarioEntidad usuarioEntidad = usuarioService.getUsuarioById(id).orElseThrow();
            
            Guarda guarda = new Guarda();
            guarda.setUsuario(usuarioEntidad);
            guarda.setPartida(this);

            guarda.setCartas((String) infoUsuario.get("cartas"));
            guarda.setTurnoEnPartida((Integer) infoUsuario.get("turno_en_juego"));
            
            usuarioEntidad.getPartidas().add(guarda);
            this.getGuarda().add(guarda);

            usuarioService.saveUsuario(usuarioEntidad);
        }
    }
    */

    @SuppressWarnings("unchecked")
    public JSONObject toJSON(){
        JSONObject estado = new JSONObject();
        JSONArray usuariosArray = new JSONArray();
        
        for (Guarda guarda : this.getGuarda()) {
            JSONObject usuarioJSON = new JSONObject();
            usuarioJSON.put("ID", guarda.getIdUsuario());
            usuarioJSON.put("turno_en_juego", guarda.getTurnoEnPartida());
            usuarioJSON.put("cartas", guarda.getCartas());
            usuariosArray.add(usuarioJSON);
        }
        estado.put("ID", this.getId());
        estado.put("turno", this.getTurno());
        estado.put("usuarios", usuariosArray);
        estado.put("cartas_mesa", this.getCartasMesa());
        estado.put("ultimas_cartas", this.getCartasUltimaJugada());
        estado.put("numero_actual", this.getNumero());
        estado.put("activa", this.isActiva());
        estado.put("es_privada", this.isPrivada());

        return estado;
    }

	public String getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(String cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCartasUltimaJugada() {
        return cartasUltimaJugada;
    }

    public void setUltimasCartas(int cartasUltimaJugada) {
        this.cartasUltimaJugada = cartasUltimaJugada;
    }
}
