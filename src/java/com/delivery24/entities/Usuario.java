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
@Table(name = "usuario", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByUsunombre", query = "SELECT u FROM Usuario u WHERE u.usunombre = :usunombre"),
    @NamedQuery(name = "Usuario.findByUsuemail", query = "SELECT u FROM Usuario u WHERE u.usuemail = :usuemail"),
    @NamedQuery(name = "Usuario.findByUsuemailPassword", query = "SELECT u FROM Usuario u WHERE u.usuemail = :usuemail AND u.usupassword = :usupassword"),
    @NamedQuery(name = "Usuario.findByUsufacebook", query = "SELECT u FROM Usuario u WHERE u.usufacebook = :usufacebook"),
    @NamedQuery(name = "Usuario.findByUsuidfacebook", query = "SELECT u FROM Usuario u WHERE u.usuidfacebook = :usuidfacebook")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "usunombre")
    private String usunombre;
    @Basic(optional = true)    
    @Size(min = 1, max = 200)
    @Column(name = "usuemail")
    private String usuemail;
    @Basic(optional = true)    
    @Size(min = 1, max = 200)
    @Column(name = "usuemailfacebook")
    private String usuemailfacebook;
    
    @Basic(optional = true)    
    @Size(min = 1, max = 200)
    @Column(name = "usupassword")
    private String usupassword;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "usufacebook")
    private int usufacebook;
    @Basic(optional = true)
    @Size(min = 1, max = 200)
    @Column(name = "usuidfacebook")
    private String usuidfacebook;
    @OneToMany(mappedBy = "idusuario")
    private List<Pedido> pedidoList;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String usunombre, String usuemail, int usufacebook, String usuidfacebook,String usupassword) {
        this.id = id;
        this.usunombre = usunombre;
        this.usuemail = usuemail;
        this.usufacebook = usufacebook;
        this.usuidfacebook = usuidfacebook;
        this.usupassword = usupassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsunombre() {
        return usunombre;
    }

    public void setUsunombre(String usunombre) {
        this.usunombre = usunombre;
    }
    
    public String getUsupassword() {
        return usupassword;
    }

    public void setUsupassword(String usupassword) {
        this.usupassword = usupassword;
    }

    public String getUsuemail() {
        return usuemail;
    }

    public void setUsuemail(String usuemail) {
        this.usuemail = usuemail;
    }
    
    public String getUsuemailfacebook() {
        return usuemailfacebook;
    }

    public void setUsuemailfacebook(String usuemail) {
        this.usuemailfacebook = usuemail;
    }

    public int getUsufacebook() {
        return usufacebook;
    }

    public void setUsufacebook(int usufacebook) {
        this.usufacebook = usufacebook;
    }

    public String getUsuidfacebook() {
        return usuidfacebook;
    }

    public void setUsuidfacebook(String usuidfacebook) {
        this.usuidfacebook = usuidfacebook;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Usuario[ id=" + id + " ]";
    }
    
}
