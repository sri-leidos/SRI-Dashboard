package sri.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.data.SiteType;

public class SiteTypeDaoImpl implements SiteTypeDao{

	@Override
	public List<SiteType> getAllSiteType() {
		
		List<SiteType> siteTypes = null;
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			siteTypes = session.selectList("sri.SiteMapper.getAllSiteTypes");
		} finally {
			session.close();
		}
		
		return siteTypes;
	}

}
