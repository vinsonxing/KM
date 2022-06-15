package com.css.interview.util;

import java.io.IOException;
import java.io.Reader;

import com.css.interview.exception.CSSRuntimeException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	}

	
	public static <T> T parse(Reader in, TypeReference<T> ref) throws RuntimeException {
		T obj = null;
		try {
			obj = mapper.readValue(in, ref);
		} catch (IOException e) {
			log.error("Error while unmarshalling", e);
			throw new CSSRuntimeException("Error while unmarshalling");
		}
		return obj;
	}
	
	public static String convertToJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("error while marshalling object to json string", obj);
			throw new CSSRuntimeException("error in converting to Json String");
		}
	}
}
