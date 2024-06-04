

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class regedit
 */
@WebServlet("/regedit")
public class regedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regedit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ñ���  
        request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html; charset=utf-8");  
  
        // ������������  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String addr = request.getParameter("addr");
  
        // �������Ѿ�����������˹�ϣ����  
        // String hashedPassword = passwordHashingMethod(password);  
  
        // ��Ӧ  
        UserDao userDao = new UserDaoImpl();  
  
        // ִ��ע�᷽��
        boolean regedit = userDao.regedit(username, password , sex, birth, addr); // ���������������ע���Ƿ�ɹ�  
  
        if (regedit) {  
            response.getWriter().write("ע��ɹ���");  //��ת����¼����
        } else {  
            response.getWriter().write("�û������ظ������޸ģ�");  
        }  
    }  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
