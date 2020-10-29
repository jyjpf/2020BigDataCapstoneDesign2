package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.StudyVO;

@Repository
@Mapper
public interface StudyDAO {

    public int getSequence(Map<String, Object> params);

    public void insert(StudyVO study) throws Exception;
}
