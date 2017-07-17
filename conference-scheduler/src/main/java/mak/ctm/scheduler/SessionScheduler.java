package mak.ctm.scheduler;

import static mak.ctm.app.config.SessionConfig.AFTERNOON_SESSION;
import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION;
import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION;
import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION;
import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION_MAX_MINUTES_ALLOWED;

import java.util.List;

import mak.ctm.dto.Session;
import mak.ctm.dto.Talk;

public class SessionScheduler implements IScheduler{
	
	private final IPacker sessionPacker;
	
	public SessionScheduler(IPacker sessionPacker) {
		this.sessionPacker = sessionPacker;
	}
	@Override
	public List<Talk> scheduleTalks(final Session session, final List<Talk> talksToAccommodate) {
		if (session.getSessionName().equals(MORNING_SESSION)|| 
			session.getSessionName().equals(AFTERNOON_SESSION))
			sessionPacker.findAnySpaceAndFill(session, talksToAccommodate);
		
		return talksToAccommodate;
	}
	@Override
	public void scheduleSuplementorySession(final Session session) {
		session.put(session.getSessionName().equals(LUNCH_SESSION) ?  
				new Talk(LUNCH_SESSION, LUNCH_SESSION_MAX_MINUTES_ALLOWED):
				new Talk(NETWORKING_SESSION, NETWORKING_SESSION_MAX_MINUTES_ALLOWED));
	}
}
