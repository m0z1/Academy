package com.FindPet.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.FindPet.model.ImgFile;
import com.FindPet.model.MissingBoard;
import com.FindPet.repository.MissingBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissingBoardService {

	private final MissingBoardRepository missingBoardRepository;
	private final ImgService imgService;

	// 글 작성
	public MissingBoard insert(MissingBoard missingBoard) {
		return missingBoardRepository.save(missingBoard);
	}

	// 상세보기
	public MissingBoard view(Long missingId) {
		return missingBoardRepository.findById(missingId).get();
	}

	// 수정
	public MissingBoard update(MissingBoard missingBoard, List<MultipartFile> imgFileList) throws Exception {

		MissingBoard missingBoard0 = missingBoardRepository.findById(missingBoard.getMissingId()).get();

		missingBoard0.missingBoardUpdate(missingBoard);

		List<ImgFile> imgFileIds = imgService.imgFindByBoardNum(null, missingBoard.getMissingId(), null);

		if (imgFileList != null) {
			int imgCount = imgFileList.size();

			for (int i = 0; i < imgCount; i++) {
				if (i < imgFileIds.size()) {
					imgService.updateImgFile(imgFileIds.get(i).getImgNum(), imgFileList.get(i));
				} else {
					ImgFile img = new ImgFile();
					img.setMissingboard(missingBoard0);

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
		return missingBoard0;
	}

	// 삭제
	public void deleteById(Long missingId) {
		missingBoardRepository.deleteById(missingId);
	}

	// 전체보기
	public List<MissingBoard> Missing_list() {
		List<MissingBoard> missing_list = missingBoardRepository.findAllByOrderByMissingIdDesc();
		return missing_list;
	}

	// 강아지 태그
	public List<MissingBoard> dog_find_list(String petCategory) {

		List<MissingBoard> dog_lists = missingBoardRepository.findByPetcategoryOrderByMissingIdDesc(petCategory);

		return dog_lists;

	}

	// 고양이 태그
	public List<MissingBoard> cat_find_list(String petCategory) {
		List<MissingBoard> cat_lists = missingBoardRepository.findByPetcategoryOrderByMissingIdDesc(petCategory);

		return cat_lists;
	}

	// 기타태그
	public List<MissingBoard> Etc_find_list(String petCategory) {
		List<MissingBoard> etc_lists = missingBoardRepository.findByPetcategoryOrderByMissingIdDesc(petCategory);

		return etc_lists;
	}

	public List<MissingBoard> all_find(String word) {
		List<MissingBoard> allfind = new ArrayList<>();
		allfind.addAll(missingBoardRepository.findByBreedContaining(word));
		allfind.addAll(missingBoardRepository.findByContentContaining(word));
		allfind.addAll(missingBoardRepository.findByMissingaddrContaining(word));
		allfind.addAll(missingBoardRepository.findByPetageContaining(word));
		allfind.addAll(missingBoardRepository.findByPetcharacterContaining(word));
		allfind.addAll(missingBoardRepository.findByPetgenderContaining(word));
		allfind.addAll(missingBoardRepository.findByPetnameContaining(word));

		return allfind;
	}
}
