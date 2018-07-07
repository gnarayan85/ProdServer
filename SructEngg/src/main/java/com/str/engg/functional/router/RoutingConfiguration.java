package com.str.engg.functional.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.str.engg.functional.handler.StructEnggMainHandler;

@Configuration
public class RoutingConfiguration {
	
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(StructEnggMainHandler graphHandler) {
        return route(GET("/api/graph").and(accept(MediaType.APPLICATION_JSON)), graphHandler::getAll)
        		.andRoute(GET("/api/graph/{id}").and(accept(MediaType.APPLICATION_JSON)), graphHandler::getGraph)
        		.andRoute(POST("/api/graph/post").and(accept(MediaType.APPLICATION_JSON)), graphHandler::postGraph)
                .andRoute(PUT("/api/graph/put/{id}").and(accept(MediaType.APPLICATION_JSON)), graphHandler::putGraph)
                .andRoute(DELETE("/api/graph/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), graphHandler::deleteGraph);
    }
    
}