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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leonardoaranibar
 */
@Entity
@Table(name = "imagen_articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagenArticulo.findAll", query = "SELECT i FROM ImagenArticulo i")
    , @NamedQuery(name = "ImagenArticulo.findByIdImagenArticulo", query = "SELECT i FROM ImagenArticulo i WHERE i.idImagenArticulo = :idImagenArticulo")})
public class ImagenArticulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idImagenArticulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    private String imagen;
    @Lob
    @Size(max = 16777215)
    private String descripcion;
    @JoinColumn(name = "codArtic", referencedColumnName = "cod_articl")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo codArtic;

    public ImagenArticulo() {
    }

    public ImagenArticulo(Integer idImagenArticulo) {
        this.idImagenArticulo = idImagenArticulo;
    }

    public ImagenArticulo(Integer idImagenArticulo, String imagen) {
        this.idImagenArticulo = idImagenArticulo;
        this.imagen = imagen;
    }

    public Integer getIdImagenArticulo() {
        return idImagenArticulo;
    }

    public void setIdImagenArticulo(Integer idImagenArticulo) {
        this.idImagenArticulo = idImagenArticulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Articulo getCodArtic() {
        return codArtic;
    }

    public void setCodArtic(Articulo codArtic) {
        this.codArtic = codArtic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagenArticulo != null ? idImagenArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagenArticulo)) {
            return false;
        }
        ImagenArticulo other = (ImagenArticulo) object;
        if ((this.idImagenArticulo == null && other.idImagenArticulo != null) || (this.idImagenArticulo != null && !this.idImagenArticulo.equals(other.idImagenArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.ImagenArticulo[ idImagenArticulo=" + idImagenArticulo + " ]";
    }
    
}
