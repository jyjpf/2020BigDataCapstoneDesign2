package com.dictation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dictation.vo.StudyVO;

@Repository
@Mapper
public interface StudyDAO {

    public int getNextSequence(Map<String, Object> params);

    public Map<String, Object> getAnswer(StudyVO study);

    public List<Map<String, Object>> getStatsList(Map<String, Object> params);

    public List<Map<String, Object>> getStatsDetailList(Map<String, Object> params);

    public void insert(StudyVO study) throws Exception;
}
