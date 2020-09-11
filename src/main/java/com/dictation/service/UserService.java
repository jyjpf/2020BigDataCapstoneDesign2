package com.dictation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.dictation.Common.PositionCode;
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
    if("선생님".equals(user.getPosition_cd())) {
			user.setPosition_cd(PositionCode.ROME_TEACHER);
		} else if("학생".equals(user.getPosition_cd())) {
			user.setPosition_cd(PositionCode.ROLE_STUDENT);
		} else {
      throw new Exception("Undefined Position Code");
		}

		if("남자".equals(user.getGender_cd())) {
		  user.setGender_cd("002001");
    } else if("여자".equals(user.getGender_cd())) {
      user.setGender_cd("002002"); 
    } else {
      throw new Exception("Undefined Gender Code");
    }
		userMapper.insert(user);
	}

	  /**
   * 내 정보 변경
   * @param UserVO
   * @return void
   * @throws Exception
   */
	public void update(UserVO user) throws Exception {
    if("선생님".equals(user.getPosition_cd())) {
			user.setPosition_cd(PositionCode.ROME_TEACHER);
		} else if("학생".equals(user.getPosition_cd())) {
			user.setPosition_cd(PositionCode.ROLE_STUDENT);
		} else {
      throw new Exception("Undefined Position Code");
		}

		if("남자".equals(user.getGender_cd())) {
		  user.setGender_cd("002001");
    } else if("여자".equals(user.getGender_cd())) {
      user.setGender_cd("002002"); 
    } else {
      throw new Exception("Undefined Gender Code");
    }
		userMapper.update(user);
	}

}
