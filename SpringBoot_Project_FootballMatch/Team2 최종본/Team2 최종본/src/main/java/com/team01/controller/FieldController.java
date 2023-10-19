package com.team01.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Book;
import com.team01.model.Field;
import com.team01.model.ImgFile;
import com.team01.model.Team;
import com.team01.repository.FieldRepository;
import com.team01.repository.ImgRepository;
import com.team01.service.BookService;
import com.team01.service.FieldService;
import com.team01.service.FileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/field/*")
public class FieldController {
	
	@Value("${imgLocation}")
    private String imgLocation;
	private final FieldService fieldService;
	private final FileService fileService;
	private final ImgRepository imgRepository;
	private final FieldRepository fieldRepository;
	private final BookService bookService;

	
	
	@GetMapping("field_insert")
	public String field_insert() {
		return "/field/field_insert";
	}
	
	
	@PostMapping("field_insert")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String field_insert(Field field, 
			@AuthenticationPrincipal PrincipalUser principal,
			@RequestParam("ImgFile") List<MultipartFile> imgFileList,
			Model model) {
		field.setUser(principal.getUser());
		if(imgFileList.get(0).isEmpty()){
            model.addAttribute("errorMessage", "첫번째 구장 이미지는 필수 입력 값 입니다.");
            return "field/field_insert";
        }

        try {
            fieldService.field_insert(field, imgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "구장 등록 중 에러가 발생하였습니다.");
            return "field/field_insert";
        }
		
		
		return "redirect:/";
	}
	
	
	@GetMapping("field_view/{fieldNum}")
	public String field_view(@PathVariable Long fieldNum, Model model,@AuthenticationPrincipal PrincipalUser principal) {
		List<ImgFile> imgList = imgRepository.findByField_FieldNumOrderByImgNumAsc(fieldNum);
		
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("MM");
		SimpleDateFormat format2 = new SimpleDateFormat("dd");
		SimpleDateFormat format3 = new SimpleDateFormat("E");
		List<String> dArr = new ArrayList<>();
		List<String> dArr2 = new ArrayList<>();
		List<String> dArr3 = new ArrayList<>();
		
		System.out.println(date);
		
		for(int i = 0 ; i < 15 ; i++) {
			Date date2 = new Date(date.getTime()+60*60*24*1000*i);
			dArr.add(format1.format(date2));
			dArr2.add(format2.format(date2));
			dArr3.add(format3.format(date2));
		}
		
		List<Book> bookList = new ArrayList<>();
		bookList = bookService.bookFieldList(fieldNum);
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String bookDate= format.format(date);
		
		model.addAttribute("dArr", dArr);
		model.addAttribute("dArr2", dArr2);
		model.addAttribute("dArr3", dArr3);
		model.addAttribute("imgList", imgList);
		model.addAttribute("field", fieldService.field_view(fieldNum));
		model.addAttribute("bookList", bookList);
		model.addAttribute("selectDate", bookDate);
		
		return "/field/field_view";
	}
	
	@GetMapping("field_update/{fieldNum}")
	public String field_update(@PathVariable Long fieldNum, Model model) {
		
		model.addAttribute("field",fieldService.field_view(fieldNum));
		return "/field/field_update";
	}
	
	@PostMapping("field_update")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String field_update(Field field, 
			@AuthenticationPrincipal PrincipalUser principal,
			@RequestParam("ImgFile") List<MultipartFile> imgFileList,
			Model model) {
		field.setUser(principal.getUser());

        try {
            fieldService.field_update(field, imgFileList);
            System.out.println("업데이트 ㄱㄱ");
        } catch (Exception e){
            model.addAttribute("errorMessage", "구장정보 수정 중 에러가 발생하였습니다.");
            return "field/field_update";
        }
		
		
		return "redirect:/field/field_view/" + Long.toString(field.getFieldNum());
	}
	
	@GetMapping("field_delete/{fieldNum}")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String field_delete(@PathVariable Long fieldNum ) throws Exception{
		List<ImgFile> imgList = imgRepository.findByField_FieldNumOrderByImgNumAsc(fieldNum);
        for (ImgFile imgFile : imgList) {
        	fileService.deleteFile(imgLocation+"/"+
                        imgFile.getImgSaveName());
        	imgRepository.delete(imgFile);
        }
        fieldRepository.delete(fieldService.field_view(fieldNum));
		
		
		return "redirect:/member/manager";
	}
}
