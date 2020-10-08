package com.dictation.controller;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.dictation.Common.DictationUtils;
import com.dictation.service.*;
import com.dictation.vo.CourseVO;


import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/lecture/{lecture_no}/dictation")
public class DictationController {

	private static final Logger logger = LogManager.getLogger(DictationController.class);
	private static final String filepath = "./audio/";

	@Autowired
	private DictationService dictationService;

	@GetMapping(value = "/{course_no}")
	public List<CourseVO> get(
			@PathVariable(value = "lecture_no") String lecture_no,
			@PathVariable(value = "course_no") String course_no) {

		Map<String, Object> params = new HashMap<>();
		params.put("lecture_no", lecture_no);
		params.put("course_no", course_no);

		return dictationService.get(params);
	}

	@PostMapping
	@Secured("ROLE_TEACHER")
	public void insert(
			@RequestParam Map<String, Object> map,
			@PathVariable(value="lecture_no") String lecture_no,
			@Param(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {

		CourseVO course = new CourseVO();
		course.setLecture_no(lecture_no);
		course.setCourse_no((String) map.get("course_no"));
		course.setQuestion_no((String) map.get("question_no"));
		course.setQuestion((String) map.get("question"));

		if (!file.isEmpty()) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());

			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);

			File dest = new File(filepath + saveFilename);
			file.transferTo(dest);
		}

		dictationService.insert(course);
	}

	@PutMapping
	@Secured("ROLE_TEACHER")
	public void update(
			@RequestParam Map<String, Object> map,
			@PathVariable(value="lecture_no") String lecture_no,
			@Param(value = "file") MultipartFile file,
			HttpServletRequest request) throws Exception {

		CourseVO course = new CourseVO();
		course.setLecture_no(lecture_no);
		course.setCourse_no((String) map.get("course_no"));
		course.setQuestion_no((String) map.get("question_no"));
		course.setQuestion((String) map.get("question"));

		if (!file.isEmpty()) {
			String saveFilename = DictationUtils.fileNameToHash(file.getOriginalFilename());

			course.setFile_nm(file.getOriginalFilename());
			course.setSave_file_nm(saveFilename);

			File dest = new File(filepath + saveFilename);
			file.transferTo(dest);
		}

		dictationService.update(course);
	}

	@DeleteMapping
	@Secured("ROLE_TEACHER")
	public void delete() throws Exception {

	}

	/*


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

	@PostMapping(value = "/enroll")
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


	@RequestMapping(value = "/lecture/teach_mylec")
	public List<LectureVO> teacher_mylec(HttpServletRequest request, @AuthenticationPrincipal UserVO activeUser) throws Exception {
		
		return lectureService.teacher_mylec(activeUser.getUser_id());
	}

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


	@GetMapping(value="/course/finish_yes/{course_no}")
	public void finish_yes(@PathVariable("course_no") int course_no, HttpServletRequest request) {
		CourseVO course = new CourseVO();
		course.setCourse_no(course_no);

		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		course.setLecture_no(lecture_session);
		course.setFinish_yn("1");

		courseService.finish_yes(course);
	}

	@PostMapping(value="/enroll/answer")
	public boolean[] answer(@RequestBody CourseVO[] courseList, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		UserVO user_session=(UserVO)session.getAttribute("user");
		String student_id = user_session.getUser_id();
		int lecture_session=(int)session.getAttribute("lecture_no");

		String question;
		CourseVO course;
		boolean[] answer=new boolean[courseList.length];

		for(int i=0; i<courseList.length; i++) {
			question=courseList[i].getQuestion();
			courseList[i].setLecture_no(lecture_session);
			course=courseService.getById(courseList[i]);
			if(question.equals(course.getQuestion())) {
				answer[i]=true;
			}else {
				answer[i]=false;
			}
		}

		EnrollVO enroll=new EnrollVO();
		enroll.setLecture_no(courseList[1].getLecture_no());
		enroll.setUser_id(student_id);

		int score=0;
		for(int i=0; i<answer.length; i++) {
			if(answer[i] == true) {
				score += 10;
			}
		}

		return answer;

	}

	@GetMapping(value="/course/finish_yes_cl")
	public List<Integer> finish_yes_cl(HttpServletRequest request) {

		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");

		return courseService.finish_yes_cl(lecture_session);
	}

	@PostMapping(value="/study/insert")
	public void study_insert(@RequestParam Map<String, Object> map, HttpServletRequest request) {

		int course_no=Integer.parseInt((String)map.get("course_no"));
		int question_no=Integer.parseInt((String)map.get("question_no"));
		String answer=(String)map.get("answer");

		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		UserVO user_session=(UserVO)session.getAttribute("user");

		StudyVO study=new StudyVO();
		study.setCourse_no(course_no);
		study.setQuestion_no(question_no);
		study.setAnswer(answer);
		study.setLecture_no(lecture_session);
		study.setUser_id(user_session.getUser_id());

		CourseVO course=new CourseVO();
		course.setLecture_no(lecture_session);
		course.setCourse_no(course_no);
		course.setQuestion_no(question_no);
		CourseVO c=courseService.getById(course);
		if(c.getQuestion().equals(answer)) {
			study.setCorrect_yn("1");
		}else {
			study.setCorrect_yn("0");
		}
		studyService.insert(study);
	}

	@PostMapping(value="/course/audio")
	public String audio(@Param(value = "course_no") int course_no, @Param(value = "question_no") int question_no,
						HttpServletRequest request) {

		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");

		CourseVO course=new CourseVO();
		course.setLecture_no(lecture_session);
		course.setCourse_no(course_no);
		course.setQuestion_no(question_no);

		String savefile_nm=courseService.getById(course).getSave_file_nm();
		String url="C:/Temp/"+savefile_nm;

		return url;
	}

	@GetMapping(value="/course/sound")
	public void soundPlay() {
		File file = new File("");
		AudioInputStream audioInputStream =null;
		SourceDataLine auline =null;

		try {
			audioInputStream=AudioSystem.getAudioInputStream(file);
		}catch(UnsupportedAudioFileException e1) {
			e1.printStackTrace();
			return;
		}catch(IOException e1) {
			e1.printStackTrace();
			return;
		}
		AudioFormat format = audioInputStream.getFormat();
		DataLine.Info info= new DataLine.Info(SourceDataLine.class, format);
		try {
			auline=(SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		}catch(LineUnavailableException e) {
			e.printStackTrace();
			return;
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		auline.start();
		int nBytesRead=0;
		final int EXTERNAL_BUFFER_SIZE=524288;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

		try {
			while(nBytesRead !=-1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if(nBytesRead >=0) {
					auline.write(abData, 0, nBytesRead);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
			return;
		}finally {
			auline.drain();
			auline.close();
		}
	}

	 */
}
