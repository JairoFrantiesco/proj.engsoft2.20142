package br.com.wife.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.wife.controller.RastreamentoController;
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

}
