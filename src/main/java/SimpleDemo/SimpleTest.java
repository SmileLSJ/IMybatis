package SimpleDemo;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import po.Item;

/**
 * 原始的整合mybatis的过程
 * @author ZY003
 *
 */

/**
  	区别1
			#{} ：相当于JDBC SQL语句中的占位符? (PreparedStatement)
			${}  : 相当于JDBC SQL语句中的连接符合 + (Statement)
				区别2
			#{} ： 进行输入映射的时候，会对参数进行类型解析（如果是String类型，那么SQL语句会自动加上’’）
			${}  :进行输入映射的时候，将参数原样输出到SQL语句中
				区别3
			
			#{} ： 如果进行简单类型（String、Date、8种基本类型的包装类）的输入映射时，#{}中参数名称可以任意
			${}  : 如果进行简单类型（String、Date、8种基本类型的包装类）的输入映射时，${}中参数名称必须是value
			
				
			${} :存在SQL注入问题 ，使用OR 1=1 关键字将查询条件忽略
			 
 *
 */
public class SimpleTest {

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
	

	@Test
	public void findItemById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Item item = sqlSession.selectOne("test.findItemById", 1);
		System.out.println(item);
	}
	
	
	/**
	 * 对于使用${}来进行基本类型查询，花括号中的变量名称必须为value,否则报
	 * org.apache.ibatis.exceptions.PersistenceException: 
		### Error querying database.  Cause: org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'id' in 'class java.lang.Integer'
		### Cause: org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'id' in 'class java.lang.Integer'
		Caused by: org.apache.ibatis.reflection.ReflectionException: There is no getter for property named 'id' in 'class java.lang.Integer'
	 */
	//错误
	@Test
	public void findItemBy$Id() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Item item = sqlSession.selectOne("test.findItemBy$Id", 1);
		System.out.println("findItemBy$Id："+item);
	}
	//正确
	@Test
	public void findItemBy$Id_value() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Item item = sqlSession.selectOne("test.findItemBy$Id_value", 1);
		System.out.println("findItemBy$Id_value："+item);
	}
	
	
	
	/**
	 * 对于#{}而言，基本类型是，花括号中的变量名称可以是任意的
	 */
	@Test
	public void findItemBy_hello() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Item item = sqlSession.selectOne("test.findItemBy_hello", 1);
		System.out.println("findItemBy_hello："+item);
	}
	
	
	
	
	
	@Test
	public void findItemBy$Name() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Item item = sqlSession.selectOne("test.findItemBy$Name", "笔记");
		System.out.println("findItemBy$Name："+item);
	}
	
	@Test
	public void findItemByName_hello() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Item item = sqlSession.selectOne("test.findItemByName_hello", "笔记");
		System.out.println("findItemByName_hello："+item);
	}
}
