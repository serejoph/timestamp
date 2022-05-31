package pedro.serejo.TimestampMicroservice.service.parse;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;
import java.util.Optional;

public class LocalDateParser extends DateParser {

	public LocalDateParser(DateParser next) {
		super(next);
	}

	@Override
	public Optional<Long> parse(String date) {
		try {
			
			
			Long l = LocalDate.parse(date).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
			return Optional.of(l);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

}
