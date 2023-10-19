package com.team01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.team01.model.ImgFile;
import com.team01.repository.ImgRepository;

import javax.persistence.EntityNotFoundException;

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
            imgUrl = "/images/field/" + imgName;
            
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
            String imgUrl = "/images/field/" + imgName;
            
            savedFieldImg.updateImgFile(oriImgName, imgName, imgUrl);
        }
    }

}