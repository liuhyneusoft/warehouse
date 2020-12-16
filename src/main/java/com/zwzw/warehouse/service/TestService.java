package com.zwzw.warehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwzw.warehouse.dao.TestDao;
import com.zwzw.warehouse.po.TestPO;

@Service
public class TestService {

	@Autowired
	private TestDao testDao;
	
	
	public List<TestPO> ts() {
		List<TestPO> pos = testDao.findAll();
		return pos;
	}
}
