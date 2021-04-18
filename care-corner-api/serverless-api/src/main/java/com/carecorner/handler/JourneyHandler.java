package com.carecorner.handler;

import com.carecorner.gateway.ApiGatewayResponse;
import com.carecorner.gateway.Response;

import com.carecorner.dao.ContactDao;
import com.carecorner.pojo.Contact;
import com.carecorner.dao.JourneyDao;
import com.carecorner.pojo.Journey;
import com.carecorner.notification.Messenger;

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
	private final ContactDao contactDao = ContactDao.INSTANCE;
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Journey Handler received: {}", input);

		int statusCode = 401;
		try {
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
			logger.debug("Params: {}", body);

			String userId = body.get("user-id").asText();
			String destination = body.get("destination").asText();
			String eta = body.get("eta").asText();

			logger.debug("User ID: {}", userId);
			logger.debug("Destination: {}", destination);
			logger.debug("ETA: {}", eta);

			List<Contact> contacts = contactDao.findByUser(userId);
			logger.debug("Contacts: {}", contacts.toString());
			if (contacts.size() > 0) {
				Contact contact = contacts.get(0);
				logger.debug("Contact: {}", contact);
				Messenger.sendSMS(contact.getPhone());
			}
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

