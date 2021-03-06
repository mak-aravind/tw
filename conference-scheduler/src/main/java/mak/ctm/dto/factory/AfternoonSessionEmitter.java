package mak.ctm.dto.factory;

import static mak.ctm.app.config.SessionConfig.AFTERNOON_SESSION;
import static mak.ctm.app.config.SessionConfig.AFTERNOON_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.AFTERNOON_SESSION_START_TIME;

import mak.ctm.dto.Session;

public class AfternoonSessionEmitter implements ISessionEmitter{
	@Override
	public Session emitSession(Session previouSession,boolean longWait) {
		return new Session(AFTERNOON_SESSION, AFTERNOON_SESSION_MAX_MINUTES_ALLOWED, AFTERNOON_SESSION_START_TIME);
	}
}
