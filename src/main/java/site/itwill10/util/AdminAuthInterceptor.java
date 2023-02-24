package site.itwill10.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import site.itwill10.dto.Userinfo;

//���ͼ��� Ŭ������ HandlerInterceptorAdapter Ŭ������ ��ӹ޾� �ۼ�
// => Bean Configuration File���� Spring Bean���� ����Ͽ� ���

//������ ���� ���� ó���� ���� ���ͼ��� Ŭ����
// => ��α��� ������̰ų� �����ڰ� �ƴ� ��� ���� �������� �̵� ó��
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {
	//preHandle() : ��û ó�� �޼ҵ� ȣ�� �� ����� ������ �ۼ��ϴ� �޼ҵ�
	// => ���ѿ� ���� ������ �����ϱ� ���� ���
	// => false ��ȯ : ��û ó�� �޼ҵ� ��ȣ��, true : ��û ó�� �޼ҵ� ȣ��
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		if(loginUserinfo==null || loginUserinfo.getStatus()!=9) {
			//request.getRequestDispatcher("userinfo/user_error.jsp").forward(request, response);
			//return false;
			
			throw new Exception();
		}
		return true;
	}
	
	//postHandle() : ��û ó�� �޼ҵ� ȣ�� �� ����� ������ �ۼ��ϴ� �޼ҵ�
	// => ��û ó�� �޼ҵ忡�� ���ܰ� �߻��� ��� ��ȣ��
	// => ��û ó�� �޼ҵ��� ��ȯ���� ������ ��� ���
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	//afterCompletion() : ��û ó�� �޼ҵ� ȣ�� �� ����� ������ �ۼ��ϴ� �޼ҵ�
	// => ��û ó�� �޼ҵ忡�� ���ܰ� �߻��� ��� ȣ��
	// => ���� ó���� ��� ���
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}