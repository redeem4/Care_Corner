package com.carecorner.handler;

import com.carecorner.gateway.ApiGatewayResponse;
import com.carecorner.gateway.Response;

import com.carecorner.dao.ContactDao;
import com.carecorner.pojo.Contact;
import com.carecorner.dao.JourneyDao;
import com.carecorner.pojo.Journey;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.type.TypeReference;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class JourneyHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final JourneyDao journeryDao = JourneyDao.INSTANCE;
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Journey Handler received: {}", input);

		int statusCode = 401;
		try {
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
			logger.debug("Params: {}", body);

			String location = body.get("location").asText();
			String eta = body.get("eta").asText();

			logger.debug("Location: {}", location);
			logger.debug("ETA: {}", eta);
//			List<User> users = userDao.findByUsername(username);
	//		logger.debug("Userss: {}", users.toString());
/*			if (users.size() > 0) {
				User user = users.get(0);
				logger.debug("User: {}", user);
				logger.debug("Comp: {}", user.getPassword());
				if (user.getPassword().equals(password)) {
					// authenticated
					statusCode = 200;
				}
			}*/
		} catch (Exception exception) {
			exception.printStackTrace();

			// send the error response back
			Response responseBody = new Response("Error in resource: ", input);
			return ApiGatewayResponse.builder()
					.setStatusCode(statusCode)
					.setObjectBody(responseBody)
					.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
					.build();
		}

		Response responseBody = new Response("data:", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(statusCode)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}
}

