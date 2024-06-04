import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		request.setCharacterEncoding("utf-8"); //�������
		response.setContentType("text/html; charset=utf-8"); //��Ӧ����
		
		//������������
		String username = request.getParameter("username"); //�û�����"name"�Ƕ�Ӧ��¼ҳ����ı�������
		String password = request.getParameter("password"); //����
		
		/*//��Ӧ
		response.getWriter().write("�û���Ϊ��"+name+"������Ϊ��"+pwd);*/
		
		//��ȡ���е��������
		UserDao userDao = new UserDaoImpl();
		
		//ִ�е�¼����
		User user = userDao.login(username, password);
		if(user != null) {
			response.getWriter().write("��¼�ɹ�");//�ص�����
		}else {
			response.getWriter().write("�û������������");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
