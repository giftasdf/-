
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {


    private static DataSource ds = null;
    //1.��ȡ���ݿ����ӳ�DataSource����
    static {
        try {
            //1.������Properties����
            Properties properties = new Properties();
            //2.�򿪶�jdbc.properties�ļ������������������ַ�����Ҳ�����ֽ�����
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            //3.ͨ��properties�����load��������������--��jdbc.properties�����ݶ���properties��
            properties.load(is);
            //4.ͨ��DruidDataSourceFactory����properties����һ��DataSource����
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.ͨ�õ���ɾ�ķ���
    public static boolean updateTable(String sql, Object... params){
        Connection conn = null;
        try {
            conn = ds.getConnection();//�����ݿ����ӳ��л�ȡConnection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i+1, params[i]);
            }
            int number = pstmt.executeUpdate();
            if(number != 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                //�����ݿ�����Connection�Żص����ӳ�
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //3.jdbc�ķ�װ�����÷����������װ,�Ѵ����ݿ��л�ȡ�����ݷ�װ��һ����Ķ�����
    public static <T> List<T> queryTable(String sql, Class<T> cls,  Object... params) {
        List<T> list = new ArrayList<T>();
        Connection conn = null;
        try {
            conn = ds.getConnection();//�����ݿ����ӳ��л�ȡConnection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (params != null && params.length>0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1, params[i]);
                }
            }
            ResultSet resultSet = pstmt.executeQuery();
            //��װ����:��������ǹؼ�
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int len = metaData.getColumnCount();//�õ��еĸ���
            while(resultSet.next()){
                //��ò�����Class<T> cls��Ӧ��ʵ����Ķ���
                T objectInstance = cls.newInstance();
                for (int i = 0; i < len; i++) {
                    String column_name = metaData.getColumnName(i+1);
                    Object column_value = resultSet.getObject(column_name);
					System.out.println(column_name+"\t"+column_value);
                    //����������ֵ�����Ӧ��ʵ������й���
                    Field field = cls.getDeclaredField(column_name);
                    field.setAccessible(true);
                    field.set(objectInstance, column_value);
                }
                list.add(objectInstance);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(conn != null){
                    conn.close();//�����ݿ�����Connection�Żص����ӳ�
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
