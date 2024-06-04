

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
		// 设置编码  
        request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html; charset=utf-8");  
  
        // 接收请求数据  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        String sex = request.getParameter("sex");
        String birth = request.getParameter("birth");
        String addr = request.getParameter("addr");
  
        // 假设您已经对密码进行了哈希处理  
        // String hashedPassword = passwordHashingMethod(password);  
  
        // 响应  
        UserDao userDao = new UserDaoImpl();  
  
        // 执行注册方法
        boolean regedit = userDao.regedit(username, password , sex, birth, addr); // 假设这个方法返回注册是否成功  
  
        if (regedit) {  
            response.getWriter().write("注册成功！");  //跳转到登录界面
        } else {  
            response.getWriter().write("用户名已重复，请修改！");  
        }  
    }  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
