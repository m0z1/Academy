package com.team01.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Book;
import com.team01.model.Field;
import com.team01.model.ImgFile;
import com.team01.model.SessionUser;
import com.team01.model.Team;
import com.team01.model.User;
import com.team01.repository.FieldRepository;
import com.team01.repository.ImgRepository;
import com.team01.service.BookService;
import com.team01.service.FieldService;
import com.team01.service.FileService;
import com.team01.service.UserService;

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
	private final HttpSession httpSession;
	private final UserService userService;
	
	
	@GetMapping("field_insert")
	public String field_insert() {
		return "/field/field_insert";
	}
	
	
	@PostMapping("field_insert")
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String field_insert(Field field, 
			@RequestParam("ImgFile") List<MultipartFile> imgFileList,
			Model model) {
		
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		field.setUser(userService.user_view(sessionUser.getEmail()).get());
		if(imgFileList.get(0).isEmpty()){
            model.addAttribute("errorMessage", "첫번째 구장 이미지는 반드시 등록하여야 합니다.");
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
	public String field_view(@PathVariable Long fieldNum, Model model) {
		
		System.out.println(">>> field_view");
		Team team = null;
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		if(sessionUser != null)
			team = userService.user_view(sessionUser.getEmail()).get().getTeam();
		httpSession.setAttribute("team", team);
		
		List<ImgFile> imgList = imgRepository.findByField_FieldNumOrderByImgNumAsc(fieldNum);
		
		Date date = new Date((new Date()).getTime() + (1000 * 60 * 60 * 9));
		SimpleDateFormat format1 = new SimpleDateFormat("MM");
		SimpleDateFormat format2 = new SimpleDateFormat("dd");
		SimpleDateFormat format3 = new SimpleDateFormat("E");
		
		String dArr = format1.format(date);
		String dArr2 = format2.format(date);
		String dArr3 = format3.format(date);
		
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
			@RequestParam("ImgFile") List<MultipartFile> imgFileList,
			Model model) {
		
        try {
            fieldService.field_update(field, imgFileList);
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
