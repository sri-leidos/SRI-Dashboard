package sri.scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DBWipeJob extends QuartzJobBean{

	private DataWiper wiper;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Starting Wipe Job");
		wiper.wipeData();
	}
	
	

	public DataWiper getWiper() {
		return wiper;
	}

	public void setWiper(DataWiper wiper) {
		this.wiper = wiper;
	}
	
	
}
