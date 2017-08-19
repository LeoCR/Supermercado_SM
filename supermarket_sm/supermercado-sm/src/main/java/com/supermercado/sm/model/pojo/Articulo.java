/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supermercado.sm.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a")
    , @NamedQuery(name = "Articulo.findByCodArticl", query = "SELECT a FROM Articulo a WHERE a.codArticl = :codArticl")
    , @NamedQuery(name = "Articulo.findByPrecUnitar", query = "SELECT a FROM Articulo a WHERE a.precUnitar = :precUnitar")
    , @NamedQuery(name = "Articulo.findByImpuesto", query = "SELECT a FROM Articulo a WHERE a.impuesto = :impuesto")
    , @NamedQuery(name = "Articulo.findByCategoria", query = "SELECT a FROM Articulo a WHERE a.categoria = :categoria")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "cod_articl")
    private Integer codArticl;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prec_unitar")
    private short precUnitar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal impuesto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 230)
    private String categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codArticulo", fetch = FetchType.LAZY)
    private List<DetalleFactura> detalleFacturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codArticulo", fetch = FetchType.LAZY)
    private List<ArticuloDeSucursal> articuloDeSucursalList;
    @JoinColumn(name = "codProvedor", referencedColumnName = "cod_provedr")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Prooveedor codProvedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codArtic", fetch = FetchType.LAZY)
    private List<ImagenArticulo> imagenArticuloList;

    public Articulo() {
    }

    public Articulo(Integer codArticl) {
        this.codArticl = codArticl;
    }

    public Articulo(Integer codArticl, String nombre, short precUnitar, BigDecimal impuesto, String categoria) {
        this.codArticl = codArticl;
        this.nombre = nombre;
        this.precUnitar = precUnitar;
        this.impuesto = impuesto;
        this.categoria = categoria;
    }

    public Integer getCodArticl() {
        return codArticl;
    }

    public void setCodArticl(Integer codArticl) {
        this.codArticl = codArticl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getPrecUnitar() {
        return precUnitar;
    }

    public void setPrecUnitar(short precUnitar) {
        this.precUnitar = precUnitar;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @XmlTransient
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    @XmlTransient
    public List<ArticuloDeSucursal> getArticuloDeSucursalList() {
        return articuloDeSucursalList;
    }

    public void setArticuloDeSucursalList(List<ArticuloDeSucursal> articuloDeSucursalList) {
        this.articuloDeSucursalList = articuloDeSucursalList;
    }

    public Prooveedor getCodProvedor() {
        return codProvedor;
    }

    public void setCodProvedor(Prooveedor codProvedor) {
        this.codProvedor = codProvedor;
    }

    @XmlTransient
    public List<ImagenArticulo> getImagenArticuloList() {
        return imagenArticuloList;
    }

    public void setImagenArticuloList(List<ImagenArticulo> imagenArticuloList) {
        this.imagenArticuloList = imagenArticuloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codArticl != null ? codArticl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.codArticl == null && other.codArticl != null) || (this.codArticl != null && !this.codArticl.equals(other.codArticl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.Articulo[ codArticl=" + codArticl + " ]";
    }
    
}
