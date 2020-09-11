package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.Term_cdVO;


@Repository
@Mapper
public interface Term_cdMapper {

	/**
	 * 년도/분기 정보 조회
	 * @param Map
	 * @return Term_cdVO
	 */
	public Term_cdVO get(Map<String, Object> params);

	/**
	 * 년도/분기 정보 리스트
	 * @return List<Term_cdVO>
	 */
	public List<Term_cdVO> getList();

	/**
	 * 년도/분기 정보 삽입
	 * @param Term_cdVO
	 * @throws Exception
	 * @return void
	*/
	public void insert(Term_cdVO term_cd) throws Exception;

	/**
	 * 년도/분기 정보 업데이트
	 * @param Term_cdVO
	 * @throws Exception
	 * @return void
	*/
	public void update(Term_cdVO term_cd) throws Exception;
	
	/**
	 * 년도/분기 정보 삭제
	 * @param Map
	 * @throws Exception
	 * @return void
	 */
	public void delete(Map<String, Object> params);	

}
