//package com.spring.mspursetransaction.controller;
//
//import javax.validation.Valid;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.spring.mspursetransaction.entity.PurseTransaction;
//import com.spring.mspursetransaction.util.ProductorKafka;
//
//@RestController
//@RequestMapping("/cnt")
//public class Controller {
//	
//	@Autowired
//	ProductorKafka productorKafka;
//	
//	@Autowired
//	private KafkaTemplate<String, Object> template;
//	 
//	@GetMapping("/enviar")
//	public String enviarBalance() {
//		PurseTransaction pt = PurseTransaction.builder()
//								.id("fef464")
//								.numberOrigin(5113213)
//								.numberDetiny(965245887)
//								.balance(100.0)
//								.build();
//		String respuesta = "Proceso exitoso";
//		System.out.println("Ejecutandose enviar!");
//	    try {
//	    	
//	    	template.send("topico-everis", pt);
//	    } catch (Exception e) {
//	    	System.out.println(e.getMessage());
//	    	System.out.println(e.getMessage());
//	        respuesta = "Error desconocido";
//	    }
//	    return respuesta;
//	}
//}
