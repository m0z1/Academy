package com.myspring.app07;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	@GetMapping("uploadFile")
	public void uploadFile() {}
	
	@PostMapping("fileAction")
	public String upload(MultipartFile[] uploads) {
		String uploadFolder ="D:\\____BIG13_JUNG\\__LECTURE\\SpringWork\\SprinigProject07_Board\\src\\main\\webapp\\resources";
		for (MultipartFile  multipartfile  :uploads ) {
			//파일 이름 중복 피하기 위해 이름 수정
			UUID uuid = UUID.randomUUID();
			String uploadFileName = uuid.toString()+"_"+multipartfile.getOriginalFilename();
			File saveFile = new File(uploadFolder, uploadFileName);
			try {
				multipartfile.transferTo(saveFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
		}
		return "fileResult";
	}

}
