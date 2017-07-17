package mak.ctm.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mak.ctm.scheduler.TimeUtil;

public class Session {
	private final String sessionName;
	private final int maxMinutesAllowed;
	private final List<Talk> sesssionTalks;
	private final int startTime;
	private int currentMinutesConsumed;
	
	public Session(String sessionName,int maxMinutesAllowed,int startTime){
		this.sessionName = sessionName;
		this.maxMinutesAllowed = maxMinutesAllowed;
		this.startTime = startTime;
		this.currentMinutesConsumed = 0;
		this.sesssionTalks = new ArrayList<Talk>();
	}
	
	public boolean put(final Talk talk){
        if (canAccomadate(talk)) {
            sesssionTalks.add(talk);
            currentMinutesConsumed += talk.getDuration();
            return true;
        } else {
            return false; // item didn't fit
        }
	}

	public boolean canAccomadate(final Talk talk) {
		return currentMinutesConsumed + talk.getDuration() <= maxMinutesAllowed;
	}
	
	public void fillAllPossibleSlots(final List<Talk> talksToAccommodate) {
		for (Iterator<Talk> iter = talksToAccommodate.iterator(); iter.hasNext();) {
			final Talk talk = iter.next();
			boolean accommodated = put(talk);
			if (accommodated)
				iter.remove();
		}
	}

	public boolean isSlotAvailableForTalk(final List<Talk> talksToAccommodate) {
		boolean anySpace=false;
		for (Talk talk : talksToAccommodate) {
			anySpace = canAccomadate(talk);
			if (anySpace)
				return anySpace;
		}
		return anySpace;
	}
	
    public int getCurrentMinutesConsumed() {
		return currentMinutesConsumed;
	}

	public int getStartTime() {
		return startTime;
	}
	
	public int getMaxMinutesAllowed() {
		return maxMinutesAllowed;
	}
	
	public String getSessionName() {
		return sessionName;
	}
	@Override
    public String toString() {
		final StringBuilder sessionsToDisplay = new StringBuilder();
		Integer startTimeToDisplay = new Integer(startTime);
		for (Talk talk : sesssionTalks) {
			sessionsToDisplay.append(TimeUtil.getHHMMFormat(startTimeToDisplay) + " ");
			sessionsToDisplay.append(talk);
			sessionsToDisplay.append("\n");
			startTimeToDisplay = startTimeToDisplay.intValue() + talk.getDuration();
		}
        return sessionsToDisplay.toString();
    }
}