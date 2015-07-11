package com.sandbox.phonebook;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Маппер jQgrid JSON на экземпляр {@link JqgridFilter}
 */
public class JqGridObjectMapper {
	
	public static JqGridFilter map(String jsonString) {
		
    	if (jsonString != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	
        	try {
        		// Пробуем прочесть данные из JSON фильтра и сформировать на выходе объект
				return mapper.readValue(jsonString, JqGridFilter.class);
        	} catch (Exception e) {
				throw new RuntimeException (e);
			}
    	}
    	
		return null;
	}
}