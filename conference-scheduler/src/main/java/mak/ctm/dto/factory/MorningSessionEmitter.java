package mak.ctm.dto.factory;

import static mak.ctm.app.config.SessionConfig.MORNING_SESSION;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_START_TIME;

import mak.ctm.dto.Session;

public class MorningSessionEmitter implements ISessionEmitter {
	@Override
	public Session emitSession(Session previouSession, boolean longWait) {
		return new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
	}
}
