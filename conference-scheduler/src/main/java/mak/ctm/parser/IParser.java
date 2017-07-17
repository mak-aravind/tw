package mak.ctm.parser;

import java.util.List;

import mak.ctm.dto.Talk;

public interface IParser {
	public List<Talk> getListOfTalks(List<String> validatedInputLines);
}
