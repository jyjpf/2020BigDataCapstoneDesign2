package com.dictation.mapper;

import com.dictation.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DownloadDAO {

	/**
	 *
	 * @param hashfilename
	 * @return originalFilename
	 */
	public String getOriginalFilenameByAudio(String hashfilename);

	/**
	 *
	 * @param hashfilename
	 * @return originalFilename
	 */
	public String getOriginalFilenameByBoard(String hashfilename);

}
