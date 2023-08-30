package com.FindPet.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.FindPet.model.FindBoard;
import com.FindPet.model.ImgFile;
import com.FindPet.repository.FindBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindBoardService {

	private final FindBoardRepository findBoardRepository;
	private final ImgService imgService;

	// 글 작성
	public FindBoard insert(FindBoard findBoard) {
		return findBoardRepository.save(findBoard);
	}

	// 상세보기
	public FindBoard view(Long findId) {
		return findBoardRepository.findById(findId).get();
	}

	// 수정
		public FindBoard update(FindBoard findBoard, 
				List<MultipartFile> imgFileList,
				List<Integer> imgIndexList) throws Exception {

			FindBoard findBoard0 = findBoardRepository.findById(findBoard.getFindId()).get();
			System.out.println("service");

			findBoard0.findBoardUpdate(findBoard);

			List<ImgFile> imgFileIds = imgService.imgFindByBoardNum(findBoard.getFindId(), null, null);

			if(imgFileList!=null) {
				int imgCount = imgFileList.size();
		
				for (int i = 0; i < imgCount; i++) {
					if (imgIndexList.get(i) < imgFileIds.size()) {
						// imgIndexList.get(i)
						imgService.updateImgFile(imgFileIds.get(imgIndexList.get(i)).getImgNum(), imgFileList.get(i));
						
					} else {
						ImgFile img = new ImgFile();
						img.setImgIndex(imgIndexList.get(i));
						img.setFindboard(findBoard0);
		
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
			} else {
				
			}
			return findBoard0;
		}

	// 삭제
	public void deleteById(Long findId) {
		findBoardRepository.deleteById(findId);
	}

	// 전체보기
	public List<FindBoard> Find_list() {
		 return findBoardRepository.findAllByOrderByFindIdDesc();
		}
	
	//강아지 태그
	public List<FindBoard> dog_find_list(String petCategory) {

		List<FindBoard> dog_lists = findBoardRepository.findByPetcategoryOrderByFindIdDesc(petCategory);

		return dog_lists;

	}
	
	//고양이 태그
	public List<FindBoard> cat_find_list(String petCategory) {
		List<FindBoard> cat_lists = findBoardRepository.findByPetcategoryOrderByFindIdDesc(petCategory);

		return cat_lists;
	}
	
	//기타 태그
	public List<FindBoard> Etc_find_list(String petCategory) {
		List<FindBoard> etc_lists = findBoardRepository.findByPetcategoryOrderByFindIdDesc(petCategory);

		return etc_lists;
	}

	public List<FindBoard> all_find(String word) {
		List<FindBoard> allfind = new ArrayList<>();
		allfind.addAll(findBoardRepository.findByBreedContaining(word));
		allfind.addAll(findBoardRepository.findByContentContaining(word));
		allfind.addAll(findBoardRepository.findByFindaddrContaining(word));
		allfind.addAll(findBoardRepository.findByPetageContaining(word));
		allfind.addAll(findBoardRepository.findByPetcharacterContaining(word));
		allfind.addAll(findBoardRepository.findByPetgenderContaining(word));
		allfind.addAll(findBoardRepository.findByPetnameContaining(word));

		return allfind;
		/*
		 * -----------------------------------------------------------------------------
		 */

	}

}
