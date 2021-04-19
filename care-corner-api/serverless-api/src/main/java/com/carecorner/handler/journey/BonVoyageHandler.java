package com.carecorner.handler.journey;

import com.carecorner.gateway.ApiGatewayResponse;
import com.carecorner.gateway.Response;

import com.carecorner.dao.ContactDao;
import com.carecorner.pojo.Contact;
import com.carecorner.pojo.Journey;
import com.carecorner.pojo.User;
import com.carecorner.dao.JourneyDao;
import com.carecorner.dao.UserDao;
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

public class BonVoyageHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final JourneyDao journeryDao = JourneyDao.INSTANCE;
	private final ContactDao contactDao = ContactDao.INSTANCE;
	private final UserDao userDao = UserDao.INSTANCE;
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Journey BonVoyage Handler received: {}", input);

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

			List<User> users = userDao.findByUserID(userId);
			User user = users.get(0);

			List<Contact> contacts = contactDao.findByUser(userId);
			logger.debug("Contacts: {}", contacts.toString());
			for (int i = 0; i < contacts.size(); i = i + 1) {
				Contact contact = contacts.get(i);	
				logger.debug("Contact: {}", contact);
				Messenger.sendSMS(
					contact.getPhone(), 
					buildBeginMessage(user, contact, destination, eta)
				);
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

		Response responseBody = new Response("Bon Voyage", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(statusCode)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}

	private String buildBeginMessage(User user, Contact contact, String destination, String ETA) {
		String msg = "Hi %s, we wanted to let you know that %s has begun a walk and wanted to keep you informed. " +
			"We'll update you as they progress towards %s, with a planned arrival of %s. Best, Care Corner <3";

		return String.format(msg, contact.getName(), user.getFname(), destination, ETA);
	}
}

