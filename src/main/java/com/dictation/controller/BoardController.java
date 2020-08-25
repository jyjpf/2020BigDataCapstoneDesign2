package com.dictation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dictation.service.BoardService;
import com.dictation.vo.BoardVO;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
    //�Խ��� ���ۼ� insert(���ε� ���� ������)
	@PostMapping(produces = "application/json;charset=UTF-8")
	public void insert(@RequestParam Map<String, Object> map, @Param(value = "file") MultipartFile file, HttpServletRequest request) throws Exception{
		
		//map(BoardVO)
		String board_cd=(String)map.get("board_cd");
		String content=(String)map.get("content");
		String title=(String)map.get("title");
		//file
		String originalfileName = null;
		String save_file_nm=null;
		
		BoardVO board = new BoardVO();
		board.setBoard_cd(board_cd);
		board.setContent(content);
		board.setTitle(title);
		
		int lecture_no;
		String so_b = null;
		String no = null;
		
		//lecture_no
		HttpSession session = request.getSession();
		lecture_no=(int)session.getAttribute("lecture_no");
		System.out.println(lecture_no);
		board.setLecture_no(lecture_no);
		
		//board_cd, no
		board.setDae_b("006");
		if(board.getBoard_cd().equals("001")) {//����Ʈ���� ���������̸� 001�� ������ ���� �ѱ�  
			so_b="001";
			no=lecture_no+"001";
		}else if(board.getBoard_cd().equals("002")) {//����Ʈ���� �н��ڷ��̸� 002�� ������ ���� �ѱ�
			so_b="002";
			no=lecture_no+"002";
		}else if(board.getBoard_cd().equals("003")) {////����Ʈ���� Q&A�̸� 003�� ������ ���� �ѱ�
			so_b="003";
			no=lecture_no+"003";
		}
		board.setSo_b(so_b);
		board.setNo(Long.valueOf(no));
		
		if(file.isEmpty()){ //���ε��� ������ ���� ��
            System.out.println("���Ͼ���");
        }else {
        	System.out.println("file ���� !!");
    		
    		//���� �̸�������(FILE_NM)
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
    		
    		System.out.println("�����̸� : "+originalfileName);
    		System.out.println("���ο� �����̸� : "+save_file_nm);
    		//System.out.println("���ϰ�� : "+fileUrl);
        }
		
		board.setFile_nm(originalfileName);
		board.setSave_file_nm(save_file_nm);
		
		boardService.insert(board);
	}

	//�Խ��� ���ۼ� insert(���ε� ����x)
	@PostMapping(value="/insert_nofile")
	public void insert_nofile(@RequestParam Map<String, Object> map, HttpServletRequest request) throws Exception{
		
		//map(BoardVO)
		String board_cd=(String)map.get("board_cd");
		String content=(String)map.get("content");
		String title=(String)map.get("title");
		
		BoardVO board = new BoardVO();
		board.setBoard_cd(board_cd);
		board.setContent(content);
		board.setTitle(title);
		
		int lecture_no;
		String so_b = null;
		String no = null;
		
		//lecture_no
		HttpSession session = request.getSession();
		lecture_no=(int)session.getAttribute("lecture_no");
		System.out.println(lecture_no);
		board.setLecture_no(lecture_no);
	
		//board_cd, no
		board.setDae_b("006");
		if(board.getBoard_cd().equals("001")) {//����Ʈ���� ���������̸� 001�� ������ ���� �ѱ�  
			so_b="001";
			no=lecture_no+"001";
		}else if(board.getBoard_cd().equals("002")) {//����Ʈ���� �н��ڷ��̸� 002�� ������ ���� �ѱ�
			so_b="002";
			no=lecture_no+"002";
		}else if(board.getBoard_cd().equals("003")) {////����Ʈ���� Q&A�̸� 003�� ������ ���� �ѱ�
			so_b="003";
			no=lecture_no+"003";
		}
		board.setSo_b(so_b);
		board.setNo(Long.valueOf(no));
		
		boardService.insert(board);
	}


	
      //according to id delete
	@GetMapping(value="/delete/{board_cd}/{no}/{seq_no}")
	public void delete(@PathVariable("board_cd") String board_cd, @PathVariable("no") long no, @PathVariable("seq_no") int seq_no, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
	    int lecture_session=(int)session.getAttribute("lecture_no");
	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lecture_no", lecture_session);
		map.put("board_cd", board_cd);
		map.put("no", no);
		map.put("seq_no", seq_no);
		boardService.delete(map);
		System.out.println(seq_no + "�����Ϸ�");
		boardService.after_delete(map);
	}
	
	//�Խ��� ����(����������)
	@PostMapping(value="/update")
	public void update(@RequestParam Map<String, Object> map,@Param(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
		//lecture_no, board_cd, no, seq_no, title, content, file_nm, save_file_nm
		
		
		
		//lecture_no�� ���ǰ����� �����ͼ� ����
		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		
		
		String board_cd=(String)map.get("board_cd");
		long no=Long.parseLong((String)map.get("no"));
		int seq_no=Integer.parseInt((String)map.get("seq_no"));
		String content=(String)map.get("content");
		String title=(String)map.get("title");
		String originalfileName = null;
		String save_file_nm=null;
		
		//board�� set
		BoardVO board= new BoardVO();
		board.setLecture_no(lecture_session);
		board.setBoard_cd(board_cd);
		board.setNo(no);
		board.setSeq_no(seq_no);
		board.setContent(content);
		board.setTitle(title);
	
		if(file.isEmpty()){ //���ε��� ������ ���� ��
            System.out.println("���Ͼ���");
        }else {
        	System.out.println("file ���� !!");
    		
    		//���� �̸�������(FILE_NM)
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
    		
    		System.out.println("�����̸� : "+originalfileName);
    		System.out.println("���ο� �����̸� : "+save_file_nm);
    		//System.out.println("���ϰ�� : "+fileUrl);
    		
    		//�������� ����
			String delete_filenm=boardService.getById(board).getSave_file_nm();//������ �����̸�
			File delete_file=new File("C:/Temp/"+delete_filenm);//������ ����
			delete_file.delete();//���� ����
        }
		
		board.setFile_nm(originalfileName);
		board.setSave_file_nm(save_file_nm);
		
		boardService.update(board);
	}
	
	//�Խ��� ����(���Ͼ�����)
	@PostMapping(value="/update_nofile")
	public void update_nofile(@RequestParam Map<String, Object> map, HttpServletRequest request) {
		//lecture_no, board_cd, no, seq_no, title, content
		
		//lecture_no�� ���ǰ����� �����ͼ� ����
		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");	
		
		String board_cd=(String)map.get("board_cd");
		long no=Long.parseLong((String)map.get("no"));
		int seq_no=Integer.parseInt((String)map.get("seq_no"));
		String content=(String)map.get("content");
		String title=(String)map.get("title");
		
		//board�� set
		BoardVO board= new BoardVO();
		board.setLecture_no(lecture_session);
		board.setBoard_cd(board_cd);
		board.setNo(no);
		board.setSeq_no(seq_no);
		board.setContent(content);
		board.setTitle(title);
		
		boardService.update_nofile(board);
	}

	
	//�ش� �Խ����� ��ü ����� ������
	//lecture_no, board_cd�ʿ�(����Ʈ���� board_cd���� �ʿ�)
	@GetMapping(value="/list/{board_cd}")
	public List<BoardVO> list(@PathVariable("board_cd") String board_cd, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		int lecture_session=(int)session.getAttribute("lecture_no");
		
		BoardVO board=new BoardVO();
		board.setBoard_cd(board_cd);
		board.setLecture_no(lecture_session);
		
		return boardService.list(board);
	}
	
	

	@PostMapping("/upload")
	public String uploadToLocalFileSystem(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
		//FILE_NM
		String originalfileName = file.getOriginalFilename();
		//SAVE_FILE_NM
		UUID uuid =UUID.randomUUID();
		String save_file_nm=uuid.toString() +"_" +originalfileName;
				


		 
		// ���� �����Ҷ�(������ ��������????)
		// http://localhost:3003/files/download/a.txtó�� ������ �����Ҷ� ���
		/*String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/download/")
				.toUriString();
		*/
		
		
		/*
		//fileDownloadUrl
		String root_path = request.getSession().getServletContext().getRealPath("/");  
		String attach_path = "resources/upload/";
		String fileDownloadUrl=root_path + attach_path;
		*/
		
		String fileDownloadUrl="C:/Temp/";
		System.out.println(fileDownloadUrl);

		File dest = new File(fileDownloadUrl + save_file_nm);
		file.transferTo(dest);
		
		
		return save_file_nm;
		//return ResponseEntity.ok(fileDownloadUri);
	}
	

	@GetMapping(path = "/download/{save_file_nm}")
    public void download_file(@PathVariable("save_file_nm") String save_file_nm, HttpServletRequest request,HttpServletResponse response) throws IOException { 
    	String file_path="C:/Temp/";
    	/*
		String file_path = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/download/")
				.toUriString();
		*/
		
	    String file_name= save_file_nm; //����� �����̸�
	    String fileUrl=file_path+file_name; //����� ���� url
	    
	    File file = new File(fileUrl);
	    if (!file.exists()) {
            return;
        }
	    
	    String origin_file_nm; //���� �����̸�
	    System.out.println(file_name);
	    if((origin_file_nm=boardService.getFileNm(file_name))==null) { //��� ���� ���ϸ��� ������
	    	origin_file_nm=file_name;
	    }
	    System.out.println(origin_file_nm);
	    
	    
	    response.setContentType("application/octer-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + origin_file_nm + "\"");
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
 
            int ncount = 0;
            byte[] bytes = new byte[512];
 
            while ((ncount = fis.read(bytes)) != -1 ) {
                os.write(bytes, 0, ncount);
            }
            fis.close();
            os.close();
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    
    }
	
	
	
	

}
