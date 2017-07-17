package mak.ctm.dto.factory;

import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION;
import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION_START_TIME;

import mak.ctm.dto.Session;

public class LunchSessionEmitter implements ISessionEmitter{
	@Override
	public Session emitSession(Session previouSession, boolean longWait) {
		return new Session(LUNCH_SESSION, LUNCH_SESSION_MAX_MINUTES_ALLOWED, LUNCH_SESSION_START_TIME);
	}
}
