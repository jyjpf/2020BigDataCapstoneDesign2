package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Repository
@Mapper
public interface EnrollDAO {

	public List<Map<String, Object>> getStudentList(Map<String, Object> params);

	public List<EnrollVO> getEnrollList(UserVO user);

	public void insert(EnrollVO enroll) throws Exception;

	public void update(EnrollVO enroll) throws Exception;

	public void delete(Map<String, Object> params) throws Exception;

}
