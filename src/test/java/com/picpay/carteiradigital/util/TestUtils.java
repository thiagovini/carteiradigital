package com.picpay.carteiradigital.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String objectToJson(Object value) throws Exception {
		return mapper.writeValueAsString(value);
	}

}
