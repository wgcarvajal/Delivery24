/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.webservices;

import com.delivery24.entities.Anuncio;
import com.delivery24.entities.Configuracionpedido;
import com.delivery24.entities.Contacto;
import com.delivery24.entities.Formapago;
import com.delivery24.entities.Itempedido;
import com.delivery24.entities.Notificacion;
import com.delivery24.entities.Pedido;
import com.delivery24.entities.Producto;
import com.delivery24.entities.Subcategoria;
import com.delivery24.entities.Usuario;
import com.delivery24.facade.AnuncioFacade;
import com.delivery24.facade.ConfiguracionpedidoFacade;
import com.delivery24.facade.ContactoFacade;
import com.delivery24.facade.FormapagoFacade;
import com.delivery24.facade.ItempedidoFacade;
import com.delivery24.facade.NotificacionFacade;
import com.delivery24.facade.PedidoFacade;
import com.delivery24.facade.ProductoFacade;
import com.delivery24.facade.SubcategoriaFacade;
import com.delivery24.facade.UsuarioFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 * REST Web Service
 *
 * @author geovanny
 */
@Path("service")
public class WebServices {

    private List<Subcategoria> itemsSubcategoria = null;
    private List<Producto> itemsProducto = null;
    private List<Configuracionpedido> itemsConfiguracion = null;
    private List<Anuncio> itemsAnuncio = null;
    private List<Formapago> itemsFormapago = null;
    @EJB
    private SubcategoriaFacade ejbSubcategoriaFacade;
    @EJB
    private ProductoFacade ejbProductoFacade;
    @EJB
    private ConfiguracionpedidoFacade ejbConfiguracionFacade;
    @EJB
    private AnuncioFacade ejbAnuncioFacade;
    @EJB
    private UsuarioFacade ejbUsuarioFacade;
    @EJB
    private PedidoFacade ejbPedidoFacade;
    @EJB
    private ItempedidoFacade ejbItemPedidoFacade;
     @EJB
    private FormapagoFacade ejbFormaPago;
      @EJB
    private NotificacionFacade ejbNotificacionFacade;
       @EJB
    private ContactoFacade ejbContactoFacade;
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of WebServices
     */
    public WebServices() {
    }
    /**
     * Retrieves representation of an instance of com.delivery24.webservices.WebServices
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        JSONArray arraySubcategoria = new JSONArray();
        JSONArray arrayProducto = new JSONArray();
        JSONArray arrayConfiguracion = new JSONArray();
        JSONArray arrayAnuncio = new JSONArray();
        JSONArray arrayFormapago = new JSONArray();
        
        itemsFormapago = ejbFormaPago.findAllActive();
        for(Formapago form: itemsFormapago)
        {
            JSONObject obj = new JSONObject();
            try
            {                
                obj.put("id", form.getId()+"");
                obj.put("nombre", form.getNombre());
                obj.put("abreviatura", form.getAbreviatura());
                arrayFormapago.put(obj);
            }
            catch(Exception e)
            {

            } 
        }
        
        itemsSubcategoria = ejbSubcategoriaFacade.findAllSubcategoriaOrderByPosicion();
        for (Subcategoria sub : itemsSubcategoria)
        {
            JSONObject obj = new JSONObject();
            try
            {                
                obj.put("id", sub.getId()+"");
                obj.put("subcatnombre", sub.getSubcatnombre());
                obj.put("subcatipo", sub.getSubcatipo()+"");
                obj.put("subcatposicion", sub.getSubcatposicion()+"");
                arraySubcategoria.put(obj);
            }
            catch(Exception e)
            {

            } 
        }         
        itemsProducto = ejbProductoFacade.findAllProductoOrderByPosicion();
        for (Producto prod : itemsProducto)
        {
            JSONObject obj = new JSONObject();
            try
            {                
                obj.put("id", prod.getId()+"");
                obj.put("idsubcategoria", prod.getIdsubcategoria().getId()+"");
                obj.put("prodnombre", prod.getProdnombre());
                obj.put("proddescripcion", prod.getProddescripcion());                
                obj.put("prodnombrefactura", prod.getProdnombrefactura());
                obj.put("prodprecio", prod.getProdprecio()+"");
                obj.put("prodposicion", prod.getProdposicion()+"");
                obj.put("prodimagen", prod.getProdimagen());                
                
                arrayProducto.put(obj);
            }
            catch(Exception e)
            {

            }               

        }     
         itemsConfiguracion = ejbConfiguracionFacade.findAll();
         Configuracionpedido con = itemsConfiguracion.get(0);
         JSONObject obj = new JSONObject();
            try
            {                
                obj.put("id", con.getId()+"");
                obj.put("domicilio", con.getDomicilio()+"");
                obj.put("minimopedido", con.getMinimopedido()+"");
                obj.put("correo", con.getCorreo());
                
                arrayConfiguracion.put(obj);
            }
            catch(Exception e)
            {

            } 
            
        itemsAnuncio = ejbAnuncioFacade.findAll();
        for (Anuncio anun : itemsAnuncio)
        {
            JSONObject objA = new JSONObject();
            try
            {                
                objA.put("id", anun.getId()+"");
                objA.put("idsubcategoria", anun.getIdsubcategoria().getId()+"");
                objA.put("imagen", anun.getImagen());                
                
                arrayAnuncio.put(objA);
            }
            catch(Exception e)
            {

            }               

        } 
         return "{\"status\": 1, \"subcategorias\":"+arraySubcategoria.toString()+
                 ",\"productos\":"+arrayProducto.toString()+
                 ",\"anuncios\":"+arrayAnuncio.toString()+
                 ",\"configuracion\":"+arrayConfiguracion.toString()+
                 ",\"formapago\":"+arrayFormapago.toString()
                 +",\"totalSubcategorias\":"+itemsSubcategoria.size()
                 +",\"totalProductos\":"+itemsProducto.size()
                 +",\"totalAnuncios\":"+itemsAnuncio.size()+"}";
    }

    /**
     * PUT method for updating or creating an instance of WebServices
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        
    }   
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
     @Produces(MediaType.APPLICATION_JSON)
    public String postJson(String content) 
    {  
        try
        {
           JSONObject jsonConsulta = new JSONObject(content); 
           int tipo = jsonConsulta.getInt("tipo");
           switch(tipo)
           {
               case 1:
                  JSONArray datos = jsonConsulta.getJSONArray("datos");
                  JSONObject usuario = datos.getJSONObject(0);
                  Usuario user = new Usuario();
                  user.setUsunombre(usuario.getString("nombre"));
                  user.setUsupassword(usuario.getString("password"));
                  user.setUsuemail(usuario.getString("email"));
                  user.setUsufacebook(0);
                  if(!ejbUsuarioFacade.findUsuEmai(user.getUsuemail()))
                  {
                      ejbUsuarioFacade.create(user);
                      return "{\"id\": \""+user.getId()+"\"}";
                  }                  
               break;
               case 2:
                  JSONArray datosFacebook = jsonConsulta.getJSONArray("datos");
                  JSONObject usuarioFacebook = datosFacebook.getJSONObject(0);
                  Usuario userFacebook = new Usuario();
                  userFacebook.setUsunombre(usuarioFacebook.getString("nombre"));
                  userFacebook.setUsuidfacebook(usuarioFacebook.getString("id"));
                  if(!usuarioFacebook.getString("email").equals(""))
                  {
                      userFacebook.setUsuemailfacebook(usuarioFacebook.getString("email"));
                  }
                  List<Usuario> listus=ejbUsuarioFacade.findUsuIdFacebook(userFacebook.getUsuidfacebook());
                  if(listus.size()>0)
                  {
                      Usuario usufb = listus.get(0);
                      return "{\"id\": \""+usufb.getId()+"\",\"nombre\":\""+usufb.getUsunombre()+"\",\"tipo\": \"2\"}";
                  }
                  else
                  {
                      userFacebook.setUsufacebook(1);
                      ejbUsuarioFacade.create(userFacebook);
                      return "{\"id\": \""+userFacebook.getId()+"\",\"nombre\":\""+userFacebook.getUsunombre()+"\",\"tipo\": \"2\"}";
                  }
               case 3:
                  JSONArray datosLogin = jsonConsulta.getJSONArray("datos");
                  JSONObject usuarioLogin = datosLogin.getJSONObject(0);
                  Usuario userLogin = new Usuario();
                  userLogin.setUsupassword(usuarioLogin.getString("password"));
                  userLogin.setUsuemail(usuarioLogin.getString("email"));
                  List<Usuario> listusulogin= ejbUsuarioFacade.findEmailPassword(userLogin.getUsuemail(), userLogin.getUsupassword());
                  if(listusulogin.size()>0)
                  {
                      Usuario u = listusulogin.get(0);
                      return "{\"id\": \""+u.getId()+"\",\"nombre\":\""+u.getUsunombre()+"\",\"tipo\": \"3\"}";
                  }
                  
               break;
               
               case 4:
                   Pedido pedido = new Pedido();
                   pedido.setUsuarionombre(jsonConsulta.getString("nombre"));
                   pedido.setDireccion(jsonConsulta.getString("direccion"));
                   pedido.setTelefono(jsonConsulta.getString("telefono"));
                   pedido.setFormapago(jsonConsulta.getString("formapago"));
                   pedido.setObservaciones(jsonConsulta.getString("observaciones"));
                   pedido.setDomicilio(jsonConsulta.getInt("domicilio"));
                   pedido.setToken(jsonConsulta.getString("token"));
                   pedido.setEstado(0);
                   pedido.setFecha(new Date());
                   int registrado= jsonConsulta.getInt("registrado");
                   if(registrado == 1)
                   {
                       Usuario us = new Usuario();
                       us.setId(jsonConsulta.getInt("idusuario"));
                       pedido.setIdusuario(us);
                   }
                   ejbPedidoFacade.create(pedido);
                   
                   JSONArray items = jsonConsulta.getJSONArray("items");
                   for(int i = 0; i< items.length(); i++)
                   {
                       JSONObject item = items.getJSONObject(i);
                       Itempedido itempedido = new Itempedido();
                       itempedido.setIdpedido(pedido);
                       Producto p = new Producto();
                       p.setId(Integer.parseInt(item.getString("idproducto")));
                       itempedido.setIdproducto(p);
                       itempedido.setPrecio(Integer.parseInt(item.getString("precio")));
                       itempedido.setCantidad(Integer.parseInt(item.getString("cantidad")));
                       ejbItemPedidoFacade.create(itempedido);
                   }
                   
                   return "{\"id\": \""+pedido.getId()+"\"}";
                case 5:
                   Contacto contacto = new Contacto();
                   contacto.setEmail(jsonConsulta.getString("email"));
                   contacto.setNombre(jsonConsulta.getString("nombre"));
                   contacto.setMensaje(jsonConsulta.getString("mensaje"));
                   ejbContactoFacade.create(contacto);
                   return "{\"id\": \""+contacto.getId()+"\"}";
                
                case 6:
                    String token  = jsonConsulta.getString("token");
                    String dispositivo  = jsonConsulta.getString("device");
                    Notificacion notificacion = new Notificacion();
                    notificacion.setToken(token);
                    notificacion.setDispositivo(dispositivo);
                    ejbNotificacionFacade.create(notificacion);
                    return "{\"id\": \""+notificacion.getId()+"\"}";
                   
               
           }
        }
        catch(Exception e)
        {
            
        }
        
        return "{\"id\": \"-1\"}";
    }
    
}
