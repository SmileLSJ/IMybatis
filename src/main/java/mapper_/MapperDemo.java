package mapper_;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import po.Item;

public class MapperDemo {

	
	//会话工厂
	private SqlSessionFactory sqlSessionFactory;

	
	@Before
	public void initSqlSessionFactory() {
		String config = "SqlMapConfig.xml";
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder().
					build(Resources.getResourceAsStream(config));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//测试namespace与类全路径一致的情况
	@Test
	public void testResourceMapper() {
		SqlSession session = sqlSessionFactory.openSession();
		Item_mapper mapper = session.getMapper(Item_mapper.class);
		Item item = mapper.findItemById(1);
		System.out.println(this.getClass().getPackage().getName()+
				".testResourceMapper："+item);
	}
}
