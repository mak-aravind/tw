package mak.ctm.dto.factory;

import mak.ctm.dto.Session;

public interface ISessionEmitter {
	public Session emitSession(Session previouSession, boolean longWait);
}