package pedro.serejo.TimestampMicroservice.service.parse;

import java.time.LocalDateTime;
import java.util.Optional;

public abstract class DateParser {

	DateParser next;

	public DateParser(DateParser next) {
		this.next = next;
	}

	public abstract Optional<Long> parse(String date);

	public Optional<Long> tryNext(String date) {
		if (this.next == null) return Optional.empty();
		return next.startParsing(date);
	}

	public Optional<Long> startParsing(String date) {
		Optional<Long> optional = parse(date);
		if (optional.isPresent()) return optional;
		return tryNext(date);
	}
}
