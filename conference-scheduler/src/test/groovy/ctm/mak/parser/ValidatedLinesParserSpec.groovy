package ctm.mak.parser

import java.lang.invoke.AbstractValidatingLambdaMetafactory
import java.util.List

import javax.management.monitor.Monitor.SchedulerTask

import spock.lang.Specification
import static java.util.Collections.EMPTY_LIST

import mak.ctm.dto.factory.TalkFactory
import mak.ctm.io.reader.TalkProposalFileInputReader
import mak.ctm.parser.ValidatedLinesParser


class ValidatedLinesParserSpec extends Specification{
	def talkFactory = new TalkFactory();
	def parser = new ValidatedLinesParser(talkFactory)
	
	def "A title which is not lightning should be a elaborated talk"(List validatedLines,
		String title, int duration){
		given:
			def listOfTalks = parser.getListOfTalks(validatedLines)
			def talk = listOfTalks.get(0);
		expect:
			talk.getTitle() == title
			talk.getDuration() == duration
		where:
			validatedLines 					|title					 |duration
			["Overdoing it in Python 45min"]|"Overdoing it in Python"|45	  
	}
	
	def "A title which is lightning should have a duration of 5 minutes"(List validatedLines,
		String title, int duration){
		given:
			def listOfTalks = parser.getListOfTalks(validatedLines);
			def talk = listOfTalks.get(0);
		expect:
			talk.getTitle() == title
			talk.getDuration() == duration
		where:
			validatedLines 						|title					 |duration
			["Overdoing it in Python lightning"]|"Overdoing it in Python"|5			
	}
	
	def "A title which is BIG expects more time should return empty list"(List validatedLines,
		List result){
		given:
			def listOfTalks = parser.getListOfTalks(validatedLines);
		expect:
			listOfTalks == result
		where:
			validatedLines 						|result
			["Overdoing it in Python 245min"]	|EMPTY_LIST
	}
	
	def "A meeting porposal whose pattern is not matching should return empty list"(List validatedLines,
		List result){
		given:
			def listOfTalks = parser.getListOfTalks(validatedLines);
		expect:
			listOfTalks == result
		where:
			validatedLines 					|result
			["Overdoing it in Python 245"]	|EMPTY_LIST
	}
}
