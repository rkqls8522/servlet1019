package green;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/calc")
public class Calculator2 extends HttpServlet {
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("a = " + a + ", " + "b = " + b + "의 계산 결과 입니다.");
		writer.println("a+b = " + (a+b));
		writer.println("a-b = " + (a-b));
		writer.println("a*b = " + (a*b));
		writer.println("a/b = " + ((float)a/(float)b));
		writer.println("a%b = " + (a%b));
		
	}

}
