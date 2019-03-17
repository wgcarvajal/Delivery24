/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Itempedido;
import com.delivery24.entities.Pedido;
import com.delivery24.facade.FormapagoFacade;
import com.delivery24.facade.ItempedidoFacade;
import com.delivery24.facade.PedidoFacade;
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
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author geovanny
 */
@Named(value = "pedidoCanceladoDetallesController")
@SessionScoped
public class PedidoCanceladoDetallesController implements Serializable {

    private Pedido pedidoSelected;
    private List<Itempedido> itemsPedido;
    @EJB
    private ItempedidoFacade ejbItempedidoFacade;
    @EJB
    private FormapagoFacade ejbFormapagoFacade;
    @EJB
    private PedidoFacade ejbPedidoFacade;   
    
    
   
    public PedidoCanceladoDetallesController() 
    {
        
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
    
    public void seleccionarPedido(Pedido p) throws IOException
    {
        try
        {
            this.pedidoSelected = p;
            itemsPedido = ejbItempedidoFacade.findByPedidoId(p.getId());            
            FacesContext.getCurrentInstance().getExternalContext().redirect("/delivery24/faces/pedidoscancelados/pedidocancelado.xhtml");
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
    
}
