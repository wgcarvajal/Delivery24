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
@Table(name = "administradorgrupo", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administradorgrupo.findAll", query = "SELECT a FROM Administradorgrupo a"),
    @NamedQuery(name = "Administradorgrupo.findById", query = "SELECT a FROM Administradorgrupo a WHERE a.id = :id"),
    @NamedQuery(name = "Administradorgrupo.findByGruid", query = "SELECT a FROM Administradorgrupo a WHERE a.gruid = :gruid"),
    @NamedQuery(name = "Administradorgrupo.findByAdnombreusuario", query = "SELECT a FROM Administradorgrupo a WHERE a.adnombreusuario = :adnombreusuario"),
    @NamedQuery(name = "Administradorgrupo.findByAdid", query = "SELECT a FROM Administradorgrupo a WHERE a.adid = :adid")})
public class Administradorgrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "gruid")
    private String gruid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "adnombreusuario")
    private String adnombreusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adid")
    private int adid;

    public Administradorgrupo() {
    }

    public Administradorgrupo(Integer id) {
        this.id = id;
    }

    public Administradorgrupo(Integer id, String gruid, String adnombreusuario, int adid) {
        this.id = id;
        this.gruid = gruid;
        this.adnombreusuario = adnombreusuario;
        this.adid = adid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGruid() {
        return gruid;
    }

    public void setGruid(String gruid) {
        this.gruid = gruid;
    }

    public String getAdnombreusuario() {
        return adnombreusuario;
    }

    public void setAdnombreusuario(String adnombreusuario) {
        this.adnombreusuario = adnombreusuario;
    }

    public int getAdid() {
        return adid;
    }

    public void setAdid(int adid) {
        this.adid = adid;
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
        if (!(object instanceof Administradorgrupo)) {
            return false;
        }
        Administradorgrupo other = (Administradorgrupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Administradorgrupo[ id=" + id + " ]";
    }
    
}
