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
@Table(name = "configuracionpedido", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracionpedido.findAll", query = "SELECT c FROM Configuracionpedido c"),
    @NamedQuery(name = "Configuracionpedido.findById", query = "SELECT c FROM Configuracionpedido c WHERE c.id = :id"),
    @NamedQuery(name = "Configuracionpedido.findByDomicilio", query = "SELECT c FROM Configuracionpedido c WHERE c.domicilio = :domicilio"),
    @NamedQuery(name = "Configuracionpedido.findByMinimopedido", query = "SELECT c FROM Configuracionpedido c WHERE c.minimopedido = :minimopedido")})
public class Configuracionpedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "domicilio")
    private int domicilio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minimopedido")
    private int minimopedido;
    @Basic(optional = false) 
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "correo")
    private String correo;

    public Configuracionpedido() {
    }

    public Configuracionpedido(Integer id) {
        this.id = id;
    }

    public Configuracionpedido(Integer id, int domicilio, int minimopedido) {
        this.id = id;
        this.domicilio = domicilio;
        this.minimopedido = minimopedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(int domicilio) {
        this.domicilio = domicilio;
    }

    public int getMinimopedido() {
        return minimopedido;
    }

    public void setMinimopedido(int minimopedido) {
        this.minimopedido = minimopedido;
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
        if (!(object instanceof Configuracionpedido)) {
            return false;
        }
        Configuracionpedido other = (Configuracionpedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Configuracionpedido[ id=" + id + " ]";
    }
    
}
