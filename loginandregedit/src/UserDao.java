
public interface UserDao {
	//ʵ�ֵ�¼����
	public User login(String username,String password);
	
	//ʵ��ע�Ṧ��
	public boolean regedit(String username,String password,String sex,String birth ,String addr);
}
