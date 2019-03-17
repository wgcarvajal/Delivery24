/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "formapago", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formapago.findAll", query = "SELECT f FROM Formapago f"),
    @NamedQuery(name = "Formapago.findAllActive", query = "SELECT f FROM Formapago f WHERE f.activo = 1"),
    @NamedQuery(name = "Formapago.findById", query = "SELECT f FROM Formapago f WHERE f.id = :id"),
    @NamedQuery(name = "Formapago.findByAbreviatura", query = "SELECT f FROM Formapago f WHERE f.abreviatura = :abreviatura"),
    @NamedQuery(name = "Formapago.findByNombre", query = "SELECT f FROM Formapago f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Formapago.findByIdciudad", query = "SELECT f FROM Formapago f WHERE f.idciudad = :idciudad"),
    @NamedQuery(name = "Formapago.findByActivo", query = "SELECT f FROM Formapago f WHERE f.activo = :activo")})
public class Formapago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "abreviatura")
    private String abreviatura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "idciudad")
    private Integer idciudad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;

    public Formapago() {
    }

    public Formapago(Integer id) {
        this.id = id;
    }

    public Formapago(Integer id, String abreviatura, String nombre, int activo) {
        this.id = id;
        this.abreviatura = abreviatura;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
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
        if (!(object instanceof Formapago)) {
            return false;
        }
        Formapago other = (Formapago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Formapago[ id=" + id + " ]";
    }
    
}
