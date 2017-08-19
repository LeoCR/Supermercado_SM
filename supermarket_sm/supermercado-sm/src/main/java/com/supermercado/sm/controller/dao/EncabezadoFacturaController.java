package com.supermercado.sm.controller.dao;

import com.supermercado.sm.model.pojo.EncabezadoFactura;
import com.supermercado.sm.controller.facade.util.JsfUtil;
import com.supermercado.sm.controller.facade.util.JsfUtil.PersistAction;
import com.supermercado.sm.controller.facade.EncabezadoFacturaFacade;

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

@Named("encabezadoFacturaController")
@SessionScoped
public class EncabezadoFacturaController implements Serializable {

    @EJB
    private com.supermercado.sm.controller.facade.EncabezadoFacturaFacade ejbFacade;
    private List<EncabezadoFactura> items = null;
    private EncabezadoFactura selected;

    public EncabezadoFacturaController() {
    }

    public EncabezadoFactura getSelected() {
        return selected;
    }

    public void setSelected(EncabezadoFactura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EncabezadoFacturaFacade getFacade() {
        return ejbFacade;
    }

    public EncabezadoFactura prepareCreate() {
        selected = new EncabezadoFactura();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EncabezadoFacturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EncabezadoFacturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EncabezadoFacturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EncabezadoFactura> getItems() {
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

    public EncabezadoFactura getEncabezadoFactura(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<EncabezadoFactura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EncabezadoFactura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = EncabezadoFactura.class)
    public static class EncabezadoFacturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EncabezadoFacturaController controller = (EncabezadoFacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "encabezadoFacturaController");
            return controller.getEncabezadoFactura(getKey(value));
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
            if (object instanceof EncabezadoFactura) {
                EncabezadoFactura o = (EncabezadoFactura) object;
                return getStringKey(o.getIdEncbzd());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EncabezadoFactura.class.getName()});
                return null;
            }
        }

    }

}
