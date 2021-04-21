package com.carecorner.handler.contact;

import com.carecorner.gateway.ApiGatewayResponse;
import com.carecorner.gateway.Response;

import com.carecorner.dao.ContactDao;
import com.carecorner.pojo.Contact;;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.type.TypeReference;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ContactHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final ContactDao contactDao = ContactDao.INSTANCE;
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		logger.debug("Contact Handler received: {}", input);

		Map<String, Object> json = null;
		List<JsonNode> jsonNodes = new ArrayList<>();
		try {
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
			logger.debug("Params: {}", body);
			
			String userId = body.get("user-id").asText();
			logger.debug("User ID: {}", userId);

			List<Contact> contacts = contactDao.findByUser(userId);
			logger.debug("Contacts: {}", contacts.toString());
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode contactNode = mapper.createObjectNode();
			for (int i = 0; i < contacts.size(); i = i + 1) {
				Contact contact = contacts.get(i);	
				contactNode.put("contact-id", contact.getContactId());
				contactNode.put("name", contact.getName());
				contactNode.put("phone", contact.getPhone());
				jsonNodes.add(contactNode);
			}
			
		} catch (Exception exception) {
			logger.error("Error in contacts: " + exception);
			exception.printStackTrace();

			// send the error response back
			Response responseBody = new Response("Error in contacts: ", json);
			return ApiGatewayResponse.builder()
					.setStatusCode(500)
					.setObjectBody(jsonNodes)
					.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
					.build();

		}

		Response responseBody = new Response("Contacts", json);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "Care Corner"))
				.build();
	}
}

