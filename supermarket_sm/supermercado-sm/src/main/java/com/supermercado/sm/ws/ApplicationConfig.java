package com.supermercado.sm.ws;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) { 
        resources.add(com.supermercado.sm.ws.service.ArticuloFacadeREST.class);
        resources.add(com.supermercado.sm.ws.service.ClienteFacadeREST.class);
        resources.add(com.supermercado.sm.ws.service.ProoveedorFacadeREST.class);
    }
    
}
