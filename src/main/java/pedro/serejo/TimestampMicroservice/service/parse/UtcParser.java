package pedro.serejo.TimestampMicroservice.service.parse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

public class UtcParser extends DateParser {

	public UtcParser(DateParser next) {
		super(next);
	}

	@Override
	public Optional<Long> parse(String date) {
		try {
			Locale.setDefault(Locale.US);
			
			LocalDateTime ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMMM yyyy, O")).atStartOfDay();
			return Optional.of(ld.toInstant(ZoneOffset.UTC).toEpochMilli());
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}

	}

}
