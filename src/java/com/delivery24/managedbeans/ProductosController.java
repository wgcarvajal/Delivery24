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
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Wilson Carvajal
 */
@Named(value = "productosController")
@ViewScoped
public class ProductosController implements Serializable 
{
    @EJB
    private ProductoFacade ejbProductoFacade;
    @EJB
    private SubcategoriaFacade subcategoriaEJB;
    private List<Producto> productos;
    private List<Subcategoria> subcategorias;
    

    public ProductosController() 
    {
        inicializar();
    }
    
    private void inicializar()
    {
        
    }
    public List<Producto> getProductos() 
    {
        if(productos == null)
        {
            productos = ejbProductoFacade.findAllProductoOrderBySubCategoria();
        }
        return productos;
    }

    public void setProductos(List<Producto> productos) 
    {
        this.productos = productos;
    }
    
    public List<Subcategoria> getSubcategorias() 
    {
        if(subcategorias == null)
        {
            subcategorias = subcategoriaEJB.findAllOderByNombre();
        }
        return subcategorias;
    }

    public void setSubcategorias(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
    
    
    
    public void irRegistrarProducto()
    {
        try {
            String uri = Util.projectPath+"/productos/registarProducto.xhtml?i=1";
            FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
        } catch (IOException ex) {
            Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String estado(int activo)
    {
        if(activo == 0)
        {
            return "Inactivo";
        }
        return "Activo";
    }
    
    public void goProducto(int prodId) {
        try {
            String uri = Util.projectPath+"/productos/producto.xhtml?i=1&p="+prodId;
            FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
        } catch (IOException ex) {
            Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
