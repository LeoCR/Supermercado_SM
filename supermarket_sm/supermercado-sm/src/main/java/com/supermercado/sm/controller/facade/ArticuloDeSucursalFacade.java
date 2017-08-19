/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supermercado.sm.controller.facade;

import com.supermercado.sm.model.pojo.ArticuloDeSucursal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leonardoaranibar
 */
@Stateless
public class ArticuloDeSucursalFacade extends AbstractFacade<ArticuloDeSucursal> {

    @PersistenceContext(unitName = "supermercado_sm")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticuloDeSucursalFacade() {
        super(ArticuloDeSucursal.class);
    }
    
}
