package mak.ctm.dto.factory;

import mak.ctm.dto.Talk;

public interface ITalkFactory {
	public Talk createNewTalk(String line);
}
