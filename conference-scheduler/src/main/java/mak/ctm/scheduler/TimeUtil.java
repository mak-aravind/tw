package mak.ctm.scheduler;

public class TimeUtil {
	
	public static String getHHMMFormat(int totalMinutes){
	    final int  hours = totalMinutes / 60;
	
	    final int hoursToDisplay = getHoursToDisplay(hours);
	    final String minutesToDisplay = getMinutesToDisplay(totalMinutes, hours);
	
	    final StringBuilder displayValue = new StringBuilder();
	    displayValue.append(hoursToDisplay < 10 ? "0"+ hoursToDisplay : hoursToDisplay);
	    displayValue.append(":");
	    displayValue.append(minutesToDisplay);
	    displayValue.append(hours<12 ? " AM" : " PM");
	    return displayValue.toString();
	}

	private static int getHoursToDisplay(int hours) {
		return hours > 12 ? hours - 12 : hours;
	}

	private static String getMinutesToDisplay(final int totalMinutes, final int hours) {
		final int minutes = totalMinutes - (hours * 60);
	    String minutesToDisplay = null;
	    if(minutes == 0 ) minutesToDisplay = "00";     
	    else if( minutes < 10 ) minutesToDisplay = "0" + minutes ;
	    else minutesToDisplay = "" + minutes ;
		return minutesToDisplay;
	}
}
