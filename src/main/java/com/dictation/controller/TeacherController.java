package com.dictation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dictation.service.CourseService;
import com.dictation.service.EnrollService;
import com.dictation.service.LectureService;
import com.dictation.service.UserService;
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
@RequestMapping(value = "/api/teacher")
public class TeacherController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private LectureService lectureService;
	@Autowired
	private UserService userService;

	@PostMapping(produces = "application/json;charset=UTF-8", value = "/course")
	public void insert(@RequestParam Map<String, Object> map, @Param(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");

		int course_no = Integer.parseInt((String) map.get("course_no"));
		int question_no = Integer.parseInt((String) map.get("question_no"));
		String question = (String) map.get("question");
		String originalfileName = null;
		String save_file_nm = null;

		CourseVO course = new CourseVO();
		course.setLecture_no(lecture_session);
		course.setCourse_no(course_no);
		course.setQuestion_no(question_no);
		course.setQuestion(question);

		if (file.isEmpty()) {

		} else {

			originalfileName = file.getOriginalFilename();

			// SAVE_FILE_NM
			UUID uuid = UUID.randomUUID();
			save_file_nm = uuid.toString() + "_" + originalfileName;

			File dest = new File("C:\\Users\\jyj\\Desktop\\audiotest" + save_file_nm);
			//File dest = new File("/home/dictation/audio" + save_file_nm);
			file.transferTo(dest);

		}

		course.setFile_nm(originalfileName);
		course.setSave_file_nm(save_file_nm);

		courseService.insert(course);
	}

	@PostMapping(value = "/course/dic_modify")
	public void dic_modify(@RequestParam Map<String, Object> map, @Param(value = "file_nm") MultipartFile file_nm,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");

		int course_no = Integer.parseInt((String) map.get("course_no"));
		int question_no = Integer.parseInt((String) map.get("question_no"));
		String question = (String) map.get("question");
		boolean change_file2 = Boolean.parseBoolean((String) map.get("change_file"));
		String originalfileName = null;
		String save_file_nm = null;

		CourseVO course1 = new CourseVO();
		course1.setLecture_no(lecture_session);
		course1.setCourse_no(course_no);
		course1.setQuestion_no(question_no);
		course1.setQuestion(question);

		courseService.dic_modify_question(course1);

		if (change_file2 == true) {
			if (file_nm.isEmpty()) {

			} else {

				originalfileName = file_nm.getOriginalFilename();

				// SAVE_FILE_NM
				UUID uuid = UUID.randomUUID();
				save_file_nm = uuid.toString() + "_" + originalfileName;

				File dest = new File("C:\\Users\\jyj\\Desktop\\audiotest" + save_file_nm);
				//File dest = new File("/home/dictation/audio" + save_file_nm);
				file_nm.transferTo(dest);

			}
			CourseVO course2 = new CourseVO();
			course2.setLecture_no(lecture_session);
			course2.setCourse_no(course_no);
			course2.setQuestion_no(question_no);
			course2.setFile_nm(originalfileName);
			course2.setSave_file_nm(save_file_nm);

			String delete_filenm = courseService.getById(course2).getSave_file_nm();
			File delete_file = new File("C:\\Users\\jyj\\Desktop\\audiotest" + delete_filenm);
			delete_file.delete();

			courseService.dic_modify_file(course2);
		}
	}

	@GetMapping(value = "/course/max_dic_course")
	public int max_dic_course(HttpServletRequest request) {

		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");
		return courseService.max_dic_course(lecture_session);
	}

	@GetMapping(value = "/course/dic_answers/{course_no}")
	public List<CourseVO> dic_answers(@PathVariable("course_no") int course_no, HttpServletRequest request) {
		CourseVO course2 = new CourseVO();

		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");
		course2.setLecture_no(lecture_session);
		course2.setCourse_no(course_no);

		List<CourseVO> course_sort = courseService.dic_answers(course2);
		Collections.sort(course_sort);

		return course_sort;
	}

	@PostMapping(produces = "application/json;charset=UTF-8", value = "/enroll")
	public void insert(@RequestBody EnrollVO enroll, HttpServletRequest request) {

		HttpSession session = request.getSession();
		enroll.setLecture_no((int) session.getAttribute("lecture_no"));

		enrollService.insert(enroll);
	}

	@PostMapping(value = "/enroll/delete")
	public void delete(@RequestBody EnrollVO enroll) {
		enrollService.delete(enroll);
	}

	@GetMapping(value = "/enroll/update/{user_id}")
	public void update_request(@PathVariable("user_id") String user_id, HttpServletRequest request) {

		HttpSession session = request.getSession();
		int lecture_no = (int) session.getAttribute("lecture_no");

		enrollService.update_request(lecture_no, user_id);
	}

	@PostMapping(value = "/enroll/list_request")
	public List<UserVO> list_request(HttpSession session) {
		// lecture_no
		int lecture_no = (int) session.getAttribute("lecture_no");
		return enrollService.list_request(lecture_no);
	}

	@CrossOrigin("*")
	@PostMapping(produces = "application/json;charset=UTF-8", value = "/lecture")
	public void insert(@RequestBody LectureVO lecture, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		UserVO user_session = (UserVO) session.getAttribute("user");

		lecture.setTeacher_id(user_session.getUser_id());

		int lecture_no = rand(7);
		Object db_lec_no = lectureService.lecture_no_search(lecture_no);

		while (db_lec_no != null) {
			lecture_no = rand(7);
			db_lec_no = lectureService.lecture_no_search(lecture_no);

		}
		lecture.setLecture_no(lecture_no);
		lectureService.insert(lecture);

	}

	public int rand(int num) {
		Random random = new Random();
		String numStr = "";
		int numInt = 0;

		for (int i = 0; i < num; i++) {
			String ran = Integer.toString(random.nextInt(10));
			numStr += ran;
		}
		numInt = Integer.parseInt(numStr);
		return numInt;
	}

	@RequestMapping(value = "/lecture/teach_mylec")
	public List<LectureVO> teacher_mylec(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UserVO user_session = (UserVO) session.getAttribute("user");

		return lectureService.teacher_mylec(user_session.getUser_id());
	}

	// @PostMapping(value = "/excelup")
	// public int excelup(@Param(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {

	// 	int error_yn = 0;

	// 	UUID uuid = UUID.randomUUID();
	// 	String save_file_path = "" + uuid;
	// 	// user
	// 	HttpSession session = request.getSession();
	// 	UserVO user_session = (UserVO) session.getAttribute("user");

	// 	try {
	// 		File dest = new File(save_file_path);
	// 		file.transferTo(dest);

	// 		long start = System.currentTimeMillis();
	// 		FileInputStream inputStream = new FileInputStream(save_file_path);
	// 		Workbook workbook = new XSSFWorkbook(inputStream);
	// 		Sheet firstSheet = workbook.getSheetAt(0);
	// 		Iterator<Row> rowIterator = firstSheet.iterator();
	// 		Date date = new Date();

	// 		int count = 0;

	// 		rowIterator.next(); // skip the header row

	// 		while (rowIterator.hasNext()) {
	// 			UserVO user = new UserVO();
	// 			EnrollVO enroll = new EnrollVO();
	// 			Row nextRow = rowIterator.next();
	// 			Iterator<Cell> cellIterator = nextRow.cellIterator();
	// 			String name = "";

	// 			// insert users
	// 			while (cellIterator.hasNext()) {
	// 				Cell nextCell = cellIterator.next();

	// 				int columnIndex = nextCell.getColumnIndex();

	// 				switch (columnIndex) {
	// 					case 0:
	// 						name = getStringValue(nextCell);
	// 						user.setUser_id(name);
	// 						// statement.setString(1, name);
	// 						break;
	// 					case 1:
	// 						String pw = getStringValue(nextCell);
	// 						user.setPw(pw);
	// 						// int progress = (int) nextCell.getNumericCellValue();
	// 						// statement.setInt(3, progress);
	// 					case 2:
	// 						String school_cd = getStringValue(nextCell);
	// 						user.setSchool_cd(school_cd);
	// 					case 3:
	// 						String kor_nm = getStringValue(nextCell);
	// 						user.setKor_nm(kor_nm);
	// 					case 4:
	// 						String end_nm = getStringValue(nextCell);
	// 						user.setEnd_nm(end_nm);
	// 					case 5:
	// 						int grade = getIntValue(nextCell);
	// 						user.setGrade(grade);
	// 					case 6:
	// 						String ban = getStringValue(nextCell);
	// 						user.setBan(ban);
	// 					case 7:
	// 						String cel_phone_no = getStringValue(nextCell);
	// 						user.setCel_phone_no(cel_phone_no);
	// 					case 8:
	// 						String hom_phone_no = getStringValue(nextCell);
	// 						user.setHom_phone_no(hom_phone_no);
	// 					case 9:
	// 						String gender_cd = getStringValue(nextCell);
	// 						if (gender_cd.equals("0")) {
	// 							user.setGender_cd("002001");
	// 						} else if (gender_cd.equals("1")) {
	// 							user.setGender_cd("002002");
	// 						}
	// 					case 10:
	// 						String email = getStringValue(nextCell);
	// 						user.setEmail(email);

	// 				}

	// 			}
	// 			user.setPosition_cd("003003");
	// 			user.setInput_id(user_session.getUser_id());
	// 			userService.insert(user);

	// 			// enroll insert
	// 			enroll.setLecture_no((int) session.getAttribute("lecture_no"));
	// 			enroll.setUser_id(name);
	// 			enroll.setApproval_cd("승인");
	// 			enroll.setApproval_dt(date);
	// 			enroll.setInput_id(user_session.getUser_id());
	// 			enrollService.insert(enroll);

	// 		}

	// 		workbook.close();

	// 		long end = System.currentTimeMillis();
	// 		error_yn = 0;

	// 	} catch (Exception ex1) {
	// 		ex1.printStackTrace();
	// 		error_yn = 1;
	// 	} 

	// 	File delete_file = new File(save_file_path);
	// 	delete_file.delete();

	// 	return error_yn;
	// }

	@PostMapping(value="/users/list_request_save")
	public void list_request_save(@RequestBody List<UserVO> user, HttpServletRequest request) throws Exception {
		//lecture_no
		HttpSession session = request.getSession();
		int lecture_no = (int)session.getAttribute("lecture_no");
		List<UserVO> old_list = enrollService.list_request(lecture_no);
	
		List<UserVO> add1 = new ArrayList(user);
		List<UserVO> delete1 = new ArrayList(old_list);
		
		for(int i=0; i<add1.size(); i++) {
			for(int j=0; j<old_list.size(); j++) {
				if(add1.get(i).getUser_id().equals(old_list.get(j).getUser_id())) {
					add1.remove(i);
					i--;
					j=old_list.size()+1;
				}
			}		
		}
		
		for(int i=0; i<delete1.size(); i++) {
			for(int j=0; j<user.size(); j++) {
				if(delete1.get(i).getUser_id().equals(user.get(j).getUser_id())) {
					delete1.remove(i);
					i--;
					j=user.size()+1;
				}
			}		
		}
		
		for(UserVO ad:add1) {
			EnrollVO enroll = new EnrollVO();
			enroll.setLecture_no(lecture_no);
			enroll.setUser_id(ad.getUser_id());
			enroll.setApproval_cd("승인");
			enrollService.insert(enroll);
		}
		
		for(UserVO de:delete1) {
			EnrollVO enroll = new EnrollVO();
			enroll.setLecture_no(lecture_no);
			enroll.setUser_id(de.getUser_id());
			enrollService.delete(enroll);
		}
	}
}
