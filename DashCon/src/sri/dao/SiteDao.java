package sri.dao;

import java.util.List;

import sri.data.Site;

public interface SiteDao {
	
	public List<Site> getAllSites();

	public Site getSiteById(Integer siteId);
	
	public List<Site> getAllSitesByStateId (String StateId);
	
	public List<Site> getAllSitesBySiteType (Integer siteTypeId);
	
	public void insertNewSite (String site);
	
	public void updateSite(Site site);
	
	public void deleteSite(Integer siteId);

}
