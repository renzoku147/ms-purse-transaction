package com.spring.mspursetransaction.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.mspursetransaction.entity.PurseTransaction;
import com.spring.mspursetransaction.handler.PurseTransactionHandler;

@Configuration
public class PurseTransactionRouter {

	@Bean
    public RouterFunction<ServerResponse> routes (PurseTransactionHandler handler) {
        return route(GET("/findById"), handler::findById)
        		.andRoute(GET("/list"), handler::list)
        		.andRoute(POST("/create"), handler::create)
        		.andRoute(GET("/enviarBalance"), handler::enviarBalance)
        		.andRoute(DELETE("/delete/{id}"), handler::delete);
    }
	
}
