package com.zwzw.warehouse.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class WareResponse {
	private String responseCode = "00000";
	private String responseMsg = "success";
	private Map<String, Object> responseBody = new HashMap<String, Object>();
	public void setValue(String key, Object value) {
		this.responseBody.put(key, value);
	}
}
