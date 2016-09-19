package sri.data;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class SriPojo {

	@Override 
	public String toString(){
	  ReflectionToStringBuilder rsb = new ReflectionToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE);
	  rsb.setAppendStatics(false);
	  rsb.setAppendTransients(true);
	  rsb.setUpToClass(this.getClass());
	  rsb.setExcludeFieldNames(null);
	  return rsb.toString();
	}
}
