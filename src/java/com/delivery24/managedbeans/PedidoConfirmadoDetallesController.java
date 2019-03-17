/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Formapago;
import com.delivery24.entities.Itempedido;
import com.delivery24.entities.Pedido;
import com.delivery24.entities.Producto;
import com.delivery24.facade.FormapagoFacade;
import com.delivery24.facade.ItempedidoFacade;
import com.delivery24.facade.PedidoFacade;
import com.delivery24.networking.HttpConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@Named(value = "pedidoConfirmadoDetallesController")
@SessionScoped
public class PedidoConfirmadoDetallesController implements Serializable {

   private Pedido pedidoSelected;
    private List<Itempedido> itemsPedido;
    @EJB
    private ItempedidoFacade ejbItempedidoFacade;
    @EJB
    private FormapagoFacade ejbFormapagoFacade;
    @EJB
    private PedidoFacade ejbPedidoFacade;
    private boolean editar;
    private String domicilio;
    private List<Formapago> formasPago;
    private Itempedido itemEditarCantidad;
    private Itempedido itemEliminar;
    
    private String cliente;
    private String telefono;
    private String direccion;
    private String observaciones;
    private String formapago;
    private String cantidad;
    
    private String motivoCancelacionPedido;
    
   
    public PedidoConfirmadoDetallesController() 
    {
        editar = false;
        cantidad = "";
    }
    
    public Pedido getPedidoSelected() {
        return pedidoSelected;
    }

    public void setPedidoSelected(Pedido pedidoSelected) {
        this.pedidoSelected = pedidoSelected;
    }
    
     public List<Itempedido> getItemsPedido() {
        return itemsPedido;
    }

    public void setItemsPedido(List<Itempedido> itemsPedido) {
        this.itemsPedido = itemsPedido;
    } 
    
     public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
   
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    public List<Formapago> getFormasPago() {
        return formasPago;
    }

    public void setFormasPago(List<Formapago> formasPago) {
        this.formasPago = formasPago;
    }
    
    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getMotivoCancelacionPedido() {
        return motivoCancelacionPedido;
    }

    public void setMotivoCancelacionPedido(String motivoCancelacionPedido) {
        this.motivoCancelacionPedido = motivoCancelacionPedido;
    }
    
    public void inicializar()
    {
        this.editar = false;
        domicilio = "";
        cliente = "";
        direccion = "";
        telefono = "";
        observaciones = "";
        formasPago = null;
        formapago = "";
    }
    
    
    public void seleccionarPedido(Pedido p) throws IOException
    {
        try
        {
            inicializar();
            this.pedidoSelected = p;
            itemsPedido = ejbItempedidoFacade.findByPedidoId(p.getId());            
            FacesContext.getCurrentInstance().getExternalContext().redirect("/delivery24/faces/pedidosconfirmados/pedidoconfirmado.xhtml");
        }
        catch(IOException e)
        {
            
        }
        
    }
    public String getFechaFormatiada()
    {        
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        String fecha = formato.format(this.pedidoSelected.getFecha());
        return fecha;
    }
    
    public String getHoraFormatiada()
    {        
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        String hora = formato.format(this.pedidoSelected.getFecha());
        return hora;
    }
    
    public String getDomicilioFormater()
    {        
       NumberFormat format = NumberFormat.getInstance();
        Currency currency = Currency.getInstance("COP");
        format.setCurrency(currency);
        return format.format(pedidoSelected.getDomicilio());
    }
    
    public String getFormaDePagoFormater()
    {        
       if(this.pedidoSelected.getFormapago().equals("tc"))
       {
           return "Tarjeta";
       }
       return "Efectivo";
    }
    
    public String getTotalPedido()
    {
        int total = this.pedidoSelected.getDomicilio();
        for(Itempedido i:this.itemsPedido)
        {
            total=total+(i.getPrecio()*i.getCantidad());
        }
        NumberFormat format = NumberFormat.getInstance();
        Currency currency = Currency.getInstance("COP");
        format.setCurrency(currency);
        return format.format(total);
    }
    
    public String getPrecioFormatiado(int precio)
    {
        NumberFormat format = NumberFormat.getInstance();
        Currency currency = Currency.getInstance("COP");
        format.setCurrency(currency);
        return format.format(precio);
    }
    
    public void habilitarEditarDatosPedido()
    {
        this.editar = true;
        domicilio = pedidoSelected.getDomicilio() +"";
        cliente = pedidoSelected.getUsuarionombre();
        direccion = pedidoSelected.getDireccion();
        telefono = pedidoSelected.getTelefono();
        observaciones = pedidoSelected.getObservaciones();
        formasPago = ejbFormapagoFacade.findAllActive();
        formapago = pedidoSelected.getFormapago();        
    }
    public void cancelarEditarDatosPedido()
    {
        inicializar();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioDatosPedido");
    }
    
    public void aceptarEditarDatosPedido()
    {
        if(validarCampos())
        {
            pedidoSelected.setDomicilio(Integer.parseInt(this.domicilio));
            pedidoSelected.setUsuarionombre(this.cliente);
            pedidoSelected.setDireccion(this.direccion);
            pedidoSelected.setTelefono(this.telefono);
            pedidoSelected.setObservaciones(this.observaciones);
            pedidoSelected.setFormapago(this.formapago);
            ejbPedidoFacade.edit(pedidoSelected);
            inicializar();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formularioDatosPedido");  
            requestContext.update("formularioTotalPedido");
        }
        
    }
    
    private boolean validarCampos()
    {
        int bandera = 0;
        if(validarDominicilio() == false)
        {
            bandera = 1;
        }
        
        if(validarCliente() == false)
        {
            bandera = 1;
        }
        
        if(validarDireccion()== false)
        {
            bandera = 1;
        }
        
        if(validarTelefono()== false)
        {
            bandera = 1;
        }
        
        if(validarObservaciones()== false)
        {
            bandera = 1;
        }
        
        return bandera == 0;
    }
    
    private boolean validarDominicilio()
    {
        int bandera = 0;
        FacesContext fc = FacesContext.getCurrentInstance();
        
        if(this.domicilio.equals(""))
        {
            bandera = 1;
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Domicilio Obligatorio", "Domicilio Obligatorio"));
        }
        else
        {
            try
            {
                long campo= Long.parseLong(this.domicilio);

            }catch(Exception e)
            {
                bandera = 1;
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Domicilio campo númerico", "Domicilio campo númerico"));           
            } 
        }
        return bandera == 0;        
    }
    
    private boolean validarTelefono()
    {
        int bandera = 0;
        FacesContext fc = FacesContext.getCurrentInstance();
        
        if(this.telefono.equals(""))
        {
            bandera = 1;
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Teléfono Obligatorio", "Teléfono Obligatorio"));
        }
        else
        {
            if(this.telefono.length()>12)
            {
                bandera= 1;
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Teléfono excedido en capacidad", "Campo Teléfono excedido en capacidad"));
            }
            else
            {
                try
                {
                    long campo= Long.parseLong(this.telefono);

                }catch(Exception e)
                {
                    bandera = 1;
                    fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Teléfono campo númerico", "Teléfono campo númerico"));           
                } 
            }
            
        }
        return bandera == 0;        
    }
    
    public boolean validarCliente()
    {
        int bandera = 0;
        FacesContext fc = FacesContext.getCurrentInstance();        
        if(this.cliente.equals(""))
        {
            bandera = 1;
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Obligatorio", "Cliente Obligatorio"));
        }
        else
        {
            
            if(this.cliente.length()>200)
            {
                bandera = 1;
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Cliente excedido en capacidad", "Campo cliente excedido en capacidad"));
            } 
        }
        return bandera == 0;   
    }
    
    public boolean validarDireccion()
    {
        int bandera = 0;
        FacesContext fc = FacesContext.getCurrentInstance();        
        if(this.direccion.equals(""))
        {
            bandera = 1;
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dirección Obligatorio", "Dirección Obligatorio"));
        }
        else
        {
            
            if(this.direccion.length()>500)
            {
                bandera = 1;
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Dirección excedido en capacidad", "Campo Dirección excedido en capacidad"));
            } 
        }
        return bandera == 0;   
    }
    
    public boolean validarObservaciones()
    {
        int bandera = 0;
        FacesContext fc = FacesContext.getCurrentInstance();        
          
        if(this.observaciones.length()>500)
        {
            bandera = 1;
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo observaciones excedido en capacidad", "Campo observaciones excedido en capacidad"));
        } 
        
        return bandera == 0;   
    }
    
    public void editarCantidadItem(Itempedido item)
    {
        this.itemEditarCantidad = item;
        this.cantidad = item.getCantidad()+"";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("formularioCantidad");
        requestContext.execute("PF('agregarCantidad').show()");       
        
    }
    
    public void cancelarEditarCantidad()
    {
        this.itemEditarCantidad = null;
        this.cantidad = "";
        RequestContext requestContext = RequestContext.getCurrentInstance();        
        requestContext.execute("PF('agregarCantidad').hide()");
    }
    
     public void aceptarEditarCantidad()
    {
        if(validarCantidad())
        {
            this.itemEditarCantidad.setCantidad(Integer.parseInt(cantidad));
            ejbItempedidoFacade.edit(this.itemEditarCantidad);
            this.itemEditarCantidad = null;
            this.cantidad = "";
            RequestContext requestContext = RequestContext.getCurrentInstance(); 
            requestContext.update("tablaItemsPedido");
            requestContext.update("formularioTotalPedido");
            requestContext.execute("PF('agregarCantidad').hide()");
        }
        
        
    }
     
    public boolean validarCantidad()
    {
        int bandera = 0;
        FacesContext fc = FacesContext.getCurrentInstance();   
        if(cantidad.equals(""))
        {
            bandera= 1;
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cantidad Obligatoria", "Cantidad Obligatoria"));           

        }
        else
        {
            try
            {
                long campo= Long.parseLong(this.cantidad);

            }catch(Exception e)
            {
                bandera = 1;
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cantidad campo númerico", "Cantidad campo númerico"));           
            } 
        }
        
        return bandera == 0;
    }
    
    public void seleccionarItemEliminar(Itempedido item)
    {
        if(itemsPedido.size()>1)
        {
            this.itemEliminar = item;
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('eliminarItem').show()"); 
        }
        else
        {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('toasNoEliminarItem').show()");           
        }
        
    }
    
    public void aceptarMensajeToasNoEliminarProducto()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('toasNoEliminarItem').hide()"); 
    }
    
    public void aceptarEliminarItem()
    {
        ejbItempedidoFacade.remove(itemEliminar);
        this.itemsPedido.remove(this.itemEliminar);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('eliminarItem').hide()"); 
        requestContext.update("tablaItemsPedido");
        requestContext.update("formularioTotalPedido");
    }
    
    public void cancelarEliminarItem()
    {
        this.itemEliminar = null;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('eliminarItem').hide()"); 
    }
    
    public void agregarProducto(Producto producto)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        int bandera = 0;
        for(Itempedido itemP:itemsPedido)
        {
            if(itemP.getIdproducto().getId().equals(producto.getId()))
            {
                bandera = 1;
                break;
            }
        }        
        if(bandera == 0)
        {
            Itempedido i = new Itempedido();
            i.setIdpedido(pedidoSelected);
            i.setCantidad(1);
            i.setIdproducto(producto);
            i.setPrecio(producto.getProdprecio());
            ejbItempedidoFacade.create(i);
            itemsPedido.add(i);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("tablaItemsPedido");
            requestContext.update("formularioTotalPedido");
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto agregado al pedido", "Producto agregado al pedido"));
        }
        else
        {
             
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Producto ya se encuentra agregado", "Producto ya se encuentra agregado"));
        }
    }
    
    public void cancelarPedido()
    { 
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("ventanaMotivoCancelacion");
        requestContext.execute("PF('motivoCancelacion').show()");        
    }

    public void aceptarCancelarPedido()throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (!motivoCancelacionPedido.equals("s")) {
            String titulo = "Pedido Cancelado!!!";
            HttpConnection con = new HttpConnection();
            String json = "{\"registration_ids\": [\"" + pedidoSelected.getToken() + "\"]"
                    + "     ,\"data\": {\"message\": \"" + motivoCancelacionPedido + "\","
                    + "\"titulo\": \"" + titulo + "\",\"tipo\": 3}}";
            con.postJson(json);

            pedidoSelected.setEstado(3);
            ejbPedidoFacade.edit(pedidoSelected);
            itemsPedido = null;
            pedidoSelected = null;
            motivoCancelacionPedido = "";
            fc.getExternalContext().redirect("/delivery24/faces/pedidosnuevos/pedidosnuevos.xhtml");
        }
        else
        {
           fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe Seleccionar un movito por el cual se cancela.", "Debe Seleccionar un movito por el cual se cancela."));
           RequestContext requestContext = RequestContext.getCurrentInstance();
           requestContext.update("ventanaMotivoCancelacion"); 
        }
        
    }
    
    public void cancelarCancelarPedido()
    {
        motivoCancelacionPedido = "";
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('motivoCancelacion').hide()");
    }
    
    public void generarPdf()
    {
       
        Document document = new Document(new Rectangle(0,0,385,1058));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
            String html = getStringHtml();
            document.open();
            InputStream is = new ByteArrayInputStream(html.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document,is,Charset.forName("UTF-8"));
        }
        catch(Exception ex)
        {
            System.out.println("error:"+ex.getMessage());
            
        }
        
        document.close();
        FacesContext context = FacesContext.getCurrentInstance();
        Object response = context.getExternalContext().getResponse();
        if(response instanceof HttpServletResponse)
        {
            HttpServletResponse hsr = (HttpServletResponse)response;
            hsr.setContentType("application/pdf");
            hsr.setHeader("Content-disposition", "inline; filename= \"File.pdf\"");
            hsr.setContentLength(baos.size());
            try
            {
                ServletOutputStream out = hsr.getOutputStream();
                baos.writeTo(out);
                out.flush();
                
            }catch(IOException ex)
            {
                System.out.println("Error:"+ex.getMessage());
            }
            context.responseComplete();
        }
    }
    
    
    private String getStringHtml()
    {
        String html = "<html>" +
                      "<head>"                      
                    + "</head>"
                    + "<body>"
                        +"<div style='text-align:center;' >"
                           +"<img  src=\"http://wmyserver.sytes.net/delivery24/faces/resources/imagenesAplicacion/ic_delevery_24.png\" width= \"150\" />"
                
                        + "</div>"
                        +"<div style='font-size: 20pt;text-align:center;'>Nit. 7729999-8</div>"
                        +"<div style='height:20px;'></div>"
                        +"<div style='font-size: 13pt;font-weight:bold'>Número pedido: "+pedidoSelected.getId()+"</div>"
			+"<div style='font-size: 16pt;'>Fecha (aaaa-mm-dd): "+getFechaFormatiada()+"</div>"
                        +"<div style='font-size: 16pt;'>Hora: "+getHoraFormatiada()+"</div>"
                        +"<div style='text-align:center;font-size:20pt;font-weight:bold;'>Datos del Cliente</div>"
                        +"<div style='font-size: 17pt;font-weight:bold;'>Nombre:</div>"
                        +"<div style='font-size: 18pt'>"+pedidoSelected.getUsuarionombre()+"</div>"
                        +"<div style='font-size: 17pt;font-weight:bold;'>Dirección:</div>"
                        +"<div style='font-size: 18pt'>"+pedidoSelected.getDireccion()+"</div>"
                        +"<div style='font-size: 17pt;font-weight:bold;'>Teléfono:</div>"
                        +"<div style='font-size: 18pt'>"+pedidoSelected.getTelefono()+"</div>" 
                        +"<div style='text-align:center;font-size:20pt;font-weight:bold;'>Detalle de la compra</div>"
                        +"<div style='font-size: 17pt;font-weight:bold;'>Forma de pago:</div>"
                        +"<div style='font-size: 18pt'>"+getFormaDePagoFormater()+"</div>"
                        +"<div style='font-size: 17pt;font-weight:bold;'>Valor domicilio:</div>"
                        +"<div style='font-size: 18pt'>"+getPrecioFormatiado(pedidoSelected.getDomicilio())+"</div>";
                        
                        
        String segundaParte = "<table border='1'>"
                            +"<tr>"
                                +"<td style='font-size: 17pt;font-weight:bold;'>"
                                    +"cant."
                                +"</td>"
                                +"<td style='font-size: 17pt;font-weight:bold;'>"
                                    +"Producto"
                                +"</td>"
                                +"<td style='font-size: 17pt;font-weight:bold;'>"
                                    +"Subtotal"
                                +"</td>"
                            +"</tr>";
        int total = pedidoSelected.getDomicilio();
        for(Itempedido ip: itemsPedido)
        {
            total=total+(ip.getCantidad()*ip.getPrecio());
            segundaParte=segundaParte 
                         +"<tr>"
                                +"<td style='font-size: 15pt;'>"
                                    +ip.getCantidad()
                                +"</td>"
                                +"<td style='font-size: 15pt;'>"
                                    +ip.getIdproducto().getProdnombrefactura()
                                +"</td>"
                                +"<td style='font-size: 15pt;'>"
                                    +getPrecioFormatiado(ip.getCantidad() * ip.getPrecio())
                                +"</td>"
                         +"</tr>"; 
        }
        html = html 
                + "<div style='font-size: 17pt;font-weight:bold;'>Total Pedido:</div>"
                +"<div style='font-size: 22pt'>"+getPrecioFormatiado(total)+"</div>"
                +"<div style='height:20px;'></div>" 
                +segundaParte  
                   +"</table>"
                    +"<div style='height:20px;'></div>"
                    +"<div style='text-align:center;font-size:20pt;font-weight:bold;'>Observaciones</div>"
                    +"<div style='text-align:center;font-size:17pt;'>"+pedidoSelected.getObservaciones()+"</div>"
                    + "</body>"
                + "</html>";
                        
        
		return html;
    }
    
    public void despacharPedido()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('despacharPedido').show()"); 
    }
    
    public void aceptarDespacharPedido() throws IOException
    {
        FacesContext fc = FacesContext.getCurrentInstance();        
        String titulo = "Pedido Despachado!!!";
        HttpConnection con = new HttpConnection();
        String json = "{\"registration_ids\": [\"" + pedidoSelected.getToken() + "\"]"
                + "     ,\"data\": {\"message\": \"" + "su pedido Ha sido despachado.." + "\","
                + "\"titulo\": \"" + titulo + "\",\"tipo\": 2}}";
        con.postJson(json);

        pedidoSelected.setEstado(2);
        ejbPedidoFacade.edit(pedidoSelected);
        itemsPedido = null;
        pedidoSelected = null;
        fc.getExternalContext().redirect("/delivery24/faces/pedidosnuevos/pedidosnuevos.xhtml");        
       
    }
    
    public void cancelarDespacharPedido()
    {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('despacharPedido').hide()");
    }
    
}
