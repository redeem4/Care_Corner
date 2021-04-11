package com.carecorner.handler;

import com.carecorner.gateway.ApiGatewayResponse;
import com.carecorner.gateway.Response;

import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.type.TypeReference;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AuthenticationHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Authentication Handler received: {}", input);

    Map<String, Object> json;

    try {
      JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
      System.out.println(body.toString());

      ObjectMapper mapper = new ObjectMapper();
      ObjectNode resource = mapper.createObjectNode();
      String username = body.get("username").asText();
      String password = body.get("passwoord").asText();
      resource.put("name", body.get("username").asText());
      resource.put("phone", body.get("password").asText());
      System.out.println(username);
      System.out.println(password);

      json = mapper.convertValue(resource, new TypeReference<Map<String, Object>>(){});

      // print json
      System.out.println(json);
		} catch (Exception exception) {
			logger.error("Error in resource: " + exception);

			// send the error response back
			Response responseBody = new Response("Error in resource: ", input);
			return ApiGatewayResponse.builder()
					.setStatusCode(500)
					.setObjectBody(responseBody)
					.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
					.build();

		}

		Response responseBody = new Response("data:", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}
}

