package sri.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FireBirdDBSessionManager {

	private static String resourcePath = "mybatis.xml";
	
	private static SqlSessionFactory sqlMapper; 
	
	public static SqlSessionFactory getSqlSessionFactory(String environment){
		if(sqlMapper == null){
			
			try {
				Reader reader = Resources.getResourceAsReader(resourcePath);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader, environment);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return sqlMapper;
	}
	
	
	
	
}
