
public interface UserDao {
	//实现登录功能
	public User login(String username,String password);
	
	//实现注册功能
	public boolean regedit(String username,String password,String sex,String birth ,String addr);
}
