package com.addr.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addr.model.FileDTO;
import com.addr.model.SAddrDAO;
import com.addr.model.SAddrDAOImpl;

/**
 * Servlet implementation class FileListController
 */
@WebServlet("/addr/fileList.addr")
public class FileListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileListController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 SAddrDAO dao = SAddrDAOImpl.getInstance();
	    ArrayList<FileDTO> farr =  dao.fileList();
	    request.setAttribute("farr", farr);
	    request.getRequestDispatcher("fileList.jsp").forward(request, response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
