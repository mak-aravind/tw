package mak.ctm.app.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

public class ParserConfig {
	private ParserConfig(){
		throw new AssertionError();//Big Bang when instantiated from inside class(locally)
	}
	private static final String INPUT_VALIDATION_REGEX = "^(.+)\\s(\\d+)?\\s?((min)|(lightning))$";
	public static final Pattern PATTERN = Pattern.compile(INPUT_VALIDATION_REGEX);
	public static final int NAME_INDEX = 1;
	public static final int DURATION_INDEX = 2;
	public static final int TALK_TYPE_INDEX=3;
	
    public static final int MORNING_SESSION_DURATION = 180;
    public static final int LUNCH_SESSION_DURATION = 60;
    public static final int AFTERNOON_SESSION_DURATION = 240;

    public static final int MAX_SESSION_DURATION = Collections.max(Arrays.asList(
            MORNING_SESSION_DURATION, LUNCH_SESSION_DURATION, AFTERNOON_SESSION_DURATION));
}
