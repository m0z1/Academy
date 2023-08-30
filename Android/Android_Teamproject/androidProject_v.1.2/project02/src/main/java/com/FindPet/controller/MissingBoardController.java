package com.FindPet.controller;

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

import com.FindPet.model.ImgFile;
import com.FindPet.model.Member;
import com.FindPet.model.MissingBoard;
import com.FindPet.repository.ImgRepository;
import com.FindPet.service.ImgService;
import com.FindPet.service.MemberService;
import com.FindPet.service.MissingBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missingBoard/*")
public class MissingBoardController {

	@Value("${imgLocation}")
	private String imgLocation;
	private final ImgService imgService;
	private final ImgRepository imgRepository;
	private final MemberService memberService;
	private final MissingBoardService missingBoardService;

	// 글쓰기
	@PostMapping("insert")
	public String insert(@RequestParam(value = "imgFile", required = false) List<MultipartFile> imgFileList,
			MissingBoard missingBoard, String username) {

		System.out.println("insert");
		
		missingBoard.setMember(memberService.findmember(username.replaceAll("\"", "")));

		MissingBoard missingBoard2 = missingBoardService.insert(missingBoard);
		if (imgFileList != null) {
			for (int i = 0; i < imgFileList.size(); i++) {
				ImgFile img = new ImgFile();
				img.setMissingboard(missingBoard2);

				if (i == 0)
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

	
	/*
	 * @GetMapping("view") public List<ImgFile> view(Long missingId) {
	 * 
	 * return imgService.imgFindByBoardNum(null, missingId, null); }
	 */

	//상세보기
	@GetMapping("view2")
	public MissingBoard view2(@RequestParam("missingId") Long missingId) {

		return missingBoardService.view(missingId);
	}

	// 수정
		@PostMapping("update")
		public MissingBoard update( 
				@RequestParam(value="imgFile", required = false) List<MultipartFile> imgFileList,
				MissingBoard missingBoard,
				@RequestParam(value="imgIndexList", required = false) List<Integer> imgIndexList) {


	            try {
	            	MissingBoard missingBoard0 = missingBoardService.update(missingBoard, imgFileList, imgIndexList);
					System.out.println("update missingBoard : " + missingBoard0.getMissingId());
					return missingBoard0;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	            
		}

	// 삭제
	@DeleteMapping("/delete/{missingId}")
	public void deleteById(@PathVariable Long missingId) {
		missingBoardService.deleteById(missingId);
	}

	// 전체보기 (페이지, 검색 x)
	@GetMapping("/list")
	public List<MissingBoard> missing_list() {
		return missingBoardService.Missing_list();
	}
	
	@GetMapping("findDog/{petcategory}")
	public List<MissingBoard> findDog(@PathVariable String petcategory) {

		return missingBoardService.dog_find_list(petcategory);

	}

	@GetMapping("findCat/{petcategory}")
	public List<MissingBoard> findCat(@PathVariable String petcategory) {
		return missingBoardService.cat_find_list(petcategory);
	}

	@GetMapping("findEtc/{petcategory}")
	public List<MissingBoard> findEtc(@PathVariable String petcategory) {
		return missingBoardService.Etc_find_list(petcategory);
	}

	@GetMapping("findAll/{word}")
	public List<MissingBoard> findall(@PathVariable String word) {

		return missingBoardService.all_find(word);
	}

}
