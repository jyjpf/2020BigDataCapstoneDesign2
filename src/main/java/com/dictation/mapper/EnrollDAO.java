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

	public EnrollVO getEnroll(EnrollVO enroll);

	public List<Map<String, Object>> getStudentList(long lecture_no);

	public List<EnrollVO> getEnrollList(UserVO user);

	public void insert(EnrollVO enroll) throws Exception;

	public void updateStudent(EnrollVO enroll) throws Exception;

	public void updateTeacher(EnrollVO enroll) throws Exception;

	public void delete(Map<String, Object> params) throws Exception;

}
