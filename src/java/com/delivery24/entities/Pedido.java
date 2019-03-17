/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geovanny
 */
@Entity
@Table(name = "pedido", catalog = "delivery24bd", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findAllorderByFecha", query = "SELECT p FROM Pedido p Order by p.fecha asc"),
    @NamedQuery(name = "Pedido.findAllNewOrderByFecha", query = "SELECT p FROM Pedido p WHERE p.estado = 0 Order by p.fecha asc"),
    @NamedQuery(name = "Pedido.findAllConfirmadosOrderByFecha", query = "SELECT p FROM Pedido p WHERE p.estado = 1 Order by p.fecha asc"),
    @NamedQuery(name = "Pedido.findAllDespachadosOrderByFecha", query = "SELECT p FROM Pedido p WHERE p.estado = 2 Order by p.fecha asc"),
    @NamedQuery(name = "Pedido.findAllCanceladosOrderByFecha", query = "SELECT p FROM Pedido p WHERE p.estado = 3 Order by p.fecha asc"),
    @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM Pedido p WHERE p.id = :id"),
    @NamedQuery(name = "Pedido.findByUsuarionombre", query = "SELECT p FROM Pedido p WHERE p.usuarionombre = :usuarionombre"),
    @NamedQuery(name = "Pedido.findByDireccion", query = "SELECT p FROM Pedido p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Pedido.findByTelefono", query = "SELECT p FROM Pedido p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Pedido.findByEstado", query = "SELECT p FROM Pedido p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pedido.findByFormapago", query = "SELECT p FROM Pedido p WHERE p.formapago = :formapago"),
    @NamedQuery(name = "Pedido.findByObservaciones", query = "SELECT p FROM Pedido p WHERE p.observaciones = :observaciones")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "usuarionombre")
    private String usuarionombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "domicilio")
    private int domicilio;    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "formapago")
    private String formapago;
    @Basic(optional = true)
    @Size(min = 0, max = 500)
    @Column(name = "observaciones")
    private String observaciones;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpedido")
    private List<Itempedido> itempedidoList;
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idusuario;
    @JoinColumn(name = "idciudad", referencedColumnName = "id")
    @ManyToOne
    private Ciudad idciudad;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "token")
    private String token;

    

    public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Pedido(Integer id, String direccion, String telefono, int estado, String formapago, String observaciones) {
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
        this.formapago = formapago;
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuarionombre() {
        return usuarionombre;
    }

    public void setUsuarionombre(String usuarionombre) {
        this.usuarionombre = usuarionombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Itempedido> getItempedidoList() {
        return itempedidoList;
    }

    public void setItempedidoList(List<Itempedido> itempedidoList) {
        this.itempedidoList = itempedidoList;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    public Ciudad getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Ciudad idciudad) {
        this.idciudad = idciudad;
    }
    
    public int getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(int domicilio) {
        this.domicilio = domicilio;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.delivery24.entities.Pedido[ id=" + id + " ]";
    }
    
}
