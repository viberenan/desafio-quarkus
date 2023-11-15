package br.com.renan.resource;

import java.util.List;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.renan.dto.PagamentoDTO;
import br.com.renan.exception.PagamentoException;
import br.com.renan.service.PagamentoService;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoREST {

	@Inject
	PagamentoService service;

	private static final Logger log = LoggerFactory.getLogger(PagamentoREST.class);

	@Inject
	RoutingContext rout;

	@POST
	@Transactional
	@Counted(name = "contador salvar pagamentos")
	@Operation(summary = "Salva o Pagamento")
	public Response salvarPagamento(@Valid PagamentoDTO dto) {
		try {
			dto = service.salvar(dto);
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 201");
			return Response.status(Response.Status.CREATED).entity(dto).build();
		} catch (ConstraintViolationException ex) {
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 400");
			throw ex;
		} catch (Exception e) {
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 500");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	@Counted(name = "contador buscar por id")
	@Operation(summary = "Busca o pagamento por id")
	public Response buscarPorId(@PathParam("id") Long id) {
		try {
			PagamentoDTO dto = service.buscarPorId(id);
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 200");
			return Response.status(Response.Status.OK).entity(dto).build();
		} catch (PagamentoException e) {
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 404");
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		} catch (Exception e) {
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 500");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Counted(name = "contador buscar todos pagamentos")
	@Operation(summary = "Busca todos os pagamentos")
	public Response buscarTodosPagamentos() {
		try {
			List<PagamentoDTO> dtos = service.buscarTodosPagamentos();
			if (dtos.isEmpty()) {
				log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 204");
				return Response.status(Response.Status.NO_CONTENT).entity(dtos).build();
			}
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 200");
			return Response.status(Response.Status.OK).entity(dtos).build();
		} catch (Exception e) {
			log.info(rout.request().method().name() + " - " + rout.request().uri() + " - Status 500");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
