package com.carecorner.gateway;

import java.util.Map;

public class Response {

	private final String message;
	private final Map<String, Object> data;

	public Response(String message, Map<String, Object> data) {
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return this.message;
	}

	public Map<String, Object> getData() {
		return this.data;
	}
}
