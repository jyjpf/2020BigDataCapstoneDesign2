package com.dictation.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.logging.LoggerFactory;
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

import com.dictation.service.BoardService;
import com.dictation.service.CourseService;
import com.dictation.service.EnrollService;
import com.dictation.service.LectureService;
import com.dictation.service.UserService;
import com.dictation.vo.CourseVO;
import com.dictation.vo.EnrollVO;
import com.dictation.vo.LectureVO;
import com.dictation.vo.UserVO;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/common")
public class CommonController {

	private static final Logger logger = LogManager.getLogger(CommonController.class);

	@Autowired
	private CourseService courseService;
	@Autowired
	private EnrollService enrollService;
	@Autowired
	private LectureService lectureService;
	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;

	@PostMapping(produces = "application/json;charset=UTF-8", value = "/signup")
	public void insert(@RequestBody UserVO user) {

		// position_cd
		user.setDae_p("003");
		if (user.getPosition_cd().equals("������")) {
			user.setSo_p("001");
		} else if (user.getPosition_cd().equals("������")) {
			user.setSo_p("002");
		} else if (user.getPosition_cd().equals("�л�")) {
			user.setSo_p("003");
		}

		// gender_cd
		user.setDae_g("002");
		System.out.println(user.getPosition_cd());
		if (user.getGender_cd().equals("G01")) {
			user.setSo_g("001");
		} else if (user.getGender_cd().equals("G02")) {
			user.setSo_g("002");
		}
		userService.insert(user);
	}

	@GetMapping(value = "login/{user_id}&{pw}")
	public UserVO login(@PathVariable("user_id") String user_id, @PathVariable("pw") String pw,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		UserVO user = userService.getById(user_id);

		if (user.equals(null) || user == null) {
			user.setLoginYn("0");
			return user;
		} else if (user.getPw().equals(pw)) {

			session.setAttribute("user", user);

			UserVO user_session = (UserVO) session.getAttribute("user");
			user.setLoginYn("1");
			return user;
		} else {
			session.setAttribute("login_fail", pw);
			user.setLoginYn("0");

			return user;
		}
	}

	// according to id delete
	@GetMapping(value = "/course/delete/{lecture_no}&{course_no}&{question_no}")
	public void delete(@PathVariable("lecture_no") int lecture_no, @PathVariable("course_no") int course_no,
			@PathVariable("question_no") int question_no) {
		CourseVO course = new CourseVO();
		course.setLecture_no(lecture_no);
		course.setCourse_no(course_no);
		course.setQuestion_no(question_no);
		courseService.delete(course);
	}

	// modify
	// lecture_no�� ���ƾ� ��
	@PostMapping(value = "/course/update")
	public void update(@RequestBody CourseVO course) {
		courseService.update(course);
	}

	// according to id Query students
	@GetMapping(value = "/course/get/{lecture_no}&{course_no}&{question_no}")
	public CourseVO getById(@PathVariable("lecture_no") int lecture_no, @PathVariable("course_no") int course_no,
			@PathVariable("question_no") int question_no) {
		CourseVO course2 = new CourseVO();
		course2.setLecture_no(lecture_no);
		course2.setCourse_no(course_no);
		course2.setQuestion_no(question_no);

		CourseVO course = courseService.getById(course2);
		return course;
	}

	// All queries
	@PostMapping(value = "/course/list")
	public List<CourseVO> list() {
		return courseService.list();
	}

	@CrossOrigin("*")
	@PostMapping(value = "/course/fileupload")
	// @ResponseStatus(HttpStatus.CREATED)//@RequestParam("file")
	public String upload(HttpServletRequest request, @RequestPart MultipartFile file) throws Exception {

		if (file.isEmpty()) { // ���ε��� ������ ���� ��
			System.out.println("���Ͼ���");
			return "";
		} else {
			System.out.println("file ���� !!");

			// ���� �̸�������(FILE_NM)
			String originalfileName = file.getOriginalFilename();

			/*
			 * String fileUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
			 * .path("/downloadFile/") .path(originalfileName) .toUriString();
			 */

			// SAVE_FILE_NM
			UUID uuid = UUID.randomUUID();
			String save_file_nm = uuid.toString() + "_" + originalfileName;

			File dest = new File("C:/Temp/" + save_file_nm);
			file.transferTo(dest);

			return save_file_nm;

		}
	}

	@CrossOrigin("*")
	@PostMapping(value = "/course/fileupload_list")
	// @ResponseStatus(HttpStatus.CREATED)//@RequestParam("file")
	public String upload_list(HttpServletRequest request, @RequestPart List<MultipartFile> file) throws Exception {

		if (file.isEmpty()) { // ���ε��� ������ ���� ��
			return "";
		} else {

			for (int i = 0; i < file.size(); i++) {

				String originalfileName = file.get(i).getOriginalFilename();

				/*
				 * String fileUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
				 * .path("/downloadFile/") .path(originalfileName) .toUriString();
				 */

				// SAVE_FILE_NM
				UUID uuid = UUID.randomUUID();
				String save_file_nm = uuid.toString() + "_" + originalfileName;

				// ���� ������ ��η� ����(save_file_nm �����̸����� ����)
				File dest = new File("C:/Temp/" + save_file_nm);
				file.get(i).transferTo(dest);

			}

		}

		return "����";
	}

	// modify
	// user_id�� ���ƾ� ��
	@PostMapping(value = "/enroll/update")
	public void update(@RequestBody EnrollVO enroll) { // user_id, lecture_no�� �ʼ�
		enrollService.update(enroll);
	}

	// according to id Query students
	@GetMapping(value = "/enroll/get/{user_id}")
	public EnrollVO getById(@PathVariable("user_id") String user_id) {
		EnrollVO enroll = enrollService.getById(user_id);
		return enroll;
	}

	// All queries
	@PostMapping(value = "/enroll/list")
	public List<EnrollVO> en_list() {
		return enrollService.list();
	}

	// according to id delete
	// lecture�� ����� DB�� �ش�lecture_no�� �����ϴ� ��� �����͸� ��������(board,
	// course, enroll, study, lecture)
	@GetMapping(value = "/lecture/delete/{lecture_no}")
	public void delete(@PathVariable("lecture_no") int lecture_no) {

		boardService.lecture_delete(lecture_no);
		enrollService.lecture_delete(lecture_no);
		courseService.lecture_delete(lecture_no);
		lectureService.delete(lecture_no);
	}

	// modify
	// lecture_no�� ���ƾ� ��
	@PostMapping(value = "/lecture/update")
	public void update(@RequestBody LectureVO lecture) {
		lectureService.update(lecture);
	}

	// according to id Query students
	@GetMapping(value = "/lecture/get")
	public LectureVO getById(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int lecture_session = (int) session.getAttribute("lecture_no");

		LectureVO lecture = lectureService.getById(lecture_session);
		return lecture;
	}

	// All queries
	@RequestMapping(value = "/lecture/list")
	public List<LectureVO> lec_list() {
		return lectureService.list();
	}

	// ���µ��� lecture_no ���ǰ� ����
	// ���߿��� post�� lecture_no �� �ٰ�
	@GetMapping(value = "/lecture/lecture_no/{lecture_no}")
	public String lecture_no(@PathVariable("lecture_no") int lecture_no, HttpServletRequest request) throws Exception {

		System.out.println("lecture_no�� ���� ���ǰ��� ��");

		HttpSession session = request.getSession();
		session.setAttribute("lecture_no", lecture_no);
		int lecture_session = (int) session.getAttribute("lecture_no");
		System.out.println("lecture_no ���ǰ� :" + lecture_session);

		return "lecture_no";
	}

	// ���ǰ� Ȯ���� ����� �޼ҵ�(test��)
	@GetMapping(value = "/lecture/session")
	public String session(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		return "login/user_id&lecture_no";

	}

}
