package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Repository
@Mapper
public interface EnrollMapper {

	public List<Map<String, Object>> getList(Map<String, Object> params);

	public List<EnrollVO> getMyList(UserVO user);

	public void insert(EnrollVO enroll);

	public void update(EnrollVO enroll);

}
