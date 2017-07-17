package mak.ctm.dto;

import static mak.ctm.app.config.TrackConfig.LUNCH_SESSION_INDEX;
import static mak.ctm.app.config.TrackConfig.MAX_SESSIONS_ALLOWED_PER_TRACK;
import static mak.ctm.app.config.TrackConfig.MORNING_SESSION_INDEX;
import static mak.ctm.app.config.TrackConfig.NETWORKING_SESSION_INDEX;

import java.util.ArrayList;
import java.util.List;

import mak.ctm.manager.SessionManager;

public class Track {
	private final List<Session> sessions = new ArrayList<Session>();
	private final List<Talk> sortedTalks;
	private final SessionManager sessionManager;
	
	
	public Track(List<Talk> sortedTalks, SessionManager sessionManager) {
		this.sortedTalks = sortedTalks;
		this.sessionManager = sessionManager;
	}

	public List<Talk> assignTalksToSession(){
		int sessionIndex = MORNING_SESSION_INDEX;
		Session previouSession = null;
		while(!sortedTalks.isEmpty() && sessionIndex < MAX_SESSIONS_ALLOWED_PER_TRACK){
			final Session currentSession = sessionManager.getScheduledSession(sessionIndex, previouSession, sortedTalks);
			sessions.add(currentSession);
			previouSession = currentSession;
			++sessionIndex;
		}
		fillMissingSuplementarySessions(previouSession);
		return sortedTalks;
	}
	
	public int getNumberOfSessions(){
		return sessions.size();
	}
	
	public String toString(){
		final StringBuilder sessionToDisplay = new StringBuilder();
		for (Session session : sessions) {
			sessionToDisplay.append(session);
		}
		return sessionToDisplay.toString();
	}

	private void fillMissingSuplementarySessions(final Session previouSession) {
		if (sessions.size() == 1){
			final Session lunchSession = sessionManager.getScheduledSuplementorySession(LUNCH_SESSION_INDEX,previouSession);
			final Session networkingSession = sessionManager.getScheduledSuplementorySession(NETWORKING_SESSION_INDEX,lunchSession);
			sessions.add(lunchSession);
			sessions.add(networkingSession);
		}else if (sessions.size() == 3){
			final Session networkingSession = sessionManager.getScheduledSuplementorySession(NETWORKING_SESSION_INDEX,previouSession);
			sessions.add(networkingSession);
		}
	}
}
