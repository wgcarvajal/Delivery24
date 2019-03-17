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
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author geovanny
 */
@Named(value = "productosController")
@ViewScoped
public class ProductosController implements Serializable 
{
    @EJB
    private ProductoFacade ejbProductoFacade;
    @EJB
    private SubcategoriaFacade ejbSubcategoriaFacade;
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
            
        }
        return subcategorias;
    }

    public void setSubcategorias(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
    
    
}
