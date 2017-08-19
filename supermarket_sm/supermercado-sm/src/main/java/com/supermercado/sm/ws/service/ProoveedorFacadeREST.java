package com.supermercado.sm.ws.service;
import com.supermercado.sm.controller.facade.AbstractFacade;
import com.supermercado.sm.model.pojo.Prooveedor;
import java.io.StringWriter;
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
@Path("prooveedor")
public class ProoveedorFacadeREST extends AbstractFacade<Prooveedor> { 
    @PersistenceContext(unitName = "supermercado_sm")
    private EntityManager em; 
    public ProoveedorFacadeREST() {
        super(Prooveedor.class);
    } 
    @POST
    @Path("crear")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Prooveedor entity) {
        super.create(entity);
    } 
    @PUT
    @Path("editar/{id}")
    @Consumes( MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Prooveedor entity) {
        super.edit(entity);
    } 
    @DELETE
    @Path("eliminar/{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    } 
    @GET
    @Path("buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prooveedor find(@PathParam("id") Integer id) {
        return super.find(id);
    } 
    @GET
    @Path("verTodos")
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prooveedor> findAll() {
        return super.findAll();
    }
    @GET
    @Path("ver")
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() {
        List<Prooveedor> prooveedores=super.findAll();
        JsonArrayBuilder arregloProoveedores= Json.createArrayBuilder();
        for (Prooveedor prooveedor : prooveedores) {
            JsonObjectBuilder jsonObjectBuilderProoveedores = Json.createObjectBuilder();
            JsonObject jsonObject = jsonObjectBuilderProoveedores
                    .add("nombre", prooveedor.getNombre())
                    .add("logo", prooveedor.getLogo())
                    .add("webSite", prooveedor.getWeb())
                    .add("telefono", prooveedor.getTelef())
                    .add("email", prooveedor.getCorreo())
                    .build();
            arregloProoveedores.add(jsonObject);
        }
        JsonObjectBuilder jsonObjectBuilderProoveedor = Json.createObjectBuilder();
        JsonObject jsonFinal =jsonObjectBuilderProoveedor.add("prooveedores",arregloProoveedores).build();
        StringWriter jsonProoveedores = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(jsonProoveedores);
        jsonWriter.writeObject(jsonFinal);
        return jsonProoveedores.toString();
    }
    @GET
    @Path("barcarPorRango/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prooveedor> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    } 
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @GET
    @Path("totalDeProoveedores")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject buildJsonDocument() {
        String totalDeProoveedores=String.valueOf(super.count());
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("totalDeProoveedores", totalDeProoveedores)
                .build();
        return jsonObject;
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
