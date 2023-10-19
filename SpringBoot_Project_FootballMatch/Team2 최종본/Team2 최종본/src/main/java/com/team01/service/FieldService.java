package com.team01.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.team01.model.Field;
import com.team01.model.ImgFile;
import com.team01.repository.FieldRepository;
import com.team01.repository.ImgRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FieldService {
	
	@Value("${imgLocation}")
    private String imgLocation;
	private final FieldRepository fieldRepository;
	private final ImgService imgService;
	private final ImgRepository imgRepository;
	
	public void field_insert(Field field, List<MultipartFile> imgFileList) {
		
		fieldRepository.save(field);
		 
        //이미지 등록
        for(int i=0;i<imgFileList.size();i++){
            ImgFile img = new ImgFile();
            img.setField(field);

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
	
    
	public void field_update(Field field, List<MultipartFile> imgFileList) throws Exception{
			
		Field field0 = fieldRepository.findById(field.getFieldNum()).get();
		
	    field0.field_update(field);
	    
	    List<ImgFile> imgFileIds = imgRepository.findByField_FieldNumOrderByImgNumAsc(field.getFieldNum());
	    
	    int imgCount = imgFileList.size();

	    
	    for(int i=0;i<imgCount;i++){
	    	if(i<imgFileIds.size()) {
	    		imgService.updateImgFile(imgFileIds.get(i).getImgNum(), imgFileList.get(i));
	    	} else {
	    		ImgFile img = new ImgFile();
	            img.setField(field);

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
	}
	
	public List<Field> list(){
		
		return fieldRepository.findAll();
		
	}
	
	public Page<Field> listPage(Pageable pageable, String field, String word ){
		Page<Field> flists = fieldRepository.findAll(pageable);
		
		if (field.equals("fieldName")) {
			flists = fieldRepository.findByFieldNameContaining(word, pageable);
		} else if (field.equals("fieldSigungu")) {
			flists = fieldRepository.findByFieldSigunguContaining(word, pageable);
		}
		
		return flists;
		
	}
	
	public List<Field> field_list_num(Long userId) {
		
		return fieldRepository.findByUser_UserIdOrderByFieldNumDesc(userId);
	}
	
	public Field view(Long num) {
		
		Field field = fieldRepository.findById(num).get();
		return field;
	}
	
	public Field field_view(Long fieldNum) {
		
		return fieldRepository.findById(fieldNum).get();
		
	}
}
