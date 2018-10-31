package TemplateUtil;

import java.io.IOException;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mysql.jdbc.StringUtils;

/**
 * 利用模板模式简化代码
 * @author ZY003
 *
 */

public abstract class sqlTemplate {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static SqlSession sqlSession ;

	public abstract Map<String, Object> setValue();
	
	public void executeSQL() {
		String config = "SqlMapConfig.xml";
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().
					build(Resources.getResourceAsStream(config));
			
			sqlSession = sqlSessionFactory.openSession();
			
			Map<String, Object> params = setValue();
			
			String sqlName = String.valueOf(params.get("sql"));
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession!=null) {
				sqlSession.close();
			}
		}
	}
	
}
