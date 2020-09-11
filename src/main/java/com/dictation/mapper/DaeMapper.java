package com.dictation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.DaeVO;

@Repository
@Mapper
public interface DaeMapper {	
	
	public DaeVO get(String dae_cd);

	public List<DaeVO> list();

	public void insert(DaeVO dae_cd);

	public void update(DaeVO dae_cd);

	public void delete(String dae_cd);

}
