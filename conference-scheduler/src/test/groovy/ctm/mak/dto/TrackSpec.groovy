package ctm.mak.dto

import mak.ctm.dto.Track
import mak.ctm.dto.factory.SessionFactory
import mak.ctm.dto.factory.TalkFactory
import mak.ctm.manager.SessionManager
import mak.ctm.parser.ValidatedLinesParser
import mak.ctm.scheduler.SessionPacker
import mak.ctm.scheduler.SessionScheduler
import spock.lang.Specification

class TrackSpec extends Specification{
	def sessionFactory = new SessionFactory(true);
	def sessionPacker = new SessionPacker()
	def sessionScheduler = new SessionScheduler(sessionPacker)
	def sessionManager = new SessionManager(sessionFactory,sessionScheduler)
	def talkFactory = new TalkFactory();
	def parser = new ValidatedLinesParser(talkFactory)
	
	def "All can accomodate in morning session"(){
	   given:
		   def listOfTalk = ["session-1 60min","session-2 60min","session-3 60min"]
		   def talksToAccommodate = parser.getListOfTalks(listOfTalk)
		   def track = new Track(talksToAccommodate, sessionManager)
		   track.assignTalksToSession()
	   expect:
		   talksToAccommodate.size() == 0
		   track.getNumberOfSessions() == 3
	}
	
	def "Talks cannot accomodate morning session hence expects a afternoon session which has to preced with lunch and finish with networking session"(){
		given:
			def listOfTalk = ["session-1 60min","session-2 60min","session-3 60min","session-4 60min"]
			def talksToAccommodate = parser.getListOfTalks(listOfTalk)
			def track = new Track(talksToAccommodate, sessionManager)
			track.assignTalksToSession()
		expect:
			talksToAccommodate.size() == 0
			track.getNumberOfSessions() == 4
	 }

}
