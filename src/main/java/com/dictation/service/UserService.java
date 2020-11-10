package com.dictation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.dictation.Common.Code;
import com.dictation.mapper.UserMapper;
import com.dictation.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 내 정보 조회
	 * @param id
	 * @return UserVO
	 */
	public UserVO get(String id) {
		return userMapper.get(id);
	}

	/**
	 * 유저 조회
	 * @return UserVO
	 */
	public List<UserVO> getList() {
		return userMapper.getList();
	}
	
  /**
   * 회원가입
   * @param UserVO
   * @return void
   * @throws Exception
   */
  public void insert(UserVO user) throws Exception {
		userMapper.insert(user);
	}

	  /**
   * 내 정보 변경
   * @param UserVO
   * @return void
   * @throws Exception
   */
	public void update(UserVO user) throws Exception {
		userMapper.update(user);
	}

}
