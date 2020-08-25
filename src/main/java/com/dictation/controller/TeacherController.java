package com.dictation.controller;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dictation.service.CourseService;
import com.dictation.service.EnrollService;
import com.dictation.service.LectureService;
import com.dictation.vo.CourseVO;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.LectureVO;
import com.dictation.vo.UserVO;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/teacher")
public class TeacherController {//������ ��Ʈ�ѷ�
	@Autowired
	private CourseService courseService;
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private LectureService lectureService;
	
	@PostMapping(produces = "application/json;charset=UTF-8", value="/course")
	public void insert(@RequestParam Map<String, Object> map,@Param(value = "file") MultipartFile file, HttpServletRequest request) throws Exception{

		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		
		int course_no=Integer.parseInt((String)map.get("course_no"));
		int question_no=Integer.parseInt((String)map.get("question_no"));
		String question=(String)map.get("question");
		String originalfileName = null;
		String save_file_nm=null;
		
		CourseVO course = new CourseVO();
		course.setLecture_no(lecture_session);
		course.setCourse_no(course_no);
		course.setQuestion_no(question_no);
		course.setQuestion(question);
		
		if(file.isEmpty()){

        }else {

    		
    		originalfileName = file.getOriginalFilename();
    	
    		/*
    		String fileUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(originalfileName)
                    .toUriString();
            */
    		
    			
    		//SAVE_FILE_NM
    		UUID uuid =UUID.randomUUID();
    		save_file_nm=uuid.toString() +"_" +originalfileName;
    				
    		//���� ������ ��η� ����(save_file_nm �����̸����� ����)
    		File dest = new File("C:/Temp/" + save_file_nm);
    		file.transferTo(dest);

        }
		
		course.setFile_nm(originalfileName);
		course.setSave_file_nm(save_file_nm);		
		
		courseService.insert(course);
	}
	
	//������ȭ��- �޾ƾ��� ������ư
	@PostMapping(value="/course/dic_modify")
	public void dic_modify(@RequestParam Map<String, Object> map,@Param(value = "file_nm") MultipartFile file_nm, HttpServletRequest request) throws Exception{

		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		
		System.out.println("111111");
		int course_no=Integer.parseInt((String)map.get("course_no"));
		int question_no=Integer.parseInt((String)map.get("question_no"));
		String question=(String)map.get("question");
		boolean change_file2=Boolean.parseBoolean((String)map.get("change_file"));
		String originalfileName = null;
		String save_file_nm=null;
		
		CourseVO course1 = new CourseVO();
		course1.setLecture_no(lecture_session);
		course1.setCourse_no(course_no);
		course1.setQuestion_no(question_no);
		course1.setQuestion(question);
		
		courseService.dic_modify_question(course1);

		if(change_file2==true) {
			if(file_nm.isEmpty()){

	        }else {

	    		originalfileName = file_nm.getOriginalFilename();
	    			
	    		//SAVE_FILE_NM
	    		UUID uuid =UUID.randomUUID();
	    		save_file_nm=uuid.toString() +"_" +originalfileName;
	    				
	    		File dest = new File("C:/Temp/" + save_file_nm);
	    		file_nm.transferTo(dest);
	    
	        }
			CourseVO course2 = new CourseVO();
			course2.setLecture_no(lecture_session);
			course2.setCourse_no(course_no);
			course2.setQuestion_no(question_no);
			course2.setFile_nm(originalfileName);
			course2.setSave_file_nm(save_file_nm);
			
			String delete_filenm=courseService.getById(course2).getSave_file_nm();
			File delete_file=new File("C:/Temp/"+delete_filenm);
			delete_file.delete();
			
			courseService.dic_modify_file(course2);
		}
	}
	
	@GetMapping(value="/course/max_dic_course")
	public int max_dic_course(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		return courseService.max_dic_course(lecture_session);
	}
	
	@GetMapping(value="/course/dic_answers/{course_no}")
	public List<CourseVO> dic_answers(@PathVariable("course_no") int course_no, HttpServletRequest request) {
		CourseVO course2=new CourseVO();
		
		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		course2.setLecture_no(lecture_session);
		course2.setCourse_no(course_no);
		
		System.out.println("this is dic_answer, i am finish_yn: "+ courseService.dic_answers(course2).get(1).getFinish_yn());
		
		List<CourseVO> course_sort=courseService.dic_answers(course2);
		Collections.sort(course_sort);
	
		return course_sort;
	}
	
	@PostMapping(produces = "application/json;charset=UTF-8", value="/enroll")
	public void insert(@RequestBody EnrollVO enroll, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		enroll.setLecture_no((int)session.getAttribute("lecture_no"));
				
		enrollService.insert(enroll);
	}
	
	@PostMapping(value="/enroll/delete")
	public void delete(@RequestBody EnrollVO enroll) {
		enrollService.delete(enroll);
	}
	
	@GetMapping(value="/enroll/update/{user_id}")
	public void update_request(@PathVariable("user_id") String user_id, HttpServletRequest request) { //user_id, lecture_no�� �ʼ�

		HttpSession session = request.getSession();
		int lecture_no=(int)session.getAttribute("lecture_no");

		enrollService.update_request(lecture_no, user_id);
	}

	@PostMapping(value="/enroll/list_request")
	public List<UserVO> list_request(HttpServletRequest request){
		//lecture_no
		HttpSession session = request.getSession();
		int lecture_no=(int)session.getAttribute("lecture_no");
		return enrollService.list_request(lecture_no);
	}
	
	@CrossOrigin("*")
	@PostMapping(produces = "application/json;charset=UTF-8", value="/lecture")
	public void insert(@RequestBody LectureVO lecture, HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
		
		lecture.setTeacher_id(user_session.getUser_id());
		
		int lecture_no=rand(7); 
		Object db_lec_no = lectureService.lecture_no_search(lecture_no); 
		
		while(db_lec_no!=null) {
			lecture_no=rand(7);
			db_lec_no = lectureService.lecture_no_search(lecture_no);
		
		}
		lecture.setLecture_no(lecture_no);
		lectureService.insert(lecture);
				
	}
	public int rand(int num) {
		Random random = new Random();
		String numStr="";
		int numInt=0;
		
		for(int i=0; i<num; i++) {
			String ran=Integer.toString(random.nextInt(10));
			numStr += ran;
		}
		numInt=Integer.parseInt(numStr);
		return numInt;
	}
	
	@RequestMapping(value="/lecture/teach_mylec")
	public List<LectureVO> teacher_mylec(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
				
		return lectureService.teacher_mylec(user_session.getUser_id());
	}
	
}
