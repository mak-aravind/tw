package mak.ctm.scheduler

import spock.lang.Specification

class TimeUtilSpec extends Specification{
	def "9 * 60 Minutes should be equal to 09:00 AM"(){
		given:
			int timeInMinutes = 9 * 60;
		expect:
			"09:00 AM".equals(TimeUtil.getHHMMFormat(timeInMinutes));
	}
	
	def "9 * 60 + 58 Minutes should be equal to 09:58 AM"(){
		given:
			int timeInMinutes = (9 * 60) + 58;
		expect:
			"09:58 AM".equals(TimeUtil.getHHMMFormat(timeInMinutes));
	}
	
	def "12 * 60 Minutes should be equal to 12:00 PM"(){
		given:
			int timeInMinutes = (12 * 60);
		expect:
			"12:00 PM".equals(TimeUtil.getHHMMFormat(timeInMinutes));
	}
	
	def "40 Minutes should be equal to 00:40 AM"(){
		given:
			int timeInMinutes =  40;
		expect:
			"00:40 AM".equals(TimeUtil.getHHMMFormat(timeInMinutes));
	}
}
