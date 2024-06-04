import java.sql.Date;
import java.util.List;

public class UserDaoImpl implements UserDao{

	public User login(String username, String password) {
		String sql = "SELECT id,username,password FROM user where username=? AND password = ?";
		//ͨ��������ִ��sql���
		List<User> list=DBUtil.queryTable(sql, User.class,username,password);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public boolean regedit(String username,String password,String sex,String birth,String addr) {
		// ���ȼ���û����Ƿ��Ѵ���  
	    String checkUsernameSql = "SELECT * FROM user WHERE username = ?";
	    List<User> existingUsers = DBUtil.queryTable(checkUsernameSql, User.class, username);  
	    if (existingUsers.size() > 0) {  
	    	System.out.println("�û����Ѵ��ڣ�ע��ʧ��");
	        return false;
	    }else {
	    	String insertSql = "INSERT INTO user(username, password, sex, birth, addr) VALUES(?,?,?,?,?)"; 
	    	boolean rowsAffected = DBUtil.updateTable(insertSql, username, password, sex, birth, addr);  
	    	System.out.println("����ɹ�");
	    	return rowsAffected;
	    }
	}
	
}