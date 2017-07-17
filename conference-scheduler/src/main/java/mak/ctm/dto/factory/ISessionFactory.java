package mak.ctm.dto.factory;

import mak.ctm.dto.Session;

public interface ISessionFactory {
	public Session createSession(int sessionCounter,  Session previouSession);
}