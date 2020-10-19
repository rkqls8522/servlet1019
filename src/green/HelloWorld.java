package green;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet{

	//������� ����
	//������ Ÿ�� ServletConfig, �������� config;
	//config�� configuration�� ����
	ServletConfig config;
	
	@Override
	public void destroy() {
		System.out.println("destroyȣ���");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletȣ���");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo()ȣ���");
		return "version=10.; author=";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		//���ø����̼� ����� �ڵ����� ȣ���
		System.out.println("init() �޼��� ȣ�� !!");
		this.config = config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		//init()�޼��� �������� ȣ��Ǵ� �޼���
		//service �޼��忡�� doget,dopost�� ���޵�
		System.out.println("service() ȣ��� ~");
	}

}
