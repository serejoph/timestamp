package pedro.serejo.TimestampMicroservice.service.parse;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
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
			DateTimeFormatter customDTF = DateTimeFormatter.ofPattern("dd MMMM YYYY, O");
			OffsetDateTime ldt = OffsetDateTime.parse(date, customDTF);

			Instant i = ldt.toInstant();
			return Optional.of(i.toEpochMilli());
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}

	}

}
