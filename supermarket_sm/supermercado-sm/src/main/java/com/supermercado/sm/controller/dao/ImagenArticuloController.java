package com.supermercado.sm.controller.dao;

import com.supermercado.sm.model.pojo.ImagenArticulo;
import com.supermercado.sm.controller.facade.util.JsfUtil;
import com.supermercado.sm.controller.facade.util.JsfUtil.PersistAction;
import com.supermercado.sm.controller.facade.ImagenArticuloFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("imagenArticuloController")
@SessionScoped
public class ImagenArticuloController implements Serializable {

    @EJB
    private com.supermercado.sm.controller.facade.ImagenArticuloFacade ejbFacade;
    private List<ImagenArticulo> items = null;
    private ImagenArticulo selected;

    public ImagenArticuloController() {
    }

    public ImagenArticulo getSelected() {
        return selected;
    }

    public void setSelected(ImagenArticulo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImagenArticuloFacade getFacade() {
        return ejbFacade;
    }

    public ImagenArticulo prepareCreate() {
        selected = new ImagenArticulo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImagenArticuloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImagenArticuloUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImagenArticuloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ImagenArticulo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ImagenArticulo getImagenArticulo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ImagenArticulo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ImagenArticulo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ImagenArticulo.class)
    public static class ImagenArticuloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagenArticuloController controller = (ImagenArticuloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagenArticuloController");
            return controller.getImagenArticulo(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ImagenArticulo) {
                ImagenArticulo o = (ImagenArticulo) object;
                return getStringKey(o.getIdImagenArticulo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImagenArticulo.class.getName()});
                return null;
            }
        }

    }

}
