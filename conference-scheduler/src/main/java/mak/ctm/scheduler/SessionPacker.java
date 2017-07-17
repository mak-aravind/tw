package mak.ctm.scheduler;

import java.util.List;

import mak.ctm.dto.Session;
import mak.ctm.dto.Talk;

public class SessionPacker implements IPacker{
	public void findAnySpaceAndFill(final Session session, final List<Talk> talksToAccommodate) {
		while(!talksToAccommodate.isEmpty()&& 
				session.isSlotAvailableForTalk(talksToAccommodate)){ 
			session.fillAllPossibleSlots(talksToAccommodate);
		}
	}
}
