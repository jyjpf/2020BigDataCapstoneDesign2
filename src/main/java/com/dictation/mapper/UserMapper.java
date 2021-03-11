package com.dictation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dictation.vo.UserVO;

@Repository
@Mapper
public interface UserMapper {	
	
	/**
	 * 내 정보 조회
	 * @param id
	 * @return UserVO
	 */
	public UserVO get(String id);

	/**
	 * 유저 정보 조회
	 * @return UserVO
	 */
	public List<UserVO> getList();

	/**
	 * 회원가입
	 * @param user
	 * @return int
	 * @throws Exception
	 */
	public int insert(UserVO user) throws Exception;

	
	/**
	 * 내 정보 변경
	 * @param UserVO
	 * @return int
	 * @throws Exception
	 */	
	public int update(UserVO user) throws Exception;

	
}
