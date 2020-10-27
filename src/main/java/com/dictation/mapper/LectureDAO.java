package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import com.dictation.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.LectureVO;

@Repository
@Mapper
public interface LectureDAO {
	
	/**
	 * 강의 정보 조회
	 * @param String
	 * @return LectureVO
	 */
	public LectureVO get(Map<String, Object> params);

	/**
	 * 강의 리스트 조회
	 * @param String
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getStudentMenuList(Map<String, Object> params);

	/**
	 * 강의 리스트 조회
	 * @param String
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getTeacherMenuList(Map<String, Object> params);

	/**
	 * 강의 리스트 조회
	 * @param String
	 * @return List<LectureVO>
	 */
	public List<Map<String, Object>> getEnrollList(UserVO user);

	/**
	 * 강의 리스트 조회
	 * @param String
	 * @return List<LectureVO>
	 */
	public List<LectureVO> getList(Map<String, Object> params);

	/**
	 * 강의 생성
	 * @param LectureVO
	 * @return void
	 */
	// TODO: 년도/분기 Funciton 생성
	public void insert(LectureVO lecture) throws Exception;

	/**
	 * 강의 업데이트
	 * @param LectureVO
	 * @return void
	 */
	public void update(LectureVO lecture) throws Exception;

	/**
	 * 강의 삭제
	 * @param String
	 * @return void
	 */
	public void delete(String lecture_no) throws Exception;


	
	
}