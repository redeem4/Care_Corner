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

public class WaypointHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
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
			String latitude = body.get("latitude").asText();
			String longitude = body.get("longitude").asText();

			logger.debug("User ID: {}", userId);
			logger.debug("Longitude: {}", longitude);
			logger.debug("Latitude: {}", latitude);

			List<User> users = userDao.findByUserID(userId);
			User user = users.get(0);

			List<Contact> contacts = contactDao.findByUser(userId);
			logger.debug("Contacts: {}", contacts.toString());
			for (int i = 0; i < contacts.size(); i = i + 1) {
				Contact contact = contacts.get(i);
				logger.debug("Contact: {}", contact);
				Messenger.sendSMS(contact.getPhone(), 
					buildWaypointMessage(user, contact, latitude, longitude));
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

	private String buildWaypointMessage(User user, Contact contact, String latitude, String longitude) {
		String msg = "Location update for %s. At %s they were at " +
			"https://maps.google.com/?q=%s,%s";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); 
		LocalDateTime now = LocalDateTime.now();  

		return String.format(msg, user.getFname(), formatter.format(now), latitude, longitude);
	}
}

