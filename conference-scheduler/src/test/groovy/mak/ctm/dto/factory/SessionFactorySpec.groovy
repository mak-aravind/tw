package mak.ctm.dto.factory

import mak.ctm.dto.Session
import mak.ctm.dto.factory.SessionFactory
import mak.ctm.dto.factory.TalkFactory
import mak.ctm.io.reader.TalkProposalFileInputReader
import mak.ctm.parser.ValidatedLinesParser
import spock.lang.Specification
import mak.ctm.scheduler.TimeUtil

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

class SessionFactorySpec extends Specification{
	def Session previousSession;
	def sessionFactory = new SessionFactory(true);
	def talkFactory = new TalkFactory();
	def parser = new ValidatedLinesParser(talkFactory)
	
	def "Morning Session should start by 9 AM"(int index, String startTime,
		 int maxMinutesAllowed,String sessionName){
		given:
			previousSession = null
		expect:
			Session session = sessionFactory.createSession(index,previousSession)
			TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
			session.getMaxMinutesAllowed() == maxMinutesAllowed
			session.getSessionName() == sessionName
		where:
			index|startTime		|maxMinutesAllowed	|sessionName
			0	 |"09:00 AM"  	|180				|MORNING_SESSION
	}
	
	def "Lunch Session should start by 12 PM"(int index, String startTime,
		 int maxMinutesAllowed,String sessionName){
		given:
			previousSession = new Session(MORNING_SESSION,MORNING_SESSION_MAX_MINUTES_ALLOWED, MORNING_SESSION_START_TIME);
		expect:
			Session session = sessionFactory.createSession(index,previousSession);
			TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
			session.getMaxMinutesAllowed() == maxMinutesAllowed
			session.getSessionName() == sessionName
		where:
			index|startTime		|maxMinutesAllowed	|sessionName
			1	 |"12:00 PM"  	|60					|LUNCH_SESSION
	}
	
	def "Afternoon Session should start by 01 PM"(int index, String startTime,
		int maxMinutesAllowed,String sessionName){
	   given:
		   previousSession = new Session(LUNCH_SESSION, LUNCH_SESSION_MAX_MINUTES_ALLOWED, LUNCH_SESSION_START_TIME);
	   expect:
		   Session session = sessionFactory.createSession(index,previousSession);
		   TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
		   session.getMaxMinutesAllowed() == maxMinutesAllowed
		   session.getSessionName() == sessionName
	   where:
		   index	|startTime		|maxMinutesAllowed	|sessionName
		   2	 	|"01:00 PM"  	|240				|AFTERNOON_SESSION
   }
   
   def "When afternoon session is not occupied till 4 PM. Networking Session should start by 04 PM"(int index, String startTime,
	   int maxMinutesAllowed,String sessionName){
	  given:
		  previousSession = new Session(AFTERNOON_SESSION, AFTERNOON_SESSION_MAX_MINUTES_ALLOWED, AFTERNOON_SESSION_START_TIME);
		  def listOfTalk = ["Overdoing it in Python 90min"]
		  def talk = parser.getListOfTalks(listOfTalk)
		  previousSession.put(talk)
	  expect:
		  Session session = sessionFactory.createSession(index,previousSession);
		  TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
		  session.getMaxMinutesAllowed() == maxMinutesAllowed
		  session.getSessionName() == sessionName
	  where:
		  index	|startTime		|maxMinutesAllowed	|sessionName
		  3	 	|"04:00 PM"  	|60					|NETWORKING_SESSION
	}
	
	def "SWITCH OFF LONG WAIT When afternoon session is not occupied till 4 PM. Networking Session should start earlier"(int index, String startTime,
		int maxMinutesAllowed,String sessionName){
	   given:
		   previousSession = new Session(AFTERNOON_SESSION, AFTERNOON_SESSION_MAX_MINUTES_ALLOWED, AFTERNOON_SESSION_START_TIME);
		   def listOfTalk = ["Overdoing it in Python 90min"]
		   def talk = parser.getListOfTalks(listOfTalk)
		   previousSession.put(talk)
		   sessionFactory = new SessionFactory(false);
	   expect:
		   Session session = sessionFactory.createSession(index,previousSession);
		   TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
		   session.getMaxMinutesAllowed() == maxMinutesAllowed
		   session.getSessionName() == sessionName
	   where:
		   index	|startTime		|maxMinutesAllowed	|sessionName
		   3	 	|"02:30 PM"  	|60					|NETWORKING_SESSION
	 }
	
	def "When afternoon session is occupied till 4 PM. Networking Session should start by 04 PM"(int index, String startTime,
		int maxMinutesAllowed,String sessionName){
	   given:
		   previousSession = new Session(AFTERNOON_SESSION, AFTERNOON_SESSION_MAX_MINUTES_ALLOWED, AFTERNOON_SESSION_START_TIME);
		   def listOfTalk = ["Overdoing it in Python 180min"]
		   def talk = parser.getListOfTalks(listOfTalk)
		   previousSession.put(talk)
	   expect:
		   Session session = sessionFactory.createSession(index,previousSession);
		   TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
		   session.getMaxMinutesAllowed() == maxMinutesAllowed
		   session.getSessionName() == sessionName
	   where:
		   index	|startTime		|maxMinutesAllowed	|sessionName
		   3	 	|"04:00 PM"  	|60					|NETWORKING_SESSION
	 }
  
  def "When afternoon session is streching 10 minutes beyond 4 PM. Networking Session should start by 04:10 PM"(int index, String startTime,
	  int maxMinutesAllowed,String sessionName){
	 given:
		 previousSession = new Session(AFTERNOON_SESSION, AFTERNOON_SESSION_MAX_MINUTES_ALLOWED, AFTERNOON_SESSION_START_TIME);
		 def listOfTalk = ["Overdoing it in Python 190min"]
		 def talk = parser.getListOfTalks(listOfTalk)
		 previousSession.put(talk)
	 expect:
		 Session session = sessionFactory.createSession(index,previousSession);
		 TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
		 session.getMaxMinutesAllowed() == maxMinutesAllowed
		 session.getSessionName() == sessionName
	 where:
		 index	|startTime		|maxMinutesAllowed	|sessionName
		 3	 	|"04:10 PM"  	|60					|NETWORKING_SESSION
  }
	  
  def "When afternoon session is prolonging upto 5 PM. Networking Session start time should start by 05:00 PM"(int index, String startTime,
		  int maxMinutesAllowed,String sessionName){
		 given:
			 previousSession = new Session(AFTERNOON_SESSION, AFTERNOON_SESSION_MAX_MINUTES_ALLOWED, AFTERNOON_SESSION_START_TIME);
			 def listOfTalk = ["Overdoing it in Python 240min"]
			 def talk = parser.getListOfTalks(listOfTalk)
			 previousSession.put(talk)
		 expect:
			 Session session = sessionFactory.createSession(index,previousSession);
			 TimeUtil.getHHMMFormat(session.getStartTime()) == startTime
			 session.getMaxMinutesAllowed() == maxMinutesAllowed
			 session.getSessionName() == sessionName
		 where:
			 index	|startTime		|maxMinutesAllowed	|sessionName
			 3	 	|"05:00 PM"  	|60					|NETWORKING_SESSION
   }
}
