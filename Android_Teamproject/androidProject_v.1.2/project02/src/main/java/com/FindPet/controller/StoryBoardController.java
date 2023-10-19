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
import com.FindPet.model.StoryBoard;
import com.FindPet.repository.ImgRepository;
import com.FindPet.service.ImgService;
import com.FindPet.service.MemberService;
import com.FindPet.service.StoryBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storyBoard/*")
public class StoryBoardController {

	@Value("${imgLocation}")
	private String imgLocation;
	private final ImgService imgService;
	private final ImgRepository imgRepository;
	private final MemberService memberService;
	private final StoryBoardService storyBoardService;

	@PostMapping("insert")
	public String insert(@RequestParam(value = "imgFile", required = false) List<MultipartFile> imgFileList,
			StoryBoard storyBoard, String username) {

		System.out.println("StoryBoard insert");
		
		Member member = memberService.findmember(username);
		storyBoard.setMember(memberService.findmember(username.replaceAll("\"", "")));

		StoryBoard storyBoard2 = storyBoardService.insert(storyBoard);
		if (imgFileList != null) {
			for (int i = 0; i < imgFileList.size(); i++) {
				ImgFile img = new ImgFile();
				img.setStoryboard(storyBoard2);

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

	@GetMapping("view")
	public List<ImgFile> view(Long storyId) {
		return imgService.imgFindByBoardNum(null, null, storyId);
	}

	// 상세보기
	@GetMapping("view3")
	public StoryBoard view3(@RequestParam("storyId") Long storyId) {
		return storyBoardService.view(storyId);
	}

	// 수정
		@PostMapping("update")
		public StoryBoard update( 
				@RequestParam(value="imgFile", required = false) List<MultipartFile> imgFileList,
				StoryBoard storyBoard,
				@RequestParam(value="imgIndexList", required = false) List<Integer> imgIndexList) {


	            try {
	            	StoryBoard storyBoard0 = storyBoardService.update(storyBoard, imgFileList, imgIndexList);
					System.out.println("update storyBoard : " + storyBoard0.getStoryId());
					return storyBoard0;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
	            
		}

	// 삭제
	@DeleteMapping("/delete/{storyId}")
	public void deleteById(@PathVariable Long storyId) {
		storyBoardService.deleteById(storyId);
	}

	// 전체보기 (페이지, 검색 x)
	@GetMapping("/list")
	public List<StoryBoard> story_list() {
		return storyBoardService.story_list();
	}
}
