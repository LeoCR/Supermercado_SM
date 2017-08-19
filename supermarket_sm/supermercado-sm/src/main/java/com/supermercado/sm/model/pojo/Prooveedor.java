/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supermercado.sm.model.pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leonardoaranibar
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prooveedor.findAll", query = "SELECT p FROM Prooveedor p")
    , @NamedQuery(name = "Prooveedor.findByCodProvedr", query = "SELECT p FROM Prooveedor p WHERE p.codProvedr = :codProvedr")
    , @NamedQuery(name = "Prooveedor.findByNombre", query = "SELECT p FROM Prooveedor p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Prooveedor.findByCorreo", query = "SELECT p FROM Prooveedor p WHERE p.correo = :correo")
    , @NamedQuery(name = "Prooveedor.findByTelef", query = "SELECT p FROM Prooveedor p WHERE p.telef = :telef")
    , @NamedQuery(name = "Prooveedor.findByDireccion", query = "SELECT p FROM Prooveedor p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Prooveedor.findByWeb", query = "SELECT p FROM Prooveedor p WHERE p.web = :web")})
public class Prooveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "cod_provedr")
    private Integer codProvedr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String telef;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String logo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    private String web;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codProvedor", fetch = FetchType.LAZY)
    private List<Articulo> articuloList;

    public Prooveedor() {
    }

    public Prooveedor(Integer codProvedr) {
        this.codProvedr = codProvedr;
    }

    public Prooveedor(Integer codProvedr, String nombre, String correo, String telef, String direccion, String logo, String web) {
        this.codProvedr = codProvedr;
        this.nombre = nombre;
        this.correo = correo;
        this.telef = telef;
        this.direccion = direccion;
        this.logo = logo;
        this.web = web;
    }

    public Integer getCodProvedr() {
        return codProvedr;
    }

    public void setCodProvedr(Integer codProvedr) {
        this.codProvedr = codProvedr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @XmlTransient
    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProvedr != null ? codProvedr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prooveedor)) {
            return false;
        }
        Prooveedor other = (Prooveedor) object;
        if ((this.codProvedr == null && other.codProvedr != null) || (this.codProvedr != null && !this.codProvedr.equals(other.codProvedr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre ;
    }
    
}
