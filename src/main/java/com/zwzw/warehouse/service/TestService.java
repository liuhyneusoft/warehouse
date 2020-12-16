package com.zwzw.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zwzw.warehouse.dao.TestDao;
import com.zwzw.warehouse.mapper.TestMapper;
import com.zwzw.warehouse.model.TestModel;
import com.zwzw.warehouse.param.TestParam;
import com.zwzw.warehouse.po.TestPO;
import com.zwzw.warehouse.util.PaginationUtil;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;
	
	
	public List<TestPO> ts() {
		List<TestPO> pos = testDao.findAll();
		return pos;
	}
	
	
	@Autowired
	private TestMapper testMapper;

	public Page<TestModel> getTestList(TestParam param) {
		Page<TestModel> page = PaginationUtil.getPage(param.getPagination());
		testMapper.test(param);
		return page;
	}
}
