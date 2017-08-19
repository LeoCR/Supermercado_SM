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
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByCodDepartmt", query = "SELECT d FROM Departamento d WHERE d.codDepartmt = :codDepartmt")
    , @NamedQuery(name = "Departamento.findByNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Departamento.findByTelef", query = "SELECT d FROM Departamento d WHERE d.telef = :telef")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "cod_departmt")
    private Integer codDepartmt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 240)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 230)
    private String telef;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codDepart", fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;
    @JoinColumn(name = "codSucrsl", referencedColumnName = "cod_sucursl")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursal codSucrsl;

    public Departamento() {
    }

    public Departamento(Integer codDepartmt) {
        this.codDepartmt = codDepartmt;
    }

    public Departamento(Integer codDepartmt, String nombre, String correo, String telef) {
        this.codDepartmt = codDepartmt;
        this.nombre = nombre;
        this.correo = correo;
        this.telef = telef;
    }

    public Integer getCodDepartmt() {
        return codDepartmt;
    }

    public void setCodDepartmt(Integer codDepartmt) {
        this.codDepartmt = codDepartmt;
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

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Sucursal getCodSucrsl() {
        return codSucrsl;
    }

    public void setCodSucrsl(Sucursal codSucrsl) {
        this.codSucrsl = codSucrsl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDepartmt != null ? codDepartmt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.codDepartmt == null && other.codDepartmt != null) || (this.codDepartmt != null && !this.codDepartmt.equals(other.codDepartmt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.Departamento[ codDepartmt=" + codDepartmt + " ]";
    }
    
}
