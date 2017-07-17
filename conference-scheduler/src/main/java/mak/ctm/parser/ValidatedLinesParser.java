package mak.ctm.parser;

import static mak.ctm.app.config.ParserConfig.MAX_SESSION_DURATION;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mak.ctm.dto.Talk;
import mak.ctm.dto.factory.ITalkFactory;

public class ValidatedLinesParser implements IParser{

	private ITalkFactory talkFactory;
	
	public ValidatedLinesParser(ITalkFactory talkFactory) {
		this.talkFactory = talkFactory;
	}
	@Override
	public List<Talk> getListOfTalks(final List<String> validatedInputLines) {
		final List<Talk> listOfTalks = new ArrayList<Talk>();
		for (String line : validatedInputLines) {
			final Talk talk = talkFactory.createNewTalk(line);
			if (talk == null || talk.getDuration() > MAX_SESSION_DURATION)
				return Collections.emptyList();
			listOfTalks.add(talk);
		}
		return listOfTalks;
	}
}
