package mat.ctm.customiser;

import mak.ctm.dto.factory.ISessionFactory;
import mak.ctm.dto.factory.SessionFactory;
import mak.ctm.manager.SessionManager;
import mak.ctm.manager.TrackManager;
import mak.ctm.scheduler.IPacker;
import mak.ctm.scheduler.IScheduler;
import mak.ctm.scheduler.SessionPacker;
import mak.ctm.scheduler.SessionScheduler;

import static mak.ctm.app.config.ConferenceMangerConfig.LONG_WAIT;

public class TrackManagerCustomiser {
	private TrackManager trackManager;

	public TrackManagerCustomiser(TrackManager trackManager) {
		this.trackManager = trackManager;
	}

	public TrackManager getCustomisedTrackManager() {
		SessionManager sessionManager = configureSessionManager();
		this.trackManager.setSessionManager(sessionManager);
		return this.trackManager;
	}

	private SessionManager configureSessionManager() {
		IPacker sessionPacker = new SessionPacker();
		IScheduler sessionScheduler = new SessionScheduler(sessionPacker);
		ISessionFactory sessionFactory = new SessionFactory(LONG_WAIT);
		SessionManager sessionManager = new SessionManager(sessionFactory, sessionScheduler);
		return sessionManager;
	}
}