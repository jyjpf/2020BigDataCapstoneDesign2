package com.dictation.mapper;

import com.dictation.vo.BoardVO;
import com.dictation.vo.CourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DictationDAO {

	List<CourseVO> get(Map<String, Object> params);

	void insert(CourseVO course) throws Exception;

	void update(CourseVO course) throws Exception;
}
