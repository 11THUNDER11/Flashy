package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {
	private String descrizione;
	private LocalDateTime timestamp;
	private DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public LogEntry(String s) {
		this.descrizione = s.split("\\|")[1].trim();
		this.timestamp = LocalDateTime.parse(s.split("\\|")[0].trim(), form);
	}

	@Override
	public String toString() {

		return timestamp.format(form) + " | " + descrizione;
	}

}
