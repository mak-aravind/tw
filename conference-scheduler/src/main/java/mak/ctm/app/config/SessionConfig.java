package mak.ctm.app.config;

public class SessionConfig {
	
	private SessionConfig(){
		throw new AssertionError();//Big Bang when instantiated from inside class(locally)
	}
	
	public static final String AFTERNOON_SESSION = "afternoon session";
	public static final String LUNCH_SESSION = "lunch session";
	public static final String MORNING_SESSION = "morning session";
	public static final String NETWORKING_SESSION = "networking session";
	public static final int MORNING_SESSION_MAX_MINUTES_ALLOWED = 210;
	public static final int LUNCH_SESSION_MAX_MINUTES_ALLOWED = 60;
	public static final int AFTERNOON_SESSION_MAX_MINUTES_ALLOWED = 240;
	public static final int NETWORKING_SESSION_MAX_MINUTES_ALLOWED = 60;
	public static final int MORNING_SESSION_START_TIME = 9*60;
	public static final int LUNCH_SESSION_START_TIME = MORNING_SESSION_START_TIME + MORNING_SESSION_MAX_MINUTES_ALLOWED;
	public static final int AFTERNOON_SESSION_START_TIME = LUNCH_SESSION_START_TIME + LUNCH_SESSION_MAX_MINUTES_ALLOWED;
	public static final int NETWORKING_SESSION_MIN_START_TIME = AFTERNOON_SESSION_START_TIME + (3 * 60); // 4 PM.
	
}
