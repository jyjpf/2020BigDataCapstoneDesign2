package com.dictation.mapper;

import java.util.Map;

import com.dictation.vo.UserVO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommonMapper {	
  
  /**
   * 로그인
   * @param params
   * @return UserVO
   */
  public UserVO login(Map<String, Object> params);
	
}
