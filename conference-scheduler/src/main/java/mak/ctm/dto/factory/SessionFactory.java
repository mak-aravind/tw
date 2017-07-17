package mak.ctm.dto.factory;

import static mak.ctm.app.config.TrackConfig.AFTERNOON_SESSION_INDEX;
import static mak.ctm.app.config.TrackConfig.LUNCH_SESSION_INDEX;
import static mak.ctm.app.config.TrackConfig.MORNING_SESSION_INDEX;
import static mak.ctm.app.config.TrackConfig.NETWORKING_SESSION_INDEX;

import java.util.HashMap;
import java.util.Map;

import mak.ctm.dto.Session;

public class SessionFactory implements ISessionFactory{
	
	private final boolean longWait;
	private final Map<Integer, ISessionEmitter> sessionEmitters = new HashMap<Integer, ISessionEmitter>();
	
	public SessionFactory(boolean longWait) {
		this.longWait = longWait;
		sessionEmitters.put(MORNING_SESSION_INDEX, new MorningSessionEmitter());
		sessionEmitters.put(LUNCH_SESSION_INDEX, new LunchSessionEmitter());
		sessionEmitters.put(AFTERNOON_SESSION_INDEX, new AfternoonSessionEmitter());
		sessionEmitters.put(NETWORKING_SESSION_INDEX, new NetworkingSessionEmitter());
	}
	
	@Override
	public Session createSession(final int sessionIndex, final Session previouSession) {
		final ISessionEmitter sessionEmitter = sessionEmitters.get(sessionIndex);
		return sessionEmitter.emitSession(previouSession,this.longWait);
	}
}
