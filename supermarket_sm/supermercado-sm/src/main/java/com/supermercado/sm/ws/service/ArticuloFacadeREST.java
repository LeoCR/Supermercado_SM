package com.supermercado.sm.ws.service;

import com.supermercado.sm.controller.facade.AbstractFacade;
import com.supermercado.sm.model.pojo.Articulo;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author leonardoaranibar
 */
@Stateless
@Path("articulo")
public class ArticuloFacadeREST extends AbstractFacade<Articulo> {

    @PersistenceContext(unitName = "supermercado_sm")
    private EntityManager em;

    public ArticuloFacadeREST() {
        super(Articulo.class);
    }

    @POST
    @Path("crear")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Articulo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Articulo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("eliminar/{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("buscar/{id}")
    @Produces( MediaType.APPLICATION_JSON)
    public Articulo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("verTodos")
    @Override
    @Produces( MediaType.APPLICATION_JSON)
    public List<Articulo> findAll() {
        return super.findAll();
    }
    @GET
    @Path("ver")
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() {
        List<Articulo> articulosNacionales=super.findAll();
        JsonArrayBuilder arregloArticulos= Json.createArrayBuilder();
        for (Articulo articulo : articulosNacionales) {
            JsonObjectBuilder jsonObjectBuilderArticulos = Json.createObjectBuilder();
            JsonObject jsonObject = jsonObjectBuilderArticulos
                    .add("id",articulo.getCodArticl())
                    .add("nombre",articulo.getNombre())
                    .add("precioUnitario",articulo.getPrecUnitar())
                    .add("categoria",articulo.getCategoria())
            .build();
            arregloArticulos.add(jsonObject);
        }
        JsonObjectBuilder jsonObjectBuilderArticulo = Json.createObjectBuilder();
        JsonObject jsonFinal =jsonObjectBuilderArticulo.add("articulos", arregloArticulos).build();
        StringWriter jsonArticulos= new StringWriter();
        JsonWriter jsonWriter =  Json.createWriter(jsonArticulos);
        jsonWriter.writeObject(jsonFinal);
        return jsonArticulos.toString();
        
    }
    @GET
    @Path("barcarPorRango/{from}/{to}")
    @Produces( MediaType.APPLICATION_JSON)
    public List<Articulo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
