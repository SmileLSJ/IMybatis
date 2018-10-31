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

	
	//�Ự����
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
	
	
	//����namespace����ȫ·��һ�µ����
	@Test
	public void testResourceMapper() {
		SqlSession session = sqlSessionFactory.openSession();
		Item_mapper mapper = session.getMapper(Item_mapper.class);
		Item item = mapper.findItemById(1);
		System.out.println(this.getClass().getPackage().getName()+
				".testResourceMapper��"+item);
	}
}
