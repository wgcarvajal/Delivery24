/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "producto", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findAllOrderByPosicion", query = "SELECT p FROM Producto p WHERE p.activo = 1 ORDER BY p.prodposicion"),
    @NamedQuery(name = "Producto.findAllOrderBySubCategoria", query = "SELECT p FROM Producto p ORDER BY p.idsubcategoria.id"),
    @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id"),
    @NamedQuery(name = "Producto.findByProdnombre", query = "SELECT p FROM Producto p WHERE p.prodnombre = :prodnombre"),
    @NamedQuery(name = "Producto.findByProddescripcion", query = "SELECT p FROM Producto p WHERE p.proddescripcion = :proddescripcion"),
    @NamedQuery(name = "Producto.findByNameProducto", query = "SELECT p FROM Producto p WHERE LOWER(CONCAT(CONCAT(p.prodnombre,' '),p.proddescripcion)) LIKE :nombre"),
    @NamedQuery(name = "Producto.findByProdnombrefactura", query = "SELECT p FROM Producto p WHERE p.prodnombrefactura = :prodnombrefactura"),
    @NamedQuery(name = "Producto.findByProdprecio", query = "SELECT p FROM Producto p WHERE p.prodprecio = :prodprecio"),
    @NamedQuery(name = "Producto.findByProdposicion", query = "SELECT p FROM Producto p WHERE p.prodposicion = :prodposicion"),
    @NamedQuery(name = "Producto.findByProdimagen", query = "SELECT p FROM Producto p WHERE p.prodimagen = :prodimagen"),
    @NamedQuery(name = "Producto.findBySubCatId", query = "SELECT p FROM Producto p WHERE p.idsubcategoria.id = :idsubcategoria"),
    @NamedQuery(name = "Producto.findByActivo", query = "SELECT p FROM Producto p WHERE p.activo = :activo")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "prodnombre")
    private String prodnombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "proddescripcion")
    private String proddescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "prodnombrefactura")
    private String prodnombrefactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prodprecio")
    private int prodprecio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prodposicion")
    private int prodposicion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "prodimagen")
    private String prodimagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idproducto")
    private List<Itempedido> itempedidoList;
    @JoinColumn(name = "idsubcategoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subcategoria idsubcategoria;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String prodnombre, String proddescripcion, String prodnombrefactura, int prodprecio, int prodposicion, String prodimagen, int activo) {
        this.id = id;
        this.prodnombre = prodnombre;
        this.proddescripcion = proddescripcion;
        this.prodnombrefactura = prodnombrefactura;
        this.prodprecio = prodprecio;
        this.prodposicion = prodposicion;
        this.prodimagen = prodimagen;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProdnombre() {
        return prodnombre;
    }

    public void setProdnombre(String prodnombre) {
        this.prodnombre = prodnombre;
    }

    public String getProddescripcion() {
        return proddescripcion;
    }

    public void setProddescripcion(String proddescripcion) {
        this.proddescripcion = proddescripcion;
    }

    public String getProdnombrefactura() {
        return prodnombrefactura;
    }

    public void setProdnombrefactura(String prodnombrefactura) {
        this.prodnombrefactura = prodnombrefactura;
    }

    public int getProdprecio() {
        return prodprecio;
    }

    public void setProdprecio(int prodprecio) {
        this.prodprecio = prodprecio;
    }

    public int getProdposicion() {
        return prodposicion;
    }

    public void setProdposicion(int prodposicion) {
        this.prodposicion = prodposicion;
    }

    public String getProdimagen() {
        return prodimagen;
    }

    public void setProdimagen(String prodimagen) {
        this.prodimagen = prodimagen;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Itempedido> getItempedidoList() {
        return itempedidoList;
    }

    public void setItempedidoList(List<Itempedido> itempedidoList) {
        this.itempedidoList = itempedidoList;
    }

    public Subcategoria getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(Subcategoria idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Producto[ id=" + id + " ]";
    }
    
}
