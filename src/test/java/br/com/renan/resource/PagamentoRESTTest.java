package br.com.renan.resource;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentoRESTTest {

	@Test
	@Order(4)
	public void salvarPagamentoSucesso() {
		String jsonPayload = "{ \"nomeCliente\": \"Teste\", \"valor\": 100.0, \"dataPagamento\": \"2023-11-15T16:39:10\" }";
		given().contentType(ContentType.JSON).body(jsonPayload).when().post("/pagamento").then().statusCode(201)
				.extract().response();
	}

	@Test
	@Order(1)
	public void salvarPagamentoCampoVazio() {
		String jsonPayload = "{ \"nomeCliente\": \"\", \"valor\": 100.0, \"dataPagamento\": \"2023-11-15T16:39:10\" }";
		given().contentType(ContentType.JSON).body(jsonPayload).when().post("/pagamento").then().statusCode(400)
				.extract().response();
	}

	@Test
	@Order(2)
	public void buscarTodosPagamentosListaVazia() {
		given().contentType(ContentType.JSON).when().get("/pagamento").then().statusCode(204).extract().response();
	}
	
	@Test
	@Order(5)
	public void buscarTodosPagamentosListaPreenchida() {
		given().contentType(ContentType.JSON).when().get("/pagamento").then().statusCode(200).extract().response();
	}
	
	@Test
	@Order(3)
	public void buscarPorIdNaoLocalizando() {
		given().contentType(ContentType.JSON).when().get("/pagamento/1").then().statusCode(404).extract().response();
	}
	
	@Test
	@Order(6)
	public void buscarPorIdSucesso() {
		given().contentType(ContentType.JSON).when().get("/pagamento/1").then().statusCode(200).extract().response();
	}

}
