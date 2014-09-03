package br.com.wife.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

import br.com.wife.controller.DispositivoController;
import br.com.wife.controller.RastreamentoController;
import br.com.wife.model.Dispositivo;
import br.com.wife.model.Rastreamento;


//Classe RestFull. Recebe os dados do android via webservice. Padrão Json
@Path("/rastreamento")
public class WifeResource {
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public ArrayList<Rastreamento> listarTodos(){
		return new RastreamentoController().getAll();
	}
	
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON(Rastreamento rastreamento) {

		RastreamentoController control = new RastreamentoController();
		
		String output = rastreamento.toString();
		
		System.out.println("RECEBIDO VIA JSON: " + rastreamento.getGpsLong());

		control.saveOrUpdate(rastreamento);
		
		return Response.status(200).entity(output).build();
	}
	
	@GET
    @Path("/maps")
    public Viewable index(@Context HttpServletRequest request) {
      
		
		RastreamentoController rastControl = new RastreamentoController();
		DispositivoController dispControl = new DispositivoController();
    	
    	List<Rastreamento> lista = rastControl.getAll();
    	
    	List<Dispositivo> lstDisp = dispControl.getAll();
    	
    	System.out.println("QUANT RASTREAMENTO: " + lista.size());
    	System.out.println("QUANT DISPOSITIVO: " + lstDisp.size());
    	
    	for(int i=0; i < lstDisp.size(); i++){
    		System.out.println("Dispositivo: " + lstDisp.get(i).getNmDispositivo() + " - Imei: " + lstDisp.get(i).getImei());
    	}
    	
    	for(int i=0; i < lista.size(); i++){
    		System.out.println("Lat: " + lista.get(i).getGpsLat() + "Long: " + lista.get(i).getGpsLong());
    		System.out.println("Dispositivo: " + lista.get(i).getDispositivo().getNmDispositivo());
    	}
    	
        request.setAttribute("objLista", lista);
        request.setAttribute("lstDisp", lstDisp);
        
        return new Viewable("/rastreamentoMap.jsp", lista);
    }

}
