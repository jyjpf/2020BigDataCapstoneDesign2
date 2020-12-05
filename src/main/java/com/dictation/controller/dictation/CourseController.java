package com.dictation.controller.dictation;

import java.io.*;
import java.util.*;

import com.dictation.Common.Code;
import com.dictation.Common.DictationUtils;
import com.dictation.service.*;
import com.dictation.vo.CourseVO;

import com.dictation.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/course")
public class CourseController {

	private static final Logger logger = LogManager.getLogger(CourseController.class);
	private static final String FILEPATH = "/home/dictation/audio/";

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudyService studyService;


	@GetMapping(value = "/{course_no}")
	public List<CourseVO> getCourse(
			@PathVariable("lecture_no") long lecture_no,
			@PathVariable("course_no") int course_no,
			@RequestParam(value = "result", required = false, defaultValue = "false") String result,
			@AuthenticationPrincipal UserVO activeUser) {

		System.out.println();
		System.out.println("[TEST]");
		System.out.println();
		Map<String, Object> params = new HashMap<>();

		params.put("lecture_no", lecture_no);
		params.put("course_no", course_no);

		if (activeUser.getPosition_cd().equals(Code.ROLE_TEACHER)) {
			return courseService.getTeacherList(params);
		} else {
			if (result.equals("true")) {
				params.put("student_id", activeUser.getUser_id());
				return studyService.getNextSequence(params) > 0 ? courseService.getTeacherList(params) : null;
			} else {
				params.put("user_id", activeUser.getUser_id());
				return courseService.getStudentList(params);
			}
		}
	}
	
	@GetMapping(value = "/random/{grade}/{count}")
	public List<CourseVO> getRandomCourse(
			@PathVariable("lecture_no") long lecture_no,
			@PathVariable("grade") int grade,
			@PathVariable("count") int count,
			@RequestParam(value = "result", required = false, defaultValue = "false") String result,
			@AuthenticationPrincipal UserVO activeUser) {

		System.out.println();
		System.out.println("[TEST2] grade=" + grade + " count=" + count);
		System.out.println();
		Map<String, Object> params = new HashMap<>();

		params.put("course_no", grade);

		if (activeUser.getPosition_cd().equals(Code.ROLE_TEACHER)) {
			List<CourseVO> dicList = courseService.getDicList(params);
		    List<CourseVO> newList = new ArrayList<CourseVO>(); 
			if(dicList != null && dicList.size() > 0) {
				Random rand = new Random();
				int k = (count > dicList.size()) ? dicList.size() : count;
			    for (int i = 0; i < k; i++) {
			        int randomIndex = rand.nextInt(dicList.size());
			        newList.add(dicList.get(randomIndex));
			        dicList.remove(randomIndex);
			    }
			}
			return newList;
		} else {
			return null;
		}
	}

	@PostMapping
	@Secured("ROLE_TEACHER")
	public void insert(
			@ModelAttribute CourseVO course,
			@PathVariable("lecture_no") long lecture_no,
			@Param("file") MultipartFile file,
			@Param(value = "type") int type,
			@Param(value = "file_nm") String file_nm,
			@Param(value = "save_file_nm") String save_file_nm,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		course.setLecture_no(lecture_no);
		course.setInput_id(activeUser.getUser_id());
		course.setUpdate_id(activeUser.getUser_id());

		if (file != null) {
			String saveFilename = DictationUtils.toMD5(file.getOriginalFilename());
			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);
			FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
			fos.write(file.getBytes());
			fos.close();
		}
		if (type == 1) {
			course.setFile_nm(file_nm);
			course.setSave_file_nm(save_file_nm);
		}

		courseService.insert(course);
	}

	// Update시 삭제 구현?
	@PutMapping
	@Secured("ROLE_TEACHER")
	public void update(
			@ModelAttribute CourseVO course,
			@PathVariable(value="lecture_no") long lecture_no,
			@Param(value = "file") MultipartFile file,
			@Param(value = "type") int type,
			@Param(value = "file_nm") String file_nm,
			@Param(value = "save_file_nm") String save_file_nm,
			@AuthenticationPrincipal UserVO activeUser) throws Exception {

		course.setLecture_no(lecture_no);
		course.setInput_id(activeUser.getUser_id());
		course.setUpdate_id(activeUser.getUser_id());
		
		if (file != null) {
			String saveFilename = DictationUtils.toMD5(file.getOriginalFilename());
			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);
			FileOutputStream fos = new FileOutputStream(FILEPATH + saveFilename);
			fos.write(file.getBytes());
			fos.close();
		}
		if (type == 1) {
			course.setFile_nm(file_nm);
			course.setSave_file_nm(save_file_nm);
		}

		courseService.update(course);
	}

	@DeleteMapping
	@Secured("ROLE_TEACHER")
	public void delete() throws Exception {

	}

}
