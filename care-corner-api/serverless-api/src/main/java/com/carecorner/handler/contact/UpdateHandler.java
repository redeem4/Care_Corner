package com.carecorner.handler.contact;

import com.carecorner.gateway.ApiGatewayResponse;
import com.carecorner.gateway.Response;

import com.carecorner.dao.ContactDao;
import com.carecorner.pojo.Contact;
import com.carecorner.dao.UserDao;

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

public class UpdateHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final ContactDao contactDao = ContactDao.INSTANCE;
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Journey BonVoyage Handler received: {}", input);

		int statusCode = 401;
		try {
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
			logger.debug("Params: {}", body);

			String userId = body.get("user-id").asText();
			String contactId = body.get("contact-id").asText();
			String phone = body.get("phone").asText();
			String name = body.get("phone").asText();

			logger.debug("User ID: {}", userId);
			logger.debug("Contact ID: {}", contactId);
			logger.debug("Phone: {}", phone);
			logger.debug("Name: {}", name);
			/*

			List<Contact> contacts = contactDao.findByUser(userId);
			logger.debug("Contacts: {}", contacts.toString());
			for (int i = 0; i < contacts.size(); i = i + 1) {
				Contact contact = contacts.get(i);	
				logger.debug("Contact: {}", contact);
				Messenger.sendSMS(
					contact.getPhone(), 
					buildBeginMessage(user, contact, destination, eta)
				);
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

		Response responseBody = new Response("Bon Voyage", input);
		return ApiGatewayResponse.builder()
				.setStatusCode(statusCode)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}
}

