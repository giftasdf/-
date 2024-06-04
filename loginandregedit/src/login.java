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
		//设置编码
		request.setCharacterEncoding("utf-8"); //请求编码
		response.setContentType("text/html; charset=utf-8"); //响应编码
		
		//接收请求数据
		String username = request.getParameter("username"); //用户名，"name"是对应登录页面的文本框名称
		String password = request.getParameter("password"); //密码
		
		/*//响应
		response.getWriter().write("用户名为："+name+"，密码为："+pwd);*/
		
		//获取表单中的请求参数
		UserDao userDao = new UserDaoImpl();
		
		//执行登录方法
		User user = userDao.login(username, password);
		if(user != null) {
			response.getWriter().write("登录成功");//回到界面
		}else {
			response.getWriter().write("用户名或密码错误");
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
