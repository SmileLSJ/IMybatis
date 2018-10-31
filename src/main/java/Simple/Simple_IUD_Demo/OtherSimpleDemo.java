package Simple.Simple_IUD_Demo;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import TemplateUtil.sqlTemplate;
import po.Item;
import po.Plu;

public class OtherSimpleDemo {
	//会话工厂
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;

	
	@Before
	public void initSqlSessionFactory() {
		String config = "SqlMapConfig.xml";
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().
					build(Resources.getResourceAsStream(config));
			sqlSession = sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	@Test
	public void insertItem() {
		Plu plu = new Plu();
		plu.setName("苹果手机");
		plu.setPrice(10000);
		
		int index = sqlSession.insert("InsertPlu", plu);
		sqlSession.commit();
		System.out.println(plu);
	}
	
	
	@Test
	public void deletetById() {
		int index = sqlSession.delete("deletetById",9);
		System.out.println(index);
		sqlSession.commit();
	}
	
	
}
