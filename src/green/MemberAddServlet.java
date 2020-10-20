package green;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>ȸ�����</title></head>");
		out.println("<body><h1> ȸ�����</h1>");
		out.println("<form action='add' method='post'>");
		out.println("�̸�:<input type='text' name ='name'><br>");
		out.println("�̸��� :<input type='text' name ='email'><br>");
		out.println("��ȣ:<input type='password' name ='password'><br>");
		out.println("<input type='submit' value='�߰�'>");
		out.println("<input type='reset' value='���'>");
		out.println("</form>");
		out.println("</body></html");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =null;
		PreparedStatement stmt =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//����̹� �ε� 
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb?", //JDBC URL
					"root",	// DBMS ����� ���̵�
					"1234");	// DBMS ����� ��ȣ
			System.out.println("DB ���� ���� " +conn);
			stmt =conn.prepareStatement("insert into members (email, pwd, mname, cre_date, mod_date) values(?,?,?, now(), now())");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("name"));
			stmt.executeUpdate();
			//�����̷�Ʈ�� �̿��� ��������
			response.sendRedirect("list"); //��� ����� �̵��� 
			
			
			
	} catch(Exception e) {
		throw new ServletException();
	}finally {
		try {
			if (stmt!=null) stmt.close();
		}catch(Exception e) {}
		try {
			if (conn!=null) conn.close();
		}catch(Exception e) {}
	}
	}
}
