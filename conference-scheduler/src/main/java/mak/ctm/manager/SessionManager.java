package mak.ctm.manager;

import static mak.ctm.app.config.TrackConfig.LUNCH_SESSION_INDEX;
import static mak.ctm.app.config.TrackConfig.NETWORKING_SESSION_INDEX;

import java.util.List;

import mak.ctm.dto.Session;
import mak.ctm.dto.Talk;
import mak.ctm.dto.factory.ISessionFactory;
import mak.ctm.scheduler.IScheduler;;


public class SessionManager {
	
	private final ISessionFactory sessionFactory;
	private final IScheduler sessionScheduler;
	
	public SessionManager(ISessionFactory sessionFactory, IScheduler sessionScheduler) {
		this.sessionFactory = sessionFactory;
		this.sessionScheduler = sessionScheduler;
	}

	public Session getScheduledSuplementorySession(final int sessionIndex, 
													final Session previouSession) {
		final Session session = sessionFactory.createSession(sessionIndex,
				 previouSession);
		sessionScheduler.scheduleSuplementorySession(session);
		return session;
	}

	public Session getScheduledSession(final int sessionIndex, 
										final Session previouSession, 
										final List<Talk> talksToAccommodate){
		if (sessionIndex == NETWORKING_SESSION_INDEX || sessionIndex == LUNCH_SESSION_INDEX)
			return getScheduledSuplementorySession(sessionIndex,previouSession);
		final Session session = sessionFactory.createSession(sessionIndex, previouSession);
		sessionScheduler.scheduleTalks(session,talksToAccommodate);	
		return session;
	}
}
