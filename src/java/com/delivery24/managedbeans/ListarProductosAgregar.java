/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Producto;
import com.delivery24.facade.ProductoFacade;
import com.delivery24.managedbeans.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
/**
 *
 * @author geovanny
 */
@Named(value = "listarProductosAgregar")
@ViewScoped
public class ListarProductosAgregar  implements Serializable
{   
    @EJB
    private ProductoFacade ejbProductoFacade;
    private List<Producto>productos;
    private String nombreProducto;   

    

    public ListarProductosAgregar() 
    {
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) 
    {
        this.productos = productos;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public void listarProductos()
    {
        productos = ejbProductoFacade.findAllProductoOrderBySubCategoria();
        nombreProducto = "";
        Util.update("ventanaListaProductos");
        Util.openDialog("listaProductos"); 
    }
    
    public void buscarPorNombreProducto()
    {
        this.productos = ejbProductoFacade.busacarPorNombreProducto(nombreProducto);
    }  
    
}
