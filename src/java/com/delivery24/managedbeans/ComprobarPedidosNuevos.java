/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Pedido;
import com.delivery24.facade.PedidoFacade;
import com.delivery24.managedbeans.util.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author geovanny
 */
@Named(value = "comprobarPedidosNuevos")
@SessionScoped
public class ComprobarPedidosNuevos implements Serializable {

    @EJB
    private PedidoFacade ejbPedidoFacade;
    private final  List<Pedido> pedidosNotificados;
    /**
     * Creates a new instance of ComprobarPedidosNuevos
     */
    public ComprobarPedidosNuevos() 
    {
        pedidosNotificados = new ArrayList();
    }
    
    public void hayNuevosPedidos()
    {
        List<Pedido>pedidosnuevos = ejbPedidoFacade.findAllNewOrderByFecha();
        int banderaNuevos=0;
        for(Pedido p: pedidosnuevos)
        {
           int bandera=0;
           for(Pedido pn : this.pedidosNotificados)
           {
               if(pn.getId().equals(p.getId()))
               {
                   bandera =1;
               }
           }           
           if(bandera == 0)
           {
               banderaNuevos = 1;
               this.pedidosNotificados.add(p);
           }
        }

        if(banderaNuevos==0)
        {
            Util.addCallbackParam("respuesta", false);
        }
        else
        { 
            Util.addCallbackParam("respuesta", true);
            Util.openDialog("nuevospedidos");
        }
    }
    
    public void aceptarNuevosPedidos()
    {
        Util.closeDialog("nuevospedidos");
        Util.addCallbackParam("respuesta", true);      
        
    }
    
}
