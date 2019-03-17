/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "ciudad", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c"),
    @NamedQuery(name = "Ciudad.findById", query = "SELECT c FROM Ciudad c WHERE c.id = :id"),
    @NamedQuery(name = "Ciudad.findByCiunombre", query = "SELECT c FROM Ciudad c WHERE c.ciunombre = :ciunombre"),
    @NamedQuery(name = "Ciudad.findByCiuemail", query = "SELECT c FROM Ciudad c WHERE c.ciuemail = :ciuemail")})
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ciunombre")
    private String ciunombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ciuemail")
    private String ciuemail;
    @OneToMany(mappedBy = "idciudad")
    private List<Pedido> pedidoList;

    public Ciudad() {
    }

    public Ciudad(Integer id) {
        this.id = id;
    }

    public Ciudad(Integer id, String ciunombre, String ciuemail) {
        this.id = id;
        this.ciunombre = ciunombre;
        this.ciuemail = ciuemail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCiunombre() {
        return ciunombre;
    }

    public void setCiunombre(String ciunombre) {
        this.ciunombre = ciunombre;
    }

    public String getCiuemail() {
        return ciuemail;
    }

    public void setCiuemail(String ciuemail) {
        this.ciuemail = ciuemail;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
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
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Ciudad[ id=" + id + " ]";
    }
    
}
