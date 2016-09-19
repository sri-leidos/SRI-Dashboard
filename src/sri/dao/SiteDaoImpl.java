package sri.dao;

import java.util.List; 

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import sri.dao.MyBatisConnectionFactory;
import sri.data.Site;

public class SiteDaoImpl implements SiteDao{

	@Override
	public List<Site> getAllSites() {
		
		List<Site> sites = null;
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			sites = session.selectList("sri.SiteMapper.getAllSites");
		} finally {
			session.close();
		}
		
		return sites;
	}

	@Override
	public Site getSiteById(Integer siteId) {
		
		Site sites;
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			sites = session.selectOne("sri.SiteMapper.getSiteById", siteId);
		} finally {
			session.close();
		}
		
		return sites;
	}

	@Override
	public List<Site> getAllSitesByStateId(String stateId) {
		List<Site> sites = null;
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			sites = session.selectList("sri.SiteMapper.getAllSitesByStateId", stateId);
		} finally {
			session.close();
		}
		
		return sites;
	}

	@Override
	public List<Site> getAllSitesBySiteType(Integer siteTypeId) {
		List<Site> sites;
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			sites = session.selectList("sri.SiteMapper.getAllSitesBySiteType", siteTypeId);
		} finally {
			session.close();
		}
		
		return sites;
	}

	@Override
	public void insertNewSite(String site) {
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
			
		try {
			session.insert("sri.SiteMapper.insertNewSite", site);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void updateSite(Site site) {
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			session.update("sri.SiteMapper.updateSite", site);
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteSite(Integer site) {
		
		SqlSessionFactory sqlMapper = MyBatisConnectionFactory.getSqlSessionFactory();
		SqlSession session = sqlMapper.openSession(true);
		
		try {
			session.delete("sri.SiteMapper.deleteSite", site);
		} finally {
			session.close();
		}
	}

}
