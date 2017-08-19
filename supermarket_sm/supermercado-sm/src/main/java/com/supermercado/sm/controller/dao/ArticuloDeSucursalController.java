package com.supermercado.sm.controller.dao;

import com.supermercado.sm.model.pojo.ArticuloDeSucursal;
import com.supermercado.sm.controller.facade.util.JsfUtil;
import com.supermercado.sm.controller.facade.util.JsfUtil.PersistAction;
import com.supermercado.sm.controller.facade.ArticuloDeSucursalFacade;

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

@Named("articuloDeSucursalController")
@SessionScoped
public class ArticuloDeSucursalController implements Serializable {

    @EJB
    private com.supermercado.sm.controller.facade.ArticuloDeSucursalFacade ejbFacade;
    private List<ArticuloDeSucursal> items = null;
    private ArticuloDeSucursal selected;

    public ArticuloDeSucursalController() {
    }

    public ArticuloDeSucursal getSelected() {
        return selected;
    }

    public void setSelected(ArticuloDeSucursal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ArticuloDeSucursalFacade getFacade() {
        return ejbFacade;
    }

    public ArticuloDeSucursal prepareCreate() {
        selected = new ArticuloDeSucursal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ArticuloDeSucursalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ArticuloDeSucursalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ArticuloDeSucursalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ArticuloDeSucursal> getItems() {
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

    public ArticuloDeSucursal getArticuloDeSucursal(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ArticuloDeSucursal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ArticuloDeSucursal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ArticuloDeSucursal.class)
    public static class ArticuloDeSucursalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticuloDeSucursalController controller = (ArticuloDeSucursalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articuloDeSucursalController");
            return controller.getArticuloDeSucursal(getKey(value));
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
            if (object instanceof ArticuloDeSucursal) {
                ArticuloDeSucursal o = (ArticuloDeSucursal) object;
                return getStringKey(o.getIdArtSuc());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ArticuloDeSucursal.class.getName()});
                return null;
            }
        }

    }

}
