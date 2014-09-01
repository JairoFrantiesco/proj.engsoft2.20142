package br.com.wife.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.wife.controller.DispositivoController;
import br.com.wife.model.Dispositivo;

import com.sun.jersey.api.view.Viewable;

@Path("/dispositivo")
@Produces(MediaType.TEXT_HTML)
public class DispositivoModel {

    @GET
    @Path("new")
    public Viewable index(@Context HttpServletRequest request) {
      //  request.setAttribute("obj", new String("IT Works"));
        System.out.println("/INDEX called");
        return new Viewable("/dispositivo.jsp", null);
    }
    
    @POST
    @Path("update")
    public Viewable update(@FormParam("imei") String imei, @FormParam("dispositivo") String dispositivo,
    		@Context HttpServletResponse servletResponse, @Context HttpServletRequest request) {
    	
    	DispositivoController dispControl = new DispositivoController();
    	Dispositivo disp = new Dispositivo();
    	
    	disp.setImei(imei);
    	disp.setNmDispositivo(dispositivo);
    	
    	dispControl.saveOrUpdate(disp);
    	
      //  request.setAttribute("obj", new String("IT Works"));
        System.out.println("inserindo novo: IMEI: " + imei + " - DISP: " + dispositivo);
       // return new Viewable("/dispositivo.jsp", null);

    	
    	List<Dispositivo> lista = dispControl.getAll();
    	
    	System.out.println("QUANT DISP: " + lista.size());
    	
        request.setAttribute("objLista", lista);
        
        return new Viewable("/listaDispositivo.jsp", lista);
    }
    
    @GET
    @Path("listar")
    public Viewable listar(@Context HttpServletRequest request) {
    	
    	DispositivoController dispControl = new DispositivoController();
    	
    	List<Dispositivo> lista = dispControl.getAll();
    	
    	System.out.println("QUANT DISP: " + lista.size());
    	
        request.setAttribute("objLista", lista);
        
        
        System.out.println("LISTA DE DISPOSITIVOS");
        
        return new Viewable("/listaDispositivo.jsp", null);
    }
    
}