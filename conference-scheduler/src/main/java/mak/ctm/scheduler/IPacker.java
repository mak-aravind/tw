package mak.ctm.scheduler;

import java.util.List;

import mak.ctm.dto.Session;
import mak.ctm.dto.Talk;

public interface IPacker {
	public void findAnySpaceAndFill(Session session, List<Talk> talksToAccommodate);
}
