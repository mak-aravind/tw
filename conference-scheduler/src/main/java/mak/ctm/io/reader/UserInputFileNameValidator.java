package mak.ctm.io.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import mak.ctm.io.util.FileUtil;

public class UserInputFileNameValidator {
	TalkProposalFileInputReader fileReader;
	
	public List<String> getValidatedInputLines(final String fileName) {
		if (fileName == null) return null;
		
		List<String> validatedInputLines = null;
		try(final Reader inputReader = getInputReader(fileName)){
			if (null == inputReader)
				return null;
			fileReader = new TalkProposalFileInputReader(inputReader);
			validatedInputLines = fileReader.getValidatedInputLines();
		}catch (IOException e) {
			System.out.println("Unexpected IO exception");
			return null;
		}
		return validatedInputLines.isEmpty() ?  null : validatedInputLines;
	}
	


	private Reader getInputReader(final String fileName) {
		Reader inputReader = null;
		try {
			inputReader = FileUtil.getInputStreamReader(getValidatedFileName(fileName));
		} catch (FileNotFoundException e) {
			try {
				inputReader = new FileReader(fileName);
			} catch (FileNotFoundException fileNotFoundException) {
				System.out.println("File not found Please rerun with valid file name.");
				return null;
			}
		}
		return inputReader;
	}

	private String getValidatedFileName(final String fileName) {
		return (null == fileName || fileName.isEmpty()) ?
				mak.ctm.app.config.FileConfig.VALID_DATA_FILE_NAME:
					fileName;
	}
}