package com.carecorner;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class PanicHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Panic Handler received: {}", input);

		try {
			// obtain the body from input
			//JsonNode body = new ObjectMapper().readTree((String)input.get("body"));
			logger.debug(input.get("name"));

		} catch (Exception exception) {
			logger.error("Error in panic: " + exception);

			// send the error response back
			Response responseBody = new Response("Error in panic: ", input);
			return ApiGatewayResponse.builder()
					.setStatusCode(500)
					.setObjectBody(responseBody)
					.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
					.build();

		}

		Response responseBody = new Response("Panic received...", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}
}

