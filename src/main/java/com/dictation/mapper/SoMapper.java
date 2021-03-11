package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.SoVO;

@Repository
@Mapper
public interface SoMapper {	
	
	public SoVO get(Map<String, Object> params);

	public List<SoVO> list(Map<String, Object> params);

	public void insert(SoVO so);

	public void update(SoVO so);

	public void delete(String code);

}
