package com.product.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.product.model.Product;
import com.product.model.ProductDAO;
import com.product.model.ProductDAOImpl;

/**
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/product/pinsert")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   //파일업로드
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
		//업로드된 파일이름
		String fileName = multi.getFilesystemName("productImage");
		if(fileName ==null) fileName="";
				
		Product p = new Product();
		p.setCategory(multi.getParameter("category"));
		 p.setCondition(multi.getParameter("condition"));
		 p.setDescription(multi.getParameter("description"));
		 p.setManufacturer(multi.getParameter("manufacturer"));
		 p.setPname(multi.getParameter("name"));
		 p.setUnitPrice(Integer.parseInt(multi.getParameter("unitPrice")));
		 p.setUnitsInStock(Integer.parseInt(multi.getParameter("unitsInStock")));
	     p.setFilename(fileName);
		ProductDAO dao = ProductDAOImpl.getInstance();
		dao.productInsert(p);
		response.sendRedirect("plist");
	}

}








