package mak.ctm.scheduler

import mak.ctm.dto.Track
import mak.ctm.dto.factory.SessionFactory
import mak.ctm.dto.factory.TalkFactory
import mak.ctm.manager.ConferenceManager
import mak.ctm.manager.TrackManager
import mak.ctm.parser.ValidatedLinesParser
import mat.ctm.customiser.TrackManagerCustomiser
import spock.lang.Specification

class ConferenceManagerSpec extends Specification{

	def talkFactory = new TalkFactory();
	def parser = new ValidatedLinesParser(talkFactory)
	def listOfTalk = ["Writing Fast Tests Against Enterprise Rails 60min",
						"Overdoing it in Python 45min",
						"Lua for the Masses 30min",
						"Ruby Errors from Mismatched Gem Versions 45min",
						"Common Ruby Errors 45min",
						"Rails for Python Developers lightning",
						"Communicating Over Distance 60min",
						"Accounting-Driven Development 45min",
						"Woah 30min",
						"Sit Down and Write 30min",
						"Pair Programming vs Noise 45min",
						"Rails Magic 60min",
						"Ruby on Rails: Why We Should Move On 60min",
						"Clojure Ate Scala (on my project) 45min",
						"Programming in the Boondocks of Seattle 30min",
						"Ruby vs. Clojure for Back-End Development 30min",
						"Ruby on Rails Legacy App Maintenance 60min",
						"A World Without HackerNews 30min",
						"User Interface CSS in Rails Apps 30min"]
	
	def "All can accomodate in 2 tracks"(){
	   given:
		   def talksToAccommodate = parser.getListOfTalks(listOfTalk)
		   def TrackManager trackManager = new TrackManager(talksToAccommodate);
		   def TrackManagerCustomiser trackManagerManagerCustomiser = new TrackManagerCustomiser(trackManager);
		   def ConferenceManager conferenceManager = new ConferenceManager(trackManagerManagerCustomiser.getCustomisedTrackManager());
	   expect:
	   	   conferenceManager.assignTalksToTrack()
	   	   conferenceManager.getNumberOfTracks() == 2
	}
}
