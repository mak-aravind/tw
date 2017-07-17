package mak.ctm.io.reader;

import static mak.ctm.app.config.ParserConfig.PATTERN;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TalkProposalFileInputReader {
	
	private final Reader input;
	
	
	public TalkProposalFileInputReader(Reader input){
		this.input = input;
	}
	public List<String> getValidatedInputLines(){
		try (Stream<String> lineStream = new BufferedReader(input).lines()){
			final List<String> validatedInputLines= lineStream.filter(line->isValid(line))
											.map(line->line.trim())
											.collect(Collectors.toList());
			return validatedInputLines;
		}
	}
	public boolean isValid(final String line){
        return PATTERN.matcher(line).matches();
    }
}
