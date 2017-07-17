package mak.ctm.manager;

import java.util.ArrayList;
import java.util.List;

import mak.ctm.dto.Track;

public class ConferenceManager {
	private TrackManager trackManager;
	private final List<Track> tracks;
	public ConferenceManager(TrackManager trackManager){
		this.trackManager = trackManager; 
		this.tracks = new ArrayList<Track>();
	}

	public void assignTalksToTrack(){
		trackManager.assignTalksToTrack(this.tracks);
	}
	
	public int getNumberOfTracks(){
		return tracks.size();
	}

	@Override
	public String toString() {
		final StringBuilder tracksToDisplay = new StringBuilder();
		int counter = 0;
		for (Track track : tracks) {
			counter = furnishTrackDetails(tracksToDisplay, counter, track);
		}
		return tracksToDisplay.toString();
	}

	private int furnishTrackDetails(final StringBuilder tracksToDisplay, int counter, Track track) {
		tracksToDisplay.append("Track-" + ++counter);
		tracksToDisplay.append("\n");
		tracksToDisplay.append(track);
		return counter;
	}
}
