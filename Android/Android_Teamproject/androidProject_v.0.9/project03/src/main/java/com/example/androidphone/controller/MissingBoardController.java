package com.example.androidphone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.androidphone.model.ImgFile;
import com.example.androidphone.model.MissingBoard;
import com.example.androidphone.repository.ImgRepository;
import com.example.androidphone.service.ImgService;
import com.example.androidphone.service.MissingBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missingBoard/*")
public class MissingBoardController {
	
	@Value("${imgLocation}")
    private String imgLocation;
	private final ImgService imgService;
	private final ImgRepository imgRepository;
	
	private final MissingBoardService missingBoardService;
	
	
	//글쓰기
	@PostMapping("insert")
	public String insert(@RequestParam(value="imgFile", required = false) List<MultipartFile> imgFileList,
			MissingBoard missingBoard) {
		
		System.out.println("insert");
		
		MissingBoard missingBoard2 = missingBoardService.insert(missingBoard);
		if(imgFileList!=null) {
			for(int i=0;i<imgFileList.size();i++){
	            ImgFile img = new ImgFile();
	            img.setMissingboard(missingBoard2);
	
	            if(i == 0)
	            	img.setMainImg("Y");
	            else
	            	img.setMainImg("N");
	
	            try {
					imgService.saveImgFile(img, imgFileList.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
		
		return "success";
	}
	
	@GetMapping("view")
	public List<ImgFile> view(Long missingId){
	
		return imgService.imgFindByBoardNum(null, missingId, null);
	}
	
	@GetMapping("view2")
	public MissingBoard view2(@RequestParam("missingId") Long missingId){
		
		return missingBoardService.view(missingId);
	}
		
		//수정
		@PostMapping("/update")
		public MissingBoard update(@RequestBody MissingBoard missingBoard) {
			return missingBoardService.update(missingBoard);
		}
		
		//삭제
		@DeleteMapping("/delete/{missingId}")
		public void deleteById(@PathVariable Long missingId) {
			missingBoardService.deleteById(missingId);
		}
		
		//전체보기 (페이지, 검색 x)
		@GetMapping("/list")
		public List<MissingBoard> missing_list(){
			return missingBoardService.missing_list();
		}
	

}
