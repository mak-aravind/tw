package mak.ctm.dto;

public class Talk {
	private final String title;
	private final int duration;
	
	public Talk(String title, int duration) {
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return this.title;
	}

	public int getDuration() {
		return this.duration;
	}

	@Override
	public String toString() {
		final StringBuilder talkToDisplay = new StringBuilder();
		talkToDisplay.append(this.title);
		talkToDisplay.append(" ");
		talkToDisplay.append(this.duration);
		talkToDisplay.append("min");
		return talkToDisplay.toString();
	}
}
