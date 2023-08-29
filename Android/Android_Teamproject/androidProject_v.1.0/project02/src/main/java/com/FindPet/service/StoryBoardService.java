package com.FindPet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.FindPet.model.ImgFile;
import com.FindPet.model.StoryBoard;
import com.FindPet.repository.StoryBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoryBoardService {

	private final StoryBoardRepository storyBoardRepository;
	private final ImgService imgService;

	// 글 작성
	public StoryBoard insert(StoryBoard storyBoard) {
		return storyBoardRepository.save(storyBoard);
	}

	// 상세보기
	public StoryBoard view(Long storyId) {
		return storyBoardRepository.findById(storyId).get();
	}

	// 수정
public StoryBoard update(StoryBoard storyBoard, List<MultipartFile> imgFileList) throws Exception{
		
		StoryBoard storyBoard0 = storyBoardRepository.findById(storyBoard.getStoryId()).get();
			
		storyBoard0.storyBoardUpdate(storyBoard);
		    
	    List<ImgFile> imgFileIds = imgService.imgFindByBoardNum(null, null, storyBoard.getStoryId());
	    
	    int imgCount = imgFileList.size();

	    
	    for(int i=0;i<imgCount;i++){
	    	if(i<imgFileIds.size()) {
	    		imgService.updateImgFile(imgFileIds.get(i).getImgNum(), imgFileList.get(i));
	    	} else {
	    		ImgFile img = new ImgFile();
	            img.setStoryboard(storyBoard0);

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
	    return storyBoard0;
	}

	// 삭제
	public void deleteById(Long storyId) {
		storyBoardRepository.deleteById(storyId);
	}

	// 전체보기
	public List<StoryBoard> story_list() {
		return storyBoardRepository.findAllByOrderByStoryIdDesc();

	}

}
