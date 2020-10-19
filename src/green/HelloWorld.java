package green;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet{

	//멤버변수 선언
	//데이터 타입 ServletConfig, 참조변수 config;
	//config는 configuration의 약자
	ServletConfig config;
	
	@Override
	public void destroy() {
		System.out.println("destroy호출됨");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServlet호출됨");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo()호출됨");
		return "version=10.; author=";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		//어플리케이션 실행시 자동으로 호출됨
		System.out.println("init() 메서도 호출 !!");
		this.config = config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		//init()메서드 다음으로 호출되는 메서드
		//service 메서드에서 doget,dopost로 전달됨
		System.out.println("service() 호출됨 ~");
	}

}
