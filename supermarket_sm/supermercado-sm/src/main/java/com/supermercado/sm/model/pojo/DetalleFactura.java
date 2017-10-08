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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leonardoaranibar
 */
@Entity
@Table(name = "detalle_factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d")
    , @NamedQuery(name = "DetalleFactura.findByNoDetallFact", query = "SELECT d FROM DetalleFactura d WHERE d.noDetallFact = :noDetallFact")
    , @NamedQuery(name = "DetalleFactura.findByCantTotDeArtic", query = "SELECT d FROM DetalleFactura d WHERE d.cantTotDeArtic = :cantTotDeArtic")})
public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "no_detall_fact")
    private Integer noDetallFact;
    @Basic(optional = false)
    @NotNull
    private int cantTotDeArtic;
    @JoinColumn(name = "codArticulo", referencedColumnName = "cod_articl")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo codArticulo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noDetallFactr", fetch = FetchType.LAZY)
    private List<EncabezadoFactura> encabezadoFacturaList;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer noDetallFact) {
        this.noDetallFact = noDetallFact;
    }

    public DetalleFactura(Integer noDetallFact, int cantTotDeArtic) {
        this.noDetallFact = noDetallFact;
        this.cantTotDeArtic = cantTotDeArtic;
    }

    public Integer getNoDetallFact() {
        return noDetallFact;
    }

    public void setNoDetallFact(Integer noDetallFact) {
        this.noDetallFact = noDetallFact;
    }

    public int getCantTotDeArtic() {
        return cantTotDeArtic;
    }

    public void setCantTotDeArtic(int cantTotDeArtic) {
        this.cantTotDeArtic = cantTotDeArtic;
    }

    public Articulo getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(Articulo codArticulo) {
        this.codArticulo = codArticulo;
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
        hash += (noDetallFact != null ? noDetallFact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.noDetallFact == null && other.noDetallFact != null) || (this.noDetallFact != null && !this.noDetallFact.equals(other.noDetallFact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.supermercado.sm.pojo.DetalleFactura[ noDetallFact=" + noDetallFact + " ]";
    }
    
}
