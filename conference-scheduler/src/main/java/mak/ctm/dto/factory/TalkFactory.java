package mak.ctm.dto.factory;

import static mak.ctm.app.config.ParserConfig.DURATION_INDEX;
import static mak.ctm.app.config.ParserConfig.NAME_INDEX;
import static mak.ctm.app.config.ParserConfig.PATTERN;

import java.util.regex.Matcher;

import mak.ctm.dto.Talk;

public class TalkFactory implements ITalkFactory{
	public Talk createNewTalk(String line){
		final Matcher matcher = PATTERN.matcher(line);
		if (matcher.find() == false){
			return null;
		}
		final String name = matcher.group(NAME_INDEX);
		final String durationString = matcher.group(DURATION_INDEX);
		final int duration = durationString == null ? Integer.parseInt("5"):
													  Integer.parseInt(durationString);
		return new Talk(name,duration);
	}
}