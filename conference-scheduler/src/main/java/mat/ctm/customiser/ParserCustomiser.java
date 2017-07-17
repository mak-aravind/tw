package mat.ctm.customiser;

import java.util.List;

import mak.ctm.dto.Talk;
import mak.ctm.dto.factory.TalkFactory;
import mak.ctm.parser.IParser;
import mak.ctm.parser.ValidatedLinesParser;

public class ParserCustomiser {
	final IParser parser;
	public ParserCustomiser(IParser parser ) {
			this.parser = parser;
	}
	public List<Talk> parseValidatedLines(final List<String> validatedInputLines) {
		final List<Talk> listOfTalks = parser.getListOfTalks(validatedInputLines);
		if (null == listOfTalks || listOfTalks.isEmpty()){
			return null;
		}
		return listOfTalks;
	}
}