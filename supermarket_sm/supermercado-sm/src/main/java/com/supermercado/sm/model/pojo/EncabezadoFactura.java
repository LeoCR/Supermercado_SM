/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supermercado.sm.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leonardoaranibar
 */
@Entity
@Table(name = "encabezado_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncabezadoFactura.findAll", query = "SELECT e FROM EncabezadoFactura e")
    , @NamedQuery(name = "EncabezadoFactura.findByIdEncbzd", query = "SELECT e FROM EncabezadoFactura e WHERE e.idEncbzd = :idEncbzd")
    , @NamedQuery(name = "EncabezadoFactura.findByPrecioTotal", query = "SELECT e FROM EncabezadoFactura e WHERE e.precioTotal = :precioTotal")
    , @NamedQuery(name = "EncabezadoFactura.findByImpuestoDeVenta", query = "SELECT e FROM EncabezadoFactura e WHERE e.impuestoDeVenta = :impuestoDeVenta")
    , @NamedQuery(name = "EncabezadoFactura.findByFechaCompra", query = "SELECT e FROM EncabezadoFactura e WHERE e.fechaCompra = :fechaCompra")})
public class EncabezadoFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_encbzd")
    private Integer idEncbzd;
    @Basic(optional = false)
    @NotNull
    private int precioTotal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal impuestoDeVenta;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @JoinColumn(name = "idCliente", referencedColumnName = "id_client")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idCliente;
    @JoinColumn(name = "empleadAtiend", referencedColumnName = "id_empled")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado empleadAtiend;
    @JoinColumn(name = "noDetallFactr", referencedColumnName = "no_detall_fact")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetalleFactura noDetallFactr;

    public EncabezadoFactura() {
    }

    public EncabezadoFactura(Integer idEncbzd) {
        this.idEncbzd = idEncbzd;
    }

    public EncabezadoFactura(Integer idEncbzd, int precioTotal, BigDecimal impuestoDeVenta, Date fechaCompra) {
        this.idEncbzd = idEncbzd;
        this.precioTotal = precioTotal;
        this.impuestoDeVenta = impuestoDeVenta;
        this.fechaCompra = fechaCompra;
    }

    public Integer getIdEncbzd() {
        return idEncbzd;
    }

    public void setIdEncbzd(Integer idEncbzd) {
        this.idEncbzd = idEncbzd;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public BigDecimal getImpuestoDeVenta() {
        return impuestoDeVenta;
    }

    public void setImpuestoDeVenta(BigDecimal impuestoDeVenta) {
        this.impuestoDeVenta = impuestoDeVenta;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Empleado getEmpleadAtiend() {
        return empleadAtiend;
    }

    public void setEmpleadAtiend(Empleado empleadAtiend) {
        this.empleadAtiend = empleadAtiend;
    }

    public DetalleFactura getNoDetallFactr() {
        return noDetallFactr;
    }

    public void setNoDetallFactr(DetalleFactura noDetallFactr) {
        this.noDetallFactr = noDetallFactr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncbzd != null ? idEncbzd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncabezadoFactura)) {
            return false;
        }
        EncabezadoFactura other = (EncabezadoFactura) object;
        if ((this.idEncbzd == null && other.idEncbzd != null) || (this.idEncbzd != null && !this.idEncbzd.equals(other.idEncbzd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.EncabezadoFactura[ idEncbzd=" + idEncbzd + " ]";
    }
    
}
