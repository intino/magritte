package tara.lang.model.rules.variable;

import tara.lang.model.Rule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Arrays.asList;

public class DateRule implements Rule<String> {

	private static final DateTimeFormatter[] dateFormats = new DateTimeFormatter[20];
	private static String[] patterns = {"dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm", "dd/MM/yyyy HH", "dd/MM/yyyy", "MM/yyyy", "yyyy"};

	static {
		asList(patterns).forEach(p -> dateFormats[p.length()] = DateTimeFormatter.ofPattern(p));
	}

	@Override
	public boolean accept(String value) {
		return parseDate(value) != null;
	}


	private static LocalDateTime parseDate(String date) {
		if (date.isEmpty()) return null;
		date = process(date);
		if (date.length() < dateFormats.length && dateFormats[date.length()] != null)
			return parse(date);
		return null;
	}

	private static String process(String date) {
		return date.length() == 10 ? date + " 00" :
			date.length() == 7 ? "01/" + date + " 00" :
				date.length() == 4 ? "01/01/" + date + " 00" : date;
	}

	private static LocalDateTime parse(String date) {
		try {
			return LocalDateTime.from(dateFormats[date.length()].parse(date));
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}
