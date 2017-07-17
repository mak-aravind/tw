package mak.ctm.scheduler;

import java.util.Comparator;

import mak.ctm.dto.Talk;

public class TalkComparator implements Comparator<Talk>{
	
	  public enum SortBy {ASCENDING, DESCENDING}

	  final private SortBy sortBy;

	  public TalkComparator(SortBy sortBy) {
	    this.sortBy = sortBy;
	  }

	  @Override
	  public int compare(final Talk talk1, final Talk talk2) {
	    final Integer duration1 = talk1.getDuration();
	    final Integer duration2 = talk2.getDuration();
	    final int compare = (int) Math.signum(duration1.compareTo(duration2));
	    return sortBy == SortBy.ASCENDING ? compare : compare * (-1);
	  }
}
