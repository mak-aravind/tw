package mak.ctm.scheduler
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_START_TIME;

import mak.ctm.dto.Session
import mak.ctm.dto.factory.TalkFactory
import mak.ctm.parser.ValidatedLinesParser
import mak.ctm.scheduler.TalkComparator
import mak.ctm.scheduler.TalkComparator.SortBy
import spock.lang.Specification

class TalkSortingSpec extends Specification{
	def talkFactory = new TalkFactory();
	def parser = new ValidatedLinesParser(talkFactory);
	def "Sort talks in descending order"(){
		given:
			def talks = parser.getListOfTalks([	"Rails Magic 60min",
												"Communicating Over Distance 160min",
												"Accounting-Driven Development 45min",
												"Overdoing it in Python 100min"])
		expect:
			talks.get(0).getDuration() == 60 
			talks.get(1).getDuration() == 160
			talks.get(2).getDuration() == 45
			talks.get(3).getDuration() == 100
			Collections.sort(talks,new TalkComparator(SortBy.DESCENDING));
			talks.get(0).getDuration() == 160 
			talks.get(1).getDuration() == 100
			talks.get(2).getDuration() == 60
			talks.get(3).getDuration() == 45
	}
	def "Sort talks in ascending order"(){
		given:
			def talks = parser.getListOfTalks([	"Rails Magic 60min",
												"Communicating Over Distance 160min",
												"Accounting-Driven Development 45min",
												"Overdoing it in Python 100min"])
		expect:
			talks.get(0).getDuration() == 60
			talks.get(1).getDuration() == 160
			talks.get(2).getDuration() == 45
			talks.get(3).getDuration() == 100
			Collections.sort(talks,new TalkComparator(SortBy.ASCENDING));
			talks.get(0).getDuration() == 45
			talks.get(1).getDuration() == 60
			talks.get(2).getDuration() == 100
			talks.get(3).getDuration() == 160
	}
}