package anime_list.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detailmodal")
public class DetailModalServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
		
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();


        System.out.println("항목별 개별 조회 요청!");

        System.out.println(request.getParameter("modal"));

    }
}


// package controller;

// import java.io.IOException;
// import java.io.PrintWriter;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// /**
//  * Servlet implementation class CalculatorServlet
//  */
// @WebServlet("/calculate")
// public class CalculatorServlet extends HttpServlet {
// 	private static final long serialVersionUID = 1L;
       
//     /**
//      * @see HttpServlet#HttpServlet()
//      */
//     public CalculatorServlet() {
//         super();
//         // TODO Auto-generated constructor stub
//     }

// 	/**
// 	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
// 	 */
// 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
// 		request.setCharacterEncoding("utf-8");
		
// 		System.out.println("Calculate 요청");
		
// 		System.out.println(request.getParameter("val1"));
// 		System.out.println(request.getParameter("calSign"));
// 		System.out.println(request.getParameter("val2"));
		
// 		int val1 = Integer.parseInt(request.getParameter("val1"));
// 		int val2 = Integer.parseInt(request.getParameter("val2"));
// 		String sign = request.getParameter("calSign");
// 		String ans = null;
		
// 		switch(sign) {
// 			case "plus":
// 				ans = Integer.toString(val1+val2);

// 				break;
// 			case "minus":
// 				ans = Integer.toString(val1-val2);
// 				break;
// 			case "times":
// 				ans = Integer.toString(val1*val2);
// 				break;
// 			case "divide":
// 				if(val2 == 0) {
// 					ans = "0으로 나눌 수 없습니다.";
// 				} else {
// 					ans = Integer.toString(val1/val2);
// 				}
// 				break;
// 			default:
// 				break;
// 		}
		
// 		response.setContentType("text/html;charset=utf-8");
		
// 		PrintWriter pw = response.getWriter();
// 		pw.println("계산 결과 : "+ans);
// 		pw.close();
	
// 	}

// 	/**
// 	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
// 	 */
// 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 		// TODO Auto-generated method stub
// 		doGet(request, response);
// 	}

// }
