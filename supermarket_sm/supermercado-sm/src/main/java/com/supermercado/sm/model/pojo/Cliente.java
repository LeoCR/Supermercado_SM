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
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdClient", query = "SELECT c FROM Cliente c WHERE c.idClient = :idClient")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
            , @NamedQuery(name = "Cliente.findByCorreo", query = "SELECT c FROM Cliente c WHERE c.correo = :correo")
            , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")
        , @NamedQuery(name = "Cliente.findByNombreDeUsuario", query = "SELECT c FROM Cliente c WHERE c.nombreDeUsuario = :nombreDeUsuario")
    , @NamedQuery(name = "Cliente.findByCedula", query = "SELECT c FROM Cliente c WHERE c.cedula = :cedula")
    , @NamedQuery(name = "Cliente.findByCantidadDeCompras", query = "SELECT c FROM Cliente c WHERE c.cantidadDeCompras = :cantidadDeCompras")})
public class Cliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_client")
    private Integer idClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String nombreDeUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String telefono;
    @Size(max = 240)
    private String cedula;
    private Integer cantidadDeCompras;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<EncabezadoFactura> encabezadoFacturaList;

    public Cliente() {
    }

    public Cliente(Integer idClient) {
        this.idClient = idClient;
    }

    public Cliente(Integer idClient, String nombre) {
        this.idClient = idClient;
        this.nombre = nombre;
    }

    public Cliente(String nombre, String cedula, Integer cantidadDeCompras) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cantidadDeCompras = cantidadDeCompras;
    }
    
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getCantidadDeCompras() {
        return cantidadDeCompras;
    }
    
    public void setCantidadDeCompras(Integer cantidadDeCompras) {
        this.cantidadDeCompras = cantidadDeCompras;
    }

    @XmlTransient
    public List<EncabezadoFactura> getEncabezadoFacturaList() {
        return encabezadoFacturaList;
    }

    public void setEncabezadoFacturaList(List<EncabezadoFactura> encabezadoFacturaList) {
        this.encabezadoFacturaList = encabezadoFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.Cliente[ idClient=" + idClient + " ]";
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
