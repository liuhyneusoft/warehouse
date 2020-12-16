package com.zwzw.warehouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zwzw.warehouse.po.TestPO;

@Repository
public interface TestDao  extends JpaRepository<TestPO, Long>, JpaSpecificationExecutor<TestPO> {

}


 