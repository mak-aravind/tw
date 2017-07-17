package ctm.mak.dto

import static mak.ctm.app.config.SessionConfig.MORNING_SESSION;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_MAX_MINUTES_ALLOWED;
import static mak.ctm.app.config.SessionConfig.MORNING_SESSION_START_TIME;

import java.util.List

import mak.ctm.dto.Session
import mak.ctm.dto.factory.TalkFactory
import mak.ctm.parser.ValidatedLinesParser
import spock.lang.Specification

class SessionToStringSpec extends Specification{
	
	def Session session;
	def talkFactory = new TalkFactory();
	def parser = new ValidatedLinesParser(talkFactory);
	
	def "Morning Session accomodated with one talk "(List talks, String printString){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
		expect:
		   session.put(talk)
		   session.toString() == printString
		where:
			talks							 |printString
			["Overdoing it in Python 180min"]|"09:00 AM Overdoing it in Python 180min\n"
	}
	
	def "Morning Session after accomodating two talks one of 100 mins and other of 60 mins"(List talks, String printString){
		given:
			session = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
			def talk = parser.getListOfTalks(talks)
		expect:
		   session.put(talk.get(0))
		   session.put(talk.get(1))
		   session.toString() == printString
		where:
			talks							 										|printString
			["Overdoing it in Python 100min", "Communicating Over Distance 60min"]	|"09:00 AM Overdoing it in Python 100min\n10:40 AM Communicating Over Distance 60min\n"
	}
}
