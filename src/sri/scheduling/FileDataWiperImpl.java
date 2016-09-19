package sri.scheduling;

import java.io.File;
import java.util.Date;


public class FileDataWiperImpl implements DataWiper {
	
	
	private String rootDirectory;

	public String getRootDirectory() {
		return rootDirectory;
	}

	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}

	@Override
	public void wipeData() {
		File f = new File(rootDirectory);
		File[] files = f.listFiles();
		long now = new Date().getTime();
		//23 hours is the max age of a file.
		long maxAge = 23*60*60*1000;
		for(File theFile:files){
			if(!theFile.isDirectory()){
				long age = now - theFile.lastModified();
				//if the file is older than 23 hours delete it
				if(age > maxAge){
					theFile.delete();
				}
			}
		}

	}

}
