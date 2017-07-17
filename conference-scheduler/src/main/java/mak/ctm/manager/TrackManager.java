package mak.ctm.manager;


import java.util.List;
import mak.ctm.dto.Talk;
import java.util.Collections;
import mak.ctm.dto.Track;
import mak.ctm.scheduler.TalkComparator;
import mak.ctm.scheduler.TalkComparator.SortBy;

public class TrackManager {
	private SessionManager sessionManager;
	private final List<Talk> listOfTalks;

	public TrackManager(List<Talk> listOfTalks) {
		this.listOfTalks = listOfTalks;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void sortListOfTalks(SortBy sortOrder) {
		Collections.sort(listOfTalks, new TalkComparator(sortOrder));
	}

	public void assignTalksToTrack(List<Track> tracks) {
		sortListOfTalks(SortBy.DESCENDING);
		while (!listOfTalks.isEmpty()) {
			final Track track = new Track(listOfTalks, this.sessionManager);
			track.assignTalksToSession();
			tracks.add(track);
		}
	}
}