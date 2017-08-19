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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByIdEmpled", query = "SELECT e FROM Empleado e WHERE e.idEmpled = :idEmpled")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByRole", query = "SELECT e FROM Empleado e WHERE e.role = :role")
    , @NamedQuery(name = "Empleado.findBySalario", query = "SELECT e FROM Empleado e WHERE e.salario = :salario")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_empled")
    private Integer idEmpled;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String role;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String correo;
    @Basic(optional = false)
    @NotNull
    private float salario;
    @JoinColumn(name = "codDepart", referencedColumnName = "cod_departmt")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamento codDepart;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadAtiend", fetch = FetchType.LAZY)
    private List<EncabezadoFactura> encabezadoFacturaList;

    public Empleado() {
    }

    public Empleado(Integer idEmpled) {
        this.idEmpled = idEmpled;
    }

    public Empleado(Integer idEmpled, String nombre, String clave, String role, String correo, float salario) {
        this.idEmpled = idEmpled;
        this.nombre = nombre;
        this.clave = clave;
        this.role = role;
        this.correo = correo;
        this.salario = salario;
    }

    public Integer getIdEmpled() {
        return idEmpled;
    }

    public void setIdEmpled(Integer idEmpled) {
        this.idEmpled = idEmpled;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Departamento getCodDepart() {
        return codDepart;
    }

    public void setCodDepart(Departamento codDepart) {
        this.codDepart = codDepart;
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
        hash += (idEmpled != null ? idEmpled.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpled == null && other.idEmpled != null) || (this.idEmpled != null && !this.idEmpled.equals(other.idEmpled))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.Empleado[ idEmpled=" + idEmpled + " ]";
    }
    
}
