package com.person2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.person2.model.Person2;
import com.person2.model.Person2DAOImpl;

@Controller
public class Person2Controller {
	@Autowired
	private Person2DAOImpl dao;
	//추가폼
	 @RequestMapping("person_insert.go")
	 public String person_insert() {
		 return "personForm";
	 }
	 //추가
	 @RequestMapping(value = "person_insert.go", 
			   method =RequestMethod.POST )
	 public String person_insert(Person2 person) {
		 dao.person_insert(person);
		 return "redirect:person_list.go";
	 }
	 //전체보기
	 @RequestMapping("person_list.go")
	 public String person_list(Model model) {
		  List<Person2> arr =  dao.person_list();
		  int count = dao.person_count();
		  model.addAttribute("personlist", arr);
		  model.addAttribute("count", count);
		 return "personList";
	 }
	 //상세보기
	 @RequestMapping("person_view.go")
	 public String person_view(String id,Model model ){
		 Person2 person = dao.person_view(id);
		 model.addAttribute("person", person);
		 return "personView";
	 }
	 //삭제
	 @RequestMapping("person_delete.go")
	 public String person_delete(String id) {
		 dao.person_delete(id);
		 return "redirect:person_list.go";
		 
	 }
	 
	 //수정폼
	 @RequestMapping("person_update.go")
	 public String person_update(String id, Model model) {
		 Person2 person = dao.person_view(id);
		 model.addAttribute("person", person);
		 return "personUpdate";
		 
	 }
	 //수정
	 @RequestMapping(value = "person_update.go", method = RequestMethod.POST)
	 public String person_update(Person2 person) {
		 dao.person_update(person);
		 return "redirect:person_list.go";
		 
	 }
}



