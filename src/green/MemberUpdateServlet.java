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
		//지역변수 선언 
				Connection conn = null;
				Statement  stmt = null;
				ResultSet   rs=null;
				
				try {
					Class.forName(this.getInitParameter("driver"));//드라이버 로딩 
					conn = DriverManager.getConnection(
							this.getInitParameter("url"), //JDBC URL
							this.getInitParameter("username"),	// DBMS 사용자 아이디
							this.getInitParameter("password"));	// DBMS 사용자 암호
					stmt =conn.createStatement();
					rs= stmt.executeQuery(
							"select mno,email,mname, cre_date from members "+
								" where mno =" +request.getParameter("no")
							);
					rs.next();	
					
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("<html><head><title>회원정보</title></head>");
					out.println("<body><h1>회원정보</h1>");
					out.println("<form action='update' method='post'>");
					out.println("번호:<input type='text' name='no' value='" +
							request.getParameter("no") +"' readonly><br>"
							);
					out.println("이름:<input type='text' name='name'" +
							" value = '" +rs.getString("mname") + "'><br>"
							);
					out.println("이메일:<input type='text' name='email'" +
							" value = '" +rs.getString("email") + "'><br>"
							);
					out.println("가입일:" +rs.getDate("cre_date") + "<br>");
					out.println("<input type='submit' value='저장'>");
					out.println("<input type='button' value='취소'"+ 
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
		System.out.println("포스트 잘 들어오나 업데이트 시 ");
	}

}
