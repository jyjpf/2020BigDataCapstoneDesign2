package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

@Repository
@Mapper
public interface EnrollMapper {

	public List<EnrollVO> getMyList(UserVO user);

	public void insert(EnrollVO enroll);

	public void update(EnrollVO enroll);

}
