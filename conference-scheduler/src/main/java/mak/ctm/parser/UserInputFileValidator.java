package mak.ctm.parser;

import java.util.List;

import mak.ctm.dto.Talk;
import mak.ctm.dto.factory.ITalkFactory;
import mak.ctm.dto.factory.TalkFactory;

public class UserInputFileValidator {
	final public ITalkFactory talkFactory;
	public UserInputFileValidator() {
		this.talkFactory = new TalkFactory();
	}
	
	public List<Talk> parseValidatedLines(final List<String> validatedInputLines) {
		final IParser parser = new ValidatedLinesParser(talkFactory);
		final List<Talk> listOfTalks = parser.getListOfTalks(validatedInputLines);
		if (null == listOfTalks || listOfTalks.isEmpty()){
			return null;
		}
		return listOfTalks;
	}
}