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

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
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

public class DestinationHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final JourneyDao journeryDao = JourneyDao.INSTANCE;
	private final ContactDao contactDao = ContactDao.INSTANCE;
	private final UserDao userDao = UserDao.INSTANCE;
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Journey Waypoint Handler received: {}", input);

		int statusCode = 401;
		try {
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
			logger.debug("Params: {}", body);

			String userId = body.get("user-id").asText();
			BigDecimal latitude = new BigDecimal(body.get("latitude").asText());
			BigDecimal longitude = new BigDecimal(body.get("longitude").asText());

			logger.debug("User ID: {}", userId);
			logger.debug("Longitude: {}", longitude);
			logger.debug("Latitude: {}", latitude);

			List<User> users = userDao.findByUserID(userId);
			User user = users.get(0);

			Journey journey = Journey.of(Integer.parseInt(userId), "Maiden Voyage", "",
				new BigDecimal("0.0"), new BigDecimal("0.0"), latitude, longitude);
			journeryDao.updateEndByUser(Integer.parseInt(userId), journey);

			List<Contact> contacts = contactDao.findByUser(userId);
			logger.debug("Contacts: {}", contacts.toString());
			for (int i = 0; i < contacts.size(); i = i + 1) {
				Contact contact = contacts.get(i);
				logger.debug("Contact: {}", contact);
				Messenger.sendSMS(contact.getPhone(), 
					buildDestinationMessage(user, contact)
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

		Response responseBody = new Response("Waypoint", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(statusCode)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}

	private String buildDestinationMessage(User user, Contact contact) {
		String msg = "Good news %s! At %s %s arrived at their intended destination. Care Corner <3";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); 
		LocalDateTime now = LocalDateTime.now();  

		return String.format(msg, contact.getName(), formatter.format(now), user.getFname());
	}
}

