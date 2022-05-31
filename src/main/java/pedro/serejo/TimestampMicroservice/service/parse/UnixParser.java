package pedro.serejo.TimestampMicroservice.service.parse;

import java.time.DateTimeException;
import java.util.Optional;

public class UnixParser extends DateParser{

	public UnixParser(DateParser next) {
		super(next);
	}

	@Override
	public Optional<Long> parse(String date) {
		try {
			Long millis = Long.parseLong(date);
			
			return Optional.of(millis);
		} catch (DateTimeException | NumberFormatException e) {
			return Optional.empty();
		}
	}

}
