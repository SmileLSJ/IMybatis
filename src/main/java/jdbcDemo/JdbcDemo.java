package jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *  简单回顾之前的jdbc用法
 * @author ZY003
 *
 */
public class JdbcDemo {

	
	public static void main(String[] args) {
		
		
		try {
			//加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			
			//通过驱动管理器获取连接
			Connection conn = DriverManager.
					getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8", "root", "123456");
			
			String sql = "select * from item where id = ?";
			
			PreparedStatement prestate = conn.prepareStatement(sql);
			
			prestate.setInt(1, 1);
			
			ResultSet result = prestate.executeQuery();
			
			while(result.next()) {
				String str = result.getString("id")+":"+result.getString("name");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
