package com.zwzw.warehouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zwzw.warehouse.model.TestModel;
import com.zwzw.warehouse.param.TestParam;
@Repository
@Mapper
public interface TestMapper {
	 
	List<TestModel> test(@Param("params") TestParam param);
}
