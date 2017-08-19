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
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s")
    , @NamedQuery(name = "Sucursal.findByCodSucursl", query = "SELECT s FROM Sucursal s WHERE s.codSucursl = :codSucursl")
    , @NamedQuery(name = "Sucursal.findByTelef", query = "SELECT s FROM Sucursal s WHERE s.telef = :telef")})
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "cod_sucursl")
    private Integer codSucursl;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    private String telef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codSucrsl", fetch = FetchType.LAZY)
    private List<Departamento> departamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codSucursl", fetch = FetchType.LAZY)
    private List<ArticuloDeSucursal> articuloDeSucursalList;

    public Sucursal() {
    }

    public Sucursal(Integer codSucursl) {
        this.codSucursl = codSucursl;
    }

    public Sucursal(Integer codSucursl, String nombre, String direccion, String telef) {
        this.codSucursl = codSucursl;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telef = telef;
    }

    public Integer getCodSucursl() {
        return codSucursl;
    }

    public void setCodSucursl(Integer codSucursl) {
        this.codSucursl = codSucursl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    @XmlTransient
    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    @XmlTransient
    public List<ArticuloDeSucursal> getArticuloDeSucursalList() {
        return articuloDeSucursalList;
    }

    public void setArticuloDeSucursalList(List<ArticuloDeSucursal> articuloDeSucursalList) {
        this.articuloDeSucursalList = articuloDeSucursalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codSucursl != null ? codSucursl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.codSucursl == null && other.codSucursl != null) || (this.codSucursl != null && !this.codSucursl.equals(other.codSucursl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
