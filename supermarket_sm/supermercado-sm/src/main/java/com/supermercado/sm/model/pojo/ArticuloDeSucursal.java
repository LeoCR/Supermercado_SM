/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supermercado.sm.model.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leonardoaranibar
 */
@Entity
@Table(name = "articulo_de_sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticuloDeSucursal.findAll", query = "SELECT a FROM ArticuloDeSucursal a")
    , @NamedQuery(name = "ArticuloDeSucursal.findByIdArtSuc", query = "SELECT a FROM ArticuloDeSucursal a WHERE a.idArtSuc = :idArtSuc")
    , @NamedQuery(name = "ArticuloDeSucursal.findByStock", query = "SELECT a FROM ArticuloDeSucursal a WHERE a.stock = :stock")})
public class ArticuloDeSucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idArtSuc;
    private Integer stock;
    @JoinColumn(name = "codArticulo", referencedColumnName = "cod_articl")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo codArticulo;
    @JoinColumn(name = "codSucursl", referencedColumnName = "cod_sucursl")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursal codSucursl;

    public ArticuloDeSucursal() {
    }

    public ArticuloDeSucursal(Integer idArtSuc) {
        this.idArtSuc = idArtSuc;
    }

    public Integer getIdArtSuc() {
        return idArtSuc;
    }

    public void setIdArtSuc(Integer idArtSuc) {
        this.idArtSuc = idArtSuc;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Articulo getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(Articulo codArticulo) {
        this.codArticulo = codArticulo;
    }

    public Sucursal getCodSucursl() {
        return codSucursl;
    }

    public void setCodSucursl(Sucursal codSucursl) {
        this.codSucursl = codSucursl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtSuc != null ? idArtSuc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticuloDeSucursal)) {
            return false;
        }
        ArticuloDeSucursal other = (ArticuloDeSucursal) object;
        if ((this.idArtSuc == null && other.idArtSuc != null) || (this.idArtSuc != null && !this.idArtSuc.equals(other.idArtSuc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.ArticuloDeSucursal[ idArtSuc=" + idArtSuc + " ]";
    }
    
}
