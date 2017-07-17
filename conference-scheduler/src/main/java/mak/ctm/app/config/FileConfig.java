package mak.ctm.app.config;

public class FileConfig {
	private FileConfig(){
		throw new AssertionError();//Big Bang when instantiated from inside class(locally)
	}
	
	public static final String VALID_DATA_FILE_NAME="full.data";
}
