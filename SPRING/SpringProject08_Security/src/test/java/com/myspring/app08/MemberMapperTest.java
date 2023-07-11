package com.myspring.app08;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myspring.dto.MemberDTO;
import com.myspring.mapper.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberMapperTest {
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
	  MemberDTO member = 	mapper.read("admin90");
	  System.out.println(member);
		
	}

}





