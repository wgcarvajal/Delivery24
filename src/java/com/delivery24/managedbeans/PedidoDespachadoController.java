/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Pedido;
import com.delivery24.managedbeans.util.PaginationHelper;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author geovanny
 */
@Named(value = "pedidoDespachadoController")
@RequestScoped
public class PedidoDespachadoController implements Serializable {

    private Pedido current;
    private List<Pedido> items = null;
    @EJB
    private com.delivery24.facade.PedidoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PedidoDespachadoController() 
    {
    }

    public List<Pedido> getItems()
    {
        if(items == null)
        {
           items = ejbFacade.findAllDespachadosOrderByFecha(); 
        }
        
        return items;
    }
}
