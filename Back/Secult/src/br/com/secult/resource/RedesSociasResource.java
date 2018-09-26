/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.secult.resource;

import br.com.secult.dao.CadartDao;
import br.com.secult.dao.RedesSociasDao;
import br.com.secult.model.Cadart;
import br.com.secult.model.RedesSociais;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author David
 */
@Path("/redes")
public class RedesSociasResource {

    @GET
    @Path("/inserirRedes/{nome_rede}&{nome_link}&{id_cadart}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirRede(@PathParam("nome_rede") String nomeRede, @PathParam("nome_link") String nomeLink, @PathParam("id_cadart") int idCadart) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        
        RedesSociais redes = new RedesSociais();
        redes.setNomeRede(nomeRede);
        redes.setNomeLink(nomeLink);
        redes.setIdCadart(idCadart);
        
        RedesSociasDao redesDao = new RedesSociasDao();
        
        if(redesDao.inserirRede(redes)){
            return Response.ok("{\"status\":\"ok\", \"nome_rede\":\"" + redes.getNomeRede()+ "\"}").header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
        } else {
            return Response.ok("{\"status\":\"erro\"}").build();
        }
        }
    @GET
    @Path("/listarRedes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRedesSociais() throws SQLException, Exception {

        RedesSociasDao redesDao = new RedesSociasDao();
        List<RedesSociais> redes = redesDao.listarRedes();

        Gson gson = new GsonBuilder().create();

        JsonArray ArrayUsarios = gson.toJsonTree(redes).getAsJsonArray();

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("rede social", ArrayUsarios);

        return Response.ok(jsonObject.toString()).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS").header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();

    }
    }
