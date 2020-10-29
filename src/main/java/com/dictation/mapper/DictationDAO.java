package com.dictation.mapper;

import com.dictation.vo.BoardVO;
import com.dictation.vo.CourseVO;
import com.dictation.vo.StudyVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DictationDAO {

	public List<CourseVO> getStudentList(Map<String, Object> params);

	public List<CourseVO> getTeacherList(Map<String, Object> params);

	public void insert(CourseVO course) throws Exception;

	public void update(CourseVO course) throws Exception;

}
