package com.gateway.api.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
	
	// this is for Booking Microservice
		@Bean
		public RouteLocator routeLocatorBooking(RouteLocatorBuilder routeLocatorBuilder) {
			return routeLocatorBuilder.routes()
				.route(p->p
						.path("/api/bookings")
						.uri("http://localhost:8082")
						)
				.route(p->p
						.path("/api/bookings/{id}")
						.uri("http://localhost:8082")
						)
				.route(p->p
						.path("/api/bookings/")
						.uri("http://localhost:8082")
						)
				.route(p->p
						.path("/api/bookings/{id}")
						.uri("http://localhost:8082")
						)
				.route(p->p
						.path("/api/bookings/{id}")
						.uri("http://localhost:8082")
						)
				.build();
	}
	
		// this is for Inventory Microservice
		@Bean
		public RouteLocator routeLocatorInventory(RouteLocatorBuilder routeLocatorBuilder) {
			return routeLocatorBuilder.routes()
				.route(p->p
						.path("/api/inventory")
						.uri("http://localhost:8083")
						)
				.route(p->p
						.path("/api/inventory/")
						.uri("http://localhost:8083")
						)
				.route(p->p
						.path("/api/inventory/{id}")
						.uri("http://localhost:8083")
						)
				.route(p->p
						.path("/api/inventory/")
						.uri("http://localhost:8083")
						)
				.route(p->p
						.path("/api/inventory/{id}")
						.uri("http://localhost:8083")
						)
				.route(p->p
						.path("/api/inventory/{id}")
						.uri("http://localhost:8083")
						)
				.build();
	}
	
		// this is for Room Microservice
		@Bean
		public RouteLocator routeLocatorRoom(RouteLocatorBuilder routeLocatorBuilder) {
			return routeLocatorBuilder.routes()
				.route(p->p
						.path("/api/rooms")
						.uri("http://localhost:8081")
						)
				.route(p->p
						.path("/api/rooms/{id}")
						.uri("http://localhost:8081")
						)
				.route(p->p
						.path("/api/rooms/{roomType}/{roomService}")
						.uri("http://localhost:8081")
						)
				.route(p->p
						.path("/api/rooms/")
						.uri("http://localhost:8081")
						)
				.route(p->p
						.path("/api/rooms/{id}")
						.uri("http://localhost:8081")
						)
				.build();
	}
	
	// this is for Staff Microservice
		@Bean
		public RouteLocator routeLocatorStaff(RouteLocatorBuilder routeLocatorBuilder) {
			return routeLocatorBuilder.routes()
				.route(p->p
						.path("/api/staff")
						.uri("http://localhost:8084")
						)
				.route(p->p
						.path("/api/staff/{id}")
						.uri("http://localhost:8084")
						)
				.route(p->p
						.path("/api/staff/")
						.uri("http://localhost:8084")
						)
				.route(p->p
						.path("/api/staff/{id}")
						.uri("http://localhost:8084")
						)
				.route(p->p
						.path("/api/staff/{id}")
						.uri("http://localhost:8084")
						)
				.build();
	}
}
