package com.example.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.Model.Phone;
import com.example.test.Service.PhoneService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PhoneController {

	@Autowired
	private PhoneService phoneService;
	
	// 전체보기
	@GetMapping("/list")
	public List<Phone> list(){
		return phoneService.list();
	}
	
	//추가 
	@PostMapping("/insert")
	public Phone insert(@RequestBody Phone phone) {
		return phoneService.insert(phone);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		phoneService.delete(id);
	}
	
	@PutMapping("/update/{id}")
	public Phone update(@PathVariable Long id, @RequestBody Phone phone) {
		return phoneService.update(id, phone);
	}
	
}
