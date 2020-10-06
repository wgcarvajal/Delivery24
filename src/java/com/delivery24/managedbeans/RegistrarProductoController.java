/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Producto;
import com.delivery24.entities.Subcategoria;
import com.delivery24.facade.ProductoFacade;
import com.delivery24.facade.SubcategoriaFacade;
import com.delivery24.managedbeans.util.Util;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wilson Carvajal
 */
@ManagedBean(name = "registrarProductoController")
@ViewScoped
public class RegistrarProductoController implements Serializable {
    
    @EJB
    private SubcategoriaFacade subcategoriaEJB;
    @EJB
    private ProductoFacade productoEJB;
    private Subcategoria subcategoria;
    private List<Subcategoria> subcategorias;
    private String nombre;
    private String nombreFactura;
    private String descripcion;
    private String precio;

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public List<Subcategoria> getSubcategorias() {
        if(subcategorias == null)
        {
            subcategorias = subcategoriaEJB.findAll();
        }
        return subcategorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreFactura() {
        return nombreFactura;
    }

    public void setNombreFactura(String nombreFactura) {
        this.nombreFactura = nombreFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    public void registrarProducto()
    {
        Producto p = new Producto();
        p.setProdnombre(Util.formatText(nombre));
        p.setIdsubcategoria(subcategoria);
        p.setProddescripcion(descripcion);
        p.setProdimagen("default.png");
        p.setProdnombrefactura(nombreFactura);
        p.setProdposicion(0);
        p.setProdprecio(Integer.parseInt(precio));
        p.setActivo(0);
        productoEJB.create(p);
        cleanFields();
        showMessageSuccessfull();
    }
    
    private void cleanFields()
    {
        nombre = null;
        descripcion = null;
        subcategoria = null;
        nombreFactura = null;
        precio = null;
    }
    
    private void showMessageSuccessfull()
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                ResourceBundle.getBundle("/Bundle").getString("Info")+":", 
                ResourceBundle.getBundle("/Bundle").getString("ProductAddSuccessfull")));
        Util.update("formRegister"); 
    }
    
}
