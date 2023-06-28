package com.addr.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addr.model.FileDTO;
import com.addr.model.SAddrDAO;
import com.addr.model.SAddrDAOImpl;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileInsertController
 */
@WebServlet("/addr/upload.addr")
public class FileInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String savePath = "upload"; //저장될 파일의 위치(폴더)
		int uploadFileSizeLimit =  5*1024*1024; //최대5MB 로 제한
		String encType="utf-8";
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		MultipartRequest multi = new MultipartRequest(
				    request,   // request 객체
				    uploadFilePath,  //  서버 실제 디렉토리
				    uploadFileSizeLimit,   //최대 업로드 파일크기
				    encType, //인코딩
				    new DefaultFileRenamePolicy() // 동일 이름 존재할 때 새이름 부여
		);
		//업로드한 파일 이름
		String fileName = multi.getFilesystemName("uploadFile");
		if(fileName == null) fileName="";
		SAddrDAO dao = SAddrDAOImpl.getInstance();
		FileDTO f = new FileDTO();
		f.setImage(fileName);
		f.setName(multi.getParameter("name"));
		f.setTitle(multi.getParameter("title"));
		dao.fileInsert(f);
		response.sendRedirect("fileList.addr");
		
	}

}





