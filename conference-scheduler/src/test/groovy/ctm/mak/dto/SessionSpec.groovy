package ctm.mak.dto

import spock.lang.Specification
import static mak.ctm.app.config.SessionConfig.AFTERNOON_SESSION;
import static mak.ctm.app.config.SessionConfig.AFTERNOON_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.AFTERNOON_SESSION_START_TIME;
import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION;
import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.LUNCH_SESSION_START_TIME;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_START_TIME;
import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION;
import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.NETWORKING_SESSION_MIN_START_TIME;

import mak.ctm.dto.Session
import mak.ctm.dto.factory.TalkFactory
import mak.ctm.parser.ValidatedLinesParser

class SessionSpec extends Specification{
	def Session session;
	def talkFactory = new TalkFactory();
	def parser = new ValidatedLinesParser(talkFactory);
	
	
	def "Morning Session should accomodate a talk for 180 mins "(List talks, boolean canAccomadate){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
		expect:
		   session.canAccomadate(talk) == canAccomadate
		where:
			talks							 |canAccomadate
			["Overdoing it in Python 180min"]|true
	}
	
	def "Morning Session should not accomodate talks whose duration are more than 180mins "(List talks, boolean canAccomadate){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
		expect:
		   session.canAccomadate(talk) == canAccomadate
		where:
			talks							 |canAccomadate
			["Overdoing it in Python 190min"]|false
	}
	
	def "when try to accomodate talks > 180mins in a Morning Session, the currentMinutes consumed will be zero"(List talks, int currentMinutesConsumed){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
			session.put(talk)
		expect:
		   session.getCurrentMinutesConsumed() == currentMinutesConsumed
		where:
			talks							 |currentMinutesConsumed
			["Overdoing it in Python 190min"]|0
	}
	
	def "Morning Session after accomodating 180mins of talk should return consumed time as 180mins "(List talks, int currentMinutesConsumed){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
		expect:
		   session.put(talk)
		   session.getCurrentMinutesConsumed() == currentMinutesConsumed
		where:
			talks							 |currentMinutesConsumed
			["Overdoing it in Python 180min"]|180
	}
	
	def "Morning Session after accomodating two talks one of 100 mins and other of 60 mins should return consumed time as 160mins"(List talks, int currentMinutesConsumed){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
		expect:
		   session.put(talk.get(0))
		   session.put(talk.get(1))
		   session.getCurrentMinutesConsumed() == currentMinutesConsumed
		where:
			talks							 										|currentMinutesConsumed
			["Overdoing it in Python 100min", "Communicating Over Distance 60min"]	|160
	}
	
	def "Morning Session after accomodating two talks 100 mins, 60 mins should be able to accomodate another talk of 20mins"(List talks, boolean canAccomodate){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
			def listOfTalk = ["Woah 20min"]
			def twentyMinutesTalk = parser.getListOfTalks(listOfTalk)
		expect:
		   session.put(talk.get(0))
		   session.put(talk.get(1))
		   session.canAccomadate(twentyMinutesTalk) == canAccomodate
		where:
			talks							 										|canAccomodate
			["Overdoing it in Python 100min", "Communicating Over Distance 60min"]	|true
	}
}
