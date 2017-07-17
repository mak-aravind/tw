package mak.ctm.app.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import mak.ctm.dto.Talk;
import mak.ctm.dto.factory.TalkFactory;
import mak.ctm.io.reader.UserInputFileNameValidator;
import mak.ctm.manager.ConferenceManager;
import mak.ctm.manager.TrackManager;
import mak.ctm.parser.IParser;
import mak.ctm.parser.ValidatedLinesParser;
import mat.ctm.customiser.ParserCustomiser;
import mat.ctm.customiser.TrackManagerCustomiser;

public class ConferenceTrackMangement {
	
	final UserInputFileNameValidator userInputFileNameValidator = new UserInputFileNameValidator();
	final IParser validatedLinesParser = new ValidatedLinesParser(new TalkFactory());
	final ParserCustomiser parserCustomiser = new ParserCustomiser(validatedLinesParser);
	
	public static void main(String[] args) {
		final ConferenceTrackMangement app = new ConferenceTrackMangement();
		printUserInstructions();
		String fileName = readUserInput();
		if (null != fileName)
			app.run(fileName);
	}

	public void run(final String fileName) {
		final List<Talk> listOfTalks = getListOfTalks(fileName);
		if (null == listOfTalks)
			System.out.println("Invalid/corrupted talk proposals re-run with valid file.");
		final TrackManager trackManager = new TrackManager(listOfTalks);
		final TrackManagerCustomiser trackManagerCustomiser = new TrackManagerCustomiser(trackManager);
		final ConferenceManager conferenceManager = new ConferenceManager(trackManagerCustomiser.getCustomisedTrackManager());
		conferenceManager.assignTalksToTrack();
		System.out.print(conferenceManager);
	}

	private List<Talk> getListOfTalks(final String fileName) {
		final List<String> validatedInputLines = userInputFileNameValidator.getValidatedInputLines(fileName);
		if (null == validatedInputLines) return null;
		final List<Talk> listOfTalks = parserCustomiser.parseValidatedLines(validatedInputLines);
		if (null == listOfTalks) return null;
		return listOfTalks;
	}
	
	private static String readUserInput() {
		try (final BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			final String fileName = br.readLine();
			return fileName;
		} catch (IOException e) {
			System.out.println("Unexpected IO exception");
			return null;
		}
	}
	
	private static void printUserInstructions() {
		System.out.println("Leave empty to pick DEFAULT file.");
		System.out.println("Type file name ALONE if file available in project's src/main/resources folder.");
		System.out.println("Otherwise FULLY qualified name.\n\n");
		System.out.print("Enter File Name:");
	}
}