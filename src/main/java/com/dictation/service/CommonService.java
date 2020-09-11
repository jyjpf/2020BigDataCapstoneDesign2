package com.dictation.service;

import java.util.Map;

import com.dictation.mapper.CommonMapper;
import com.dictation.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

  @Autowired
  private CommonMapper commonMapper;

  /**
   * 로그인
   * 
   * @param 아이디, 패스워드
   * @return UserVO
   */
  public UserVO login(Map<String, Object> params) {
    return commonMapper.login(params);
  }
 
}
