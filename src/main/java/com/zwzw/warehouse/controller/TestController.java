package com.zwzw.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zwzw.warehouse.common.WareResponse;
import com.zwzw.warehouse.po.TestPO;
import com.zwzw.warehouse.service.TestService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestService ts;

	@RequestMapping(value = "/init", method = { RequestMethod.GET })
	public WareResponse init() {
		WareResponse res = new WareResponse();
		List<TestPO> pos = ts.ts();
		pos.stream().forEach(v -> System.out.println(v.getName()));
		res.setValue("result", pos);
		return res;
	}
}
