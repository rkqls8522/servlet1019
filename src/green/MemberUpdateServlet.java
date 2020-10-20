package green;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
//@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������� ���� 
				Connection conn = null;
				Statement  stmt = null;
				ResultSet   rs=null;
				
				try {
					Class.forName(this.getInitParameter("driver"));//����̹� �ε� 
					conn = DriverManager.getConnection(
							this.getInitParameter("url"), //JDBC URL
							this.getInitParameter("username"),	// DBMS ����� ���̵�
							this.getInitParameter("password"));	// DBMS ����� ��ȣ
					stmt =conn.createStatement();
					rs= stmt.executeQuery(
							"select mno,email,mname, cre_date from members "+
								" where mno =" +request.getParameter("no")
							);
					rs.next();	
					
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<html><head><title>ȸ������</title></head>");
					out.println("<body><h1>ȸ������</h1>");
					out.println("<form action='update' method='post'>");
					out.println("��ȣ:<input type='text' name='no' value='" +
							request.getParameter("no") +"' readonly><br>"
							);
					out.println("�̸�:<input type='text' name='name'" +
							" value = '" +rs.getString("mname") + "'><br>"
							);
					out.println("�̸���:<input type='text' name='email'" +
							" value = '" +rs.getString("email") + "'><br>"
							);
					out.println("������:" +rs.getDate("cre_date") + "<br>");
					out.println("<input type='submit' value='����'>");
					out.println("<input type='button' value='���'"+ 
							" onclick='location.href=\"list\'>");
					out.println("</form>");
					out.println("</body></html>");			
	} catch(Exception e) {
		throw new ServletException(e);		
	}finally {
		try {if(rs!=null) rs.close();} catch(Exception e) {}
		try {if(stmt!=null) stmt.close();} catch(Exception e) {}
		try {if(conn!=null) conn.close();} catch(Exception e) {}
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����Ʈ �� ������ ������Ʈ �� ");
	}

}
