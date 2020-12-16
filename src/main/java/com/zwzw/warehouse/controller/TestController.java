package com.zwzw.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.zwzw.warehouse.common.WareResponse;
import com.zwzw.warehouse.model.TestModel;
import com.zwzw.warehouse.param.Pagination;
import com.zwzw.warehouse.param.TestParam;
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
	
	@RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index1(ModelAndView mav) {
		mav.setViewName("test/show");
		TestParam param = new TestParam();
		Pagination p = new Pagination();
		p.setPage(2);
		param.setPagination(p);
		Page<TestModel> testList = ts.getTestList(param);
		mav.addObject("rets",testList.getResult());
		return mav;
	}
}
