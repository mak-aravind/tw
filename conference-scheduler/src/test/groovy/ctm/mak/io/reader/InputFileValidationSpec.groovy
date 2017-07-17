package ctm.mak.io.reader

import spock.lang.Specification

import static mak.ctm.io.util.FileUtil.getInputStreamReader

import mak.ctm.io.reader.TalkProposalFileInputReader

import static tw.scheduler.app.testconfig.File.EMPTY_TEST_FILE_NAME
import static tw.scheduler.app.testconfig.File.VALID_TEST_DATA_FILE_NAME
import static java.util.Collections.EMPTY_LIST


class InputFileValidationSpec extends Specification{
	def "Usage of non-existing file should throw FileNotFoundException"(){
		when:
			def fileInput = getInputStreamReader("doesNotExist.txt")
		then:
			thrown(FileNotFoundException)
	}
	
	def "Usage of empty file should return empty list"(){
		given:
			def fileInput = getInputStreamReader(EMPTY_TEST_FILE_NAME)
			def inputReader = new TalkProposalFileInputReader(fileInput)
		when:
			def result = inputReader.getValidatedInputLines()
		then:
			result == EMPTY_LIST
	}

	def "Single line not ending with min or lightning should return empty list"(String talk,List result){
		given:
			def input = talk
			def stringReader = new StringReader(input)
			def inputReader = new TalkProposalFileInputReader(stringReader)
		expect:
			inputReader.getValidatedInputLines() == result
		where:
			talk 										|result
			"Overdoing it in Python 45in"				|EMPTY_LIST
			"Rails for Python Developers 2hrs"			|EMPTY_LIST
			"User Interface CSS in Rails Apps 30"		|EMPTY_LIST
			"Woah ligtning"								|EMPTY_LIST
			" "											|EMPTY_LIST
			""											|EMPTY_LIST	
	}
	
	def "Each line ends with min or lightning should pass through validation"(String talk,List result){
		given:
			def input = talk
			def stringReader = new StringReader(input)
			def inputReader = new TalkProposalFileInputReader(stringReader)
		expect:
			inputReader.getValidatedInputLines() == result
		where:
			talk 										|result
			"Overdoing it in Python 45min"				|[talk]
			"Rails for Python Developers lightning"		|[talk]
	}
	
	def "More than one line some ending with min or lightning and some not ending with min or lightning"(List talks,List result){
		given:
			def stringReader = new StringReader(talks.join("\n"))
			def inputReader = new TalkProposalFileInputReader(stringReader)
		expect:
			inputReader.getValidatedInputLines() == result
		where:
			talks 																			|result
			["Overdoing it in Python 45min","Rails for Python Developers 20min"]			|[talks.get(0),talks.get(1)]
			["Overdoing it in Python 45"," ","Rails for Python Developers 20min"]			|[talks.get(2)]
			["Rails for Python Developers 20mns"," ","User Interface CSS in Rails Apps 30"]	|EMPTY_LIST
	}
}
