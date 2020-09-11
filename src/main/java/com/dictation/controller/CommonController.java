package com.dictation.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.dictation.Common.PositionCode;
import com.dictation.Security.JWTTokenProvider;
import com.dictation.service.CommonService;
import com.dictation.service.CourseService;
import com.dictation.service.EnrollService;
import com.dictation.service.UserService;
import com.dictation.vo.CourseVO;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.UserVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/common")
public class CommonController {

	private Logger logger = LogManager.getLogger(CommonController.class);

	@Autowired
	private CourseService courseService;
	@Autowired
	private EnrollService enrollService;

	@Autowired
	private CommonService commonService;
	@Autowired
	private UserService userService;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;



	@PostMapping(value = "/login")
	public ResponseEntity<?> signin(@RequestBody Map<String, Object> params) {

		HttpStatus status;
		Map<String, Object> result = new HashMap<String, Object>();
		UserVO user = commonService.login(params);

		if(user == null) {

			status = HttpStatus.FORBIDDEN;
			result.put("msg", "Login Failed");

		} else {

			status = HttpStatus.OK;

			if(user.getPosition_cd().equals(PositionCode.ROME_ADMIN)) {
				result.put("token", jwtTokenProvider.createToken(
					user.getUser_id(), 
					user.getSchool_cd(), 
					"ROLE_ADMIN")
				);
			} else if(user.getPosition_cd().equals(PositionCode.ROME_TEACHER)) {
				result.put("token", jwtTokenProvider.createToken(
					user.getUser_id(), 
					user.getSchool_cd(), 
					"ROLE_TEACHER")
				);
			} else if(user.getPosition_cd().equals(PositionCode.ROLE_STUDENT)) {
				result.put("token", jwtTokenProvider.createToken(
					user.getUser_id(), 
					user.getSchool_cd(), 
					"ROLE_STUDENT")
				);
			} else {
				status = HttpStatus.BAD_REQUEST;
				result.put("msg", "Undefined Position");
			}
			
		}

		return ResponseEntity.status(status).body(result);

	}

	// TODO: 비밀번호 암호화
	@PostMapping(value = "/signup")
	public ResponseEntity<?> signup(@RequestBody UserVO user) {

		HttpStatus status;
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			userService.insert(user);
			status = HttpStatus.OK;
			result.put("msg", "");
		} catch(Exception e) {
			result.put("msg", e);
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).body(result);
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

}
