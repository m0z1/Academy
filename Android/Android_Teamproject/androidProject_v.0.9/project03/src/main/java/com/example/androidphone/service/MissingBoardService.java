package com.example.androidphone.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.androidphone.model.FindBoard;
import com.example.androidphone.model.MissingBoard;
import com.example.androidphone.repository.MissingBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissingBoardService {
	
	private final MissingBoardRepository missingBoardRepository;
	
	//글 작성
	public MissingBoard insert(MissingBoard missingBoard) {
		return missingBoardRepository.save(missingBoard);
	}
	
	//상세보기
		public MissingBoard view(Long missingId) {
			return missingBoardRepository.findById(missingId).get();
		}
		
		//수정
		@Transactional
		public MissingBoard update(MissingBoard missingBoard) {
			MissingBoard mb = missingBoardRepository.findById(missingBoard.getMissingId()).get();
			mb.setMissingaddr(missingBoard.getMissingaddr());
			mb.setContent(missingBoard.getContent());
			mb.setBreed(missingBoard.getBreed());
			mb.setPetname(missingBoard.getPetname());
			mb.setPetage(missingBoard.getPetage());
			mb.setPetgender(missingBoard.getPetgender());
			mb.setPetcharacter(missingBoard.getPetcharacter());
			return mb;
		}
		
		//삭제
		public void deleteById(Long missingId) {
			missingBoardRepository.deleteById(missingId);
		}
		
		//전체보기
			public List<MissingBoard> missing_list() {
				return missingBoardRepository.findAll();
				
			}
	

}
