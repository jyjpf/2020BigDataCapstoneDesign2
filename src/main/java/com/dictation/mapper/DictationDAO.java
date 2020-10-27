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

	List<CourseVO> getStudentList(Map<String, Object> params);

	List<CourseVO> getTeacherList(Map<String, Object> params);

	void insertQuestion(CourseVO course) throws Exception;

	void insertAnswer(StudyVO study) throws Exception;

	void update(CourseVO course) throws Exception;

}
