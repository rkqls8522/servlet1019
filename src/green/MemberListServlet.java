package green;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/studydb?serverTimezone=Asia/Seoul",
					"root","1234");
			System.out.println("db connection successfuly!");
			st = conn.createStatement();
			rs = st.executeQuery("select mno,mname, email, cre_date from members order by mno asc");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title> 회원목록 </title></head>");
			out.println("<body><h1>회원목록</h1>");
			while(rs.next()) {
				out.println(
						rs.getInt("mno") + "," +
						rs.getString("mname") + "," +
						rs.getString("email") + "," +
						rs.getDate("cre_date") + "<br>"
				);
			}
			out.println("</body></html>");
		}catch(Exception e) {
			throw new ServletException();
		}finally {
			try {if(rs != null) {rs.close();}} catch(Exception e) {System.out.println(e.getMessage());}
            try {if(st!= null) {st.close();}} catch(Exception e) {System.out.println(e.getMessage());}
            try {if(conn != null) {conn.close();}} catch(Exception e) {System.out.println(e.getMessage());}

		}
	}

}
