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
	
	private String positionToCd(String position) throws Exception {
		if("선생님".equals(position)) {
			return Code.ROLE_TEACHER;
		} else if("학생".equals(position)) {
			return Code.ROLE_STUDENT;
		} else {
      throw new Exception("Undefined Position Code");
		}
	}

	private String genderToCd(String position) throws Exception {
		if("남자".equals(position)) {
		  return Code.GENDER_MALE;
    } else if("여자".equals(position)) {
      return Code.GENDER_FEMALE; 
    } else {
      throw new Exception("Undefined Gender Code");
    }
	}

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
		user.setPosition_cd(positionToCd(user.getPosition_cd()));
		user.setGender_cd(genderToCd(user.getGender_cd()));
		userMapper.insert(user);
	}

	  /**
   * 내 정보 변경
   * @param UserVO
   * @return void
   * @throws Exception
   */
	public void update(UserVO user) throws Exception {
		user.setPosition_cd(positionToCd(user.getPosition_cd()));
		user.setGender_cd(genderToCd(user.getGender_cd()));
		userMapper.update(user);
	}

}
