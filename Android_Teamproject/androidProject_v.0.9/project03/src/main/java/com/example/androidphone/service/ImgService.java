package com.example.androidphone.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.androidphone.model.ImgFile;
import com.example.androidphone.repository.ImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ImgService {

    @Value("${imgLocation}")
    private String imgLocation;

    private final ImgRepository imgRepository;

    private final FileService fileService;

    public void saveImgFile(ImgFile imgFile, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(oriImgName != null && !oriImgName.isEmpty()){
            imgName = fileService.uploadFile(imgLocation, oriImgName,
                    itemImgFile.getBytes());
            imgUrl = "/images/Project02/" + imgName;
            
            //구장 이미지 정보 저장
            imgFile.updateImgFile(oriImgName, imgName, imgUrl);
            imgRepository.save(imgFile);
        }

       
    }

    public void updateImgFile(Long imgNum, MultipartFile imgFile) throws Exception{
        if(!imgFile.isEmpty()){
        	ImgFile savedFieldImg = imgRepository.findById(imgNum)
        			.orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!savedFieldImg.getImgSaveName().isEmpty()) {
                fileService.deleteFile(imgLocation+"/"+
                        savedFieldImg.getImgSaveName());
            }
            String oriImgName = imgFile.getOriginalFilename();
            
            String imgName = fileService.uploadFile(imgLocation, oriImgName, imgFile.getBytes());
            String imgUrl = "/images/Project02/" + imgName;
            
            savedFieldImg.updateImgFile(oriImgName, imgName, imgUrl);
        }
    }
    
    public List<ImgFile> imgFindByBoardNum(Long findId, Long missingId, Long storyId) {
    	System.out.println(findId);
    	if(findId!=null) {
    		return imgRepository.findByFindboard_findIdOrderByImgNumAsc(findId);
    	} else if(missingId!=null) {
    		return imgRepository.findByMissingboard_missingIdOrderByImgNumAsc(missingId);
    	} else if(storyId!=null) {
    		return imgRepository.findByStoryboard_storyIdOrderByImgNumAsc(storyId);
    	} else
    		return null;
    	
    }
}
