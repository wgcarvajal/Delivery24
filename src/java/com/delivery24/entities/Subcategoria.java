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
@Table(name = "subcategoria", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcategoria.findAll", query = "SELECT s FROM Subcategoria s"),
    @NamedQuery(name = "Subcategoria.findAllOderByNombre", query = "SELECT s FROM Subcategoria s ORDER BY s.subcatnombre"),
    @NamedQuery(name = "Subcategoria.findAllOrderByPosicion", query = "SELECT s FROM Subcategoria s  ORDER BY s.subcatposicion"),
    @NamedQuery(name = "Subcategoria.findById", query = "SELECT s FROM Subcategoria s WHERE s.id = :id"),
    @NamedQuery(name = "Subcategoria.findBySubcatnombre", query = "SELECT s FROM Subcategoria s WHERE s.subcatnombre = :subcatnombre"),
    @NamedQuery(name = "Subcategoria.findByColumn", query = "SELECT s FROM Subcategoria s WHERE LOWER(s.subcatnombre) LIKE :subcatnombre Order by s.subcatnombre asc"),
    @NamedQuery(name = "Subcategoria.findBySubcatipo", query = "SELECT s FROM Subcategoria s WHERE s.subcatipo = :subcatipo"),
    @NamedQuery(name = "Subcategoria.findBySubcatposicion", query = "SELECT s FROM Subcategoria s WHERE s.subcatposicion = :subcatposicion")})
public class Subcategoria implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubcategoria")
    private List<Anuncio> anuncioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "subcatnombre")
    private String subcatnombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subcatipo")
    private int subcatipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "subcatposicion")
    private int subcatposicion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubcategoria")
    private List<Producto> productoList;

    public Subcategoria() {
    }

    public Subcategoria(Integer id) {
        this.id = id;
    }

    public Subcategoria(Integer id, String subcatnombre, int subcatipo, int subcatposicion) {
        this.id = id;
        this.subcatnombre = subcatnombre;
        this.subcatipo = subcatipo;
        this.subcatposicion = subcatposicion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcatnombre() {
        return subcatnombre;
    }

    public void setSubcatnombre(String subcatnombre) {
        this.subcatnombre = subcatnombre;
    }

    public int getSubcatipo() {
        return subcatipo;
    }

    public void setSubcatipo(int subcatipo) {
        this.subcatipo = subcatipo;
    }

    public int getSubcatposicion() {
        return subcatposicion;
    }

    public void setSubcatposicion(int subcatposicion) {
        this.subcatposicion = subcatposicion;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
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
        if (!(object instanceof Subcategoria)) {
            return false;
        }
        Subcategoria other = (Subcategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Subcategoria[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Anuncio> getAnuncioList() {
        return anuncioList;
    }

    public void setAnuncioList(List<Anuncio> anuncioList) {
        this.anuncioList = anuncioList;
    }
    
}
