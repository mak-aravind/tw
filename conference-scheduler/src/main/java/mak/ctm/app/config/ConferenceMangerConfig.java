package mak.ctm.app.config;

public class ConferenceMangerConfig {
	private ConferenceMangerConfig(){
		throw new AssertionError();//Big Bang when instantiated from inside class(locally)
	}
	public static final boolean LONG_WAIT=false;
}
