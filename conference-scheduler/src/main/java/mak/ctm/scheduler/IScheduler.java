package mak.ctm.scheduler;

import java.util.List;

import mak.ctm.dto.Session;
import mak.ctm.dto.Talk;

public interface IScheduler {
	public List<Talk> scheduleTalks(Session session, List<Talk> talksToAccommodate);
	public void scheduleSuplementorySession(Session session);
}
