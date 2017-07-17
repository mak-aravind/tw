package mak.ctm.dto.factory;

import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION;
import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION_MIN_START_TIME;

import mak.ctm.dto.Session;

public class NetworkingSessionEmitter implements ISessionEmitter{
 
	@Override
	public Session emitSession(final Session previouSession,final boolean longWait) {
		final int netWorkingSessionStartTime = getNetworkingSessionStartTime(previouSession,longWait);
		return new Session(NETWORKING_SESSION, NETWORKING_SESSION_MAX_MINUTES_ALLOWED, netWorkingSessionStartTime);
	}

	private int getNetworkingSessionStartTime(final Session previouSession,final boolean longWait) {
		final int startTimeAsPerPreviousSession = previouSession.getStartTime() + previouSession.getCurrentMinutesConsumed();
		return  (longWait && startTimeAsPerPreviousSession < NETWORKING_SESSION_MIN_START_TIME)?
					NETWORKING_SESSION_MIN_START_TIME : startTimeAsPerPreviousSession;
	}
}
