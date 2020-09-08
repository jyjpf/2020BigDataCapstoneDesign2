package com.dictation.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// import com.dictation.Security.JWTTokenProvider;
import com.dictation.service.BoardService;
import com.dictation.service.CommonService;
import com.dictation.service.CourseService;
import com.dictation.service.EnrollService;
import com.dictation.service.LectureService;
import com.dictation.vo.CourseVO;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.LectureVO;
import com.dictation.vo.UserVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/common")
// @RequiredArgsConstructor
public class CommonController {

	private Logger logger = LogManager.getLogger(CommonController.class);

	@Autowired
	private CourseService courseService;
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private LectureService lectureService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommonService commonService;

	// private final JWTTokenProvider jwtTokenProvider;


	@CrossOrigin("*") 
	@PostMapping(value = "/login")
	public String signin(@RequestBody Map<String, Object> params, HttpSession session) {

		logger.info("login");
		UserVO user = commonService.login(params);

		// ArrayList<String> role = new ArrayList<String>();
		// role.add("ADMIN");
		// return jwtTokenProvider.createToken(user.getUser_id(), role);
		return null;
	} 
	
    //according to id delete
	@GetMapping(value="/course/delete/{lecture_no}&{course_no}&{question_no}")
	public void delete(@PathVariable("lecture_no") int lecture_no, @PathVariable("course_no") int course_no, @PathVariable("question_no") int question_no) {
		CourseVO course=new CourseVO();
		course.setLecture_no(lecture_no);
		course.setCourse_no(course_no);
		course.setQuestion_no(question_no);
		courseService.delete(course);
	}
	
	//modify
	//lecture_no는 같아야 함
	@PostMapping(value="/course/update")
	public void update(@RequestBody CourseVO course) {
		courseService.update(course);
	}

	//according to id Query students
	@GetMapping(value="/course/get/{lecture_no}&{course_no}&{question_no}")
	public CourseVO getById(@PathVariable("lecture_no") int lecture_no, @PathVariable("course_no") int course_no, @PathVariable("question_no") int question_no) {
		CourseVO course2=new CourseVO();
		course2.setLecture_no(lecture_no);
		course2.setCourse_no(course_no);
		course2.setQuestion_no(question_no);
		
		CourseVO course = courseService.getById(course2);
		return course;
	}
	
	//All queries
	@PostMapping(value="/course/list")
	public List<CourseVO> list(){
		return courseService.list();
	}	
	
	
	//파일 업로드를 위함(1개의 파일)
	@CrossOrigin("*")
	@PostMapping(value="/course/fileupload")
	//@ResponseStatus(HttpStatus.CREATED)//@RequestParam("file") 
	public String upload(HttpServletRequest request, @RequestPart MultipartFile file) throws Exception {
		
		if(file.isEmpty()){ //업로드할 파일이 없을 시
            System.out.println("파일없음");
            return "";
        }else {
        	System.out.println("file 실행 !!");
    		
    		//파일 이름가져옴(FILE_NM)
    		String originalfileName = file.getOriginalFilename();
    	
    		/*
    		String fileUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(originalfileName)
                    .toUriString();
            */
    		
    			
    		//SAVE_FILE_NM
    		UUID uuid =UUID.randomUUID();
    		String save_file_nm=uuid.toString() +"_" +originalfileName;
    				
    		//파일 지정한 경로로 저장(save_file_nm 파일이름으로 저장)
    		File dest = new File("C:/Temp/" + save_file_nm);
    		file.transferTo(dest);
    		
    		return save_file_nm;

        }
	}
	
	
	//파일 업로드를 위함(여러개의 파일)
	@CrossOrigin("*")
	@PostMapping(value="/course/fileupload_list")
	//@ResponseStatus(HttpStatus.CREATED)//@RequestParam("file") 
	public String upload_list(HttpServletRequest request, @RequestPart List<MultipartFile> file) throws Exception {
		
		if(file.isEmpty()){ //업로드할 파일이 없을 시
            System.out.println("파일없음");
            return "";
        }else {
        	
        	for(int i=0; i<file.size(); i++) {
        		System.out.println("file 실행 !!");
	    		
	    		//파일 이름가져옴(FILE_NM)
	    		String originalfileName = file.get(i).getOriginalFilename();
	    	
	    		/*
	    		String fileUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
	                    .path("/downloadFile/")
	                    .path(originalfileName)
	                    .toUriString();
	            */
	    		
	    			
	    		//SAVE_FILE_NM
	    		UUID uuid =UUID.randomUUID();
	    		String save_file_nm=uuid.toString() +"_" +originalfileName;
	    				
	    		//파일 지정한 경로로 저장(save_file_nm 파일이름으로 저장)
	    		File dest = new File("C:/Temp/" + save_file_nm);
	    		file.get(i).transferTo(dest);
	    	
        	}
        	
        }
		
		return "성공";
	}
	
	//modify
	//user_id는 같아야 함
	@PostMapping(value="/enroll/update")
	public void update(@RequestBody EnrollVO enroll) { //user_id, lecture_no값 필수
		enrollService.update(enroll);
	}

	
	//according to id Query students
	@GetMapping(value="/enroll/get/{user_id}")
	public EnrollVO getById(@PathVariable("user_id") String user_id) {
		EnrollVO enroll = enrollService.getById(user_id);
		return enroll;
	}
	
	//All queries
	@PostMapping(value="/enroll/list")
	public List<EnrollVO> en_list(){
		return enrollService.list();
	}
	
	//according to id delete
	//lecture를 지우면 DB에 해당lecture_no이 존재하는 모든 데이터를 지워야함(board, course, enroll, study, lecture) 
	@GetMapping(value="/lecture/delete/{lecture_no}")
	public void delete(@PathVariable("lecture_no") int lecture_no) {

		boardService.lecture_delete(lecture_no);
		enrollService.lecture_delete(lecture_no);
		courseService.lecture_delete(lecture_no);
		lectureService.delete(lecture_no);
	}
	//modify
	//lecture_no는 같아야 함
	@PostMapping(value="/lecture/update")
	public void update(@RequestBody LectureVO lecture) {
		lectureService.update(lecture);
	}

	//according to id Query students
	@GetMapping(value="/lecture/get/{lecture_no}")
	public LectureVO getById_nosession(@PathVariable("lecture_no") int lecture_no) {

		LectureVO lecture = lectureService.getById(lecture_no);
		return lecture;
	}
	
	@GetMapping(value="/lecture/get")
	public LectureVO getById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		
		LectureVO lecture = lectureService.getById(lecture_session);
		return lecture;
	}
	
	
	//All queries
	@RequestMapping(value="/lecture/list")
	public List<LectureVO> lec_list(){
		return lectureService.list();
	}
	
	//강좌들어갈때 lecture_no 세션값 생성
	//나중에는 post로 lecture_no 값 줄것
	@GetMapping(value = "/lecture/lecture_no/{lecture_no}")
	public String lecture_no(@PathVariable("lecture_no") int lecture_no, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("lecture_no", lecture_no);
		int lecture_session=(int)session.getAttribute("lecture_no");

	    return "lecture_no";
	}

	//세션값 확인후 지우는 메소드(test용)
	@GetMapping(value = "/lecture/session")
	public String session(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

	  return "login/user_id&lecture_no";  
	}	

}
