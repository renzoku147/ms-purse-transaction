package com.spring.mspursetransaction.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.mspursetransaction.entity.PurseTransaction;
import com.spring.mspursetransaction.kafka.ProductorKafka;
import com.spring.mspursetransaction.service.PurseTransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PurseTransactionHandler {
	@Autowired
	PurseTransactionService purseTransactionService;
	
	@Autowired
	ProductorKafka productorKafka;
	
	@Autowired
	private KafkaTemplate<String, Object> template;
	 
	public Mono<ServerResponse> enviarBalance(ServerRequest request) {
		PurseTransaction pt = PurseTransaction.builder()
								.id("fef464")
								.numberOrigin(65312324)
								.numberDetiny(965245887)
								.balance(25.0)
								.build();
		String respuesta = "Proceso exitoso";
		System.out.println("Ejecutandose enviar!");
	    try {
	    	template.send("topico-everis", pt);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        respuesta = "Error desconocido";
	    }
	    return ServerResponse.status(HttpStatus.OK)
				.bodyValue(respuesta);
	}
	
	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");
		
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(purseTransactionService.findById(id), PurseTransaction.class);
    }
	
	
	public Mono<ServerResponse> list(ServerRequest request) {
		
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(purseTransactionService.findAll(), PurseTransaction.class);
//                .bodyValue (purseTransactionService.findAll());
    }
	
	
	public Mono<ServerResponse> create(ServerRequest request) {
		Mono<PurseTransaction> monoTransaction = request.bodyToMono(PurseTransaction.class);

		return monoTransaction.flatMap(monoTrns -> {
													try {
														template.send("topico-everis", monoTrns);
														System.out.println("Envio topico-everis");
														return ServerResponse
																.status(HttpStatus.ACCEPTED)
																.contentType(MediaType.APPLICATION_JSON)
																.body(purseTransactionService.create(monoTrns), PurseTransaction.class);
													}catch(Exception e) {
														e.printStackTrace();
														return ServerResponse.status(HttpStatus.BAD_GATEWAY)
																.bodyValue("Error al crear");
													}
											});
    }
	
	public Mono<ServerResponse> delete(ServerRequest request) {
		String id = request.pathVariable("id");
		System.out.println("Esta llegando al DELETE Transaccion monedero");
        return purseTransactionService.delete(id)
        		.flatMap(purse -> ServerResponse.status(HttpStatus.OK)
								.bodyValue("Transaccion monedero Eliminado"));
    }
	
}
