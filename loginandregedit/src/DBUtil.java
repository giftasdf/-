
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
    //1.获取数据库连接池DataSource对象
    static {
        try {
            //1.创建出Properties对象
            Properties properties = new Properties();
            //2.打开读jdbc.properties文件的输入流（可以是字符流，也可以字节流）
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            //3.通过properties对象的load方法加载输入流--把jdbc.properties中内容读到properties中
            properties.load(is);
            //4.通过DruidDataSourceFactory根据properties创建一个DataSource对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.通用的增删改方法
    public static boolean updateTable(String sql, Object... params){
        Connection conn = null;
        try {
            conn = ds.getConnection();//从数据库连接池中获取Connection
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
                //把数据库连接Connection放回到连接池
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //3.jdbc的封装可以用反射机制来封装,把从数据库中获取的数据封装到一个类的对象里
    public static <T> List<T> queryTable(String sql, Class<T> cls,  Object... params) {
        List<T> list = new ArrayList<T>();
        Connection conn = null;
        try {
            conn = ds.getConnection();//从数据库连接池中获取Connection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (params != null && params.length>0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i+1, params[i]);
                }
            }
            ResultSet resultSet = pstmt.executeQuery();
            //封装数据:列名获得是关键
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int len = metaData.getColumnCount();//得到列的个数
            while(resultSet.next()){
                //获得参数中Class<T> cls对应的实体类的对象
                T objectInstance = cls.newInstance();
                for (int i = 0; i < len; i++) {
                    String column_name = metaData.getColumnName(i+1);
                    Object column_value = resultSet.getObject(column_name);
					System.out.println(column_name+"\t"+column_value);
                    //把属性名和值，与对应的实体类进行关联
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
                    conn.close();//把数据库连接Connection放回到连接池
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
