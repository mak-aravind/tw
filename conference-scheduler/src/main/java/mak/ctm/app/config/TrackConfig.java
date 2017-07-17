package mak.ctm.app.config;

public class TrackConfig {
	private TrackConfig(){
		throw new AssertionError();//Big Bang when instantiated from inside class(locally)
	}
	
	public static final int MORNING_SESSION_INDEX = 0;
	public static final int LUNCH_SESSION_INDEX = 1;
	public static final int AFTERNOON_SESSION_INDEX = 2;
	public static final int NETWORKING_SESSION_INDEX = 3;
	public static final int MAX_SESSIONS_ALLOWED_PER_TRACK=4;
}
