package com.example.test.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Model.Phone;
import com.example.test.Repository.PhoneRepository;

@Service
public class PhoneService {
	
	@Autowired
	private  PhoneRepository phoneRepository;
	
	
	
	// 전체 보기
	public List<Phone> list(){
		return phoneRepository.findAll();
	}
	
	//추가 
	
	public Phone insert(Phone phone) {
		return phoneRepository.save(phone);
	}
	
	//삭제
	
	public void delete(Long id) {
		phoneRepository.deleteById(id);
	}
	
	
	//업데이트
	@Transactional
	public Phone update(Long id , Phone phone) {
		Phone p = phoneRepository.findById(id).get();
		p.setName(phone.getName());
		p.setTel(phone.getTel());
		
		return p;
	}


}
