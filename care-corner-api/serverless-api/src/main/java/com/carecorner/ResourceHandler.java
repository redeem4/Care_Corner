package com.carecorner;

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

public class ResourceHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Resources Handler received: {}", input);

    Map<String, Object> json;

    try {
      ObjectMapper mapper = new ObjectMapper();
      ObjectNode resource = mapper.createObjectNode();
      resource.put("id", 1);
      resource.put("name", "National Sexual Assault Hotline");
      resource.put("phone", "1-800-656-4673");
      resource.put("category", "hotline");

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

		Response responseBody = new Response("data:", json);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}
}

