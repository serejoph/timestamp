package pedro.serejo.TimestampMicroservice.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pedro.serejo.TimestampMicroservice.service.parse.DateParser;
import pedro.serejo.TimestampMicroservice.service.parse.LocalDateParser;
import pedro.serejo.TimestampMicroservice.service.parse.UnixParser;
import pedro.serejo.TimestampMicroservice.service.parse.UtcParser;

@Service
public class DateService {

	public String convert(String date) {

		DateParser parser = new UtcParser(new UnixParser((new LocalDateParser(null))));
		Optional<Long> l = parser.startParsing(date);
		if (l.isEmpty())
			return null;
		String json = convertToJson(l.get());
		return json;

	}

	public String now() {
		
		return convertToJson(System.currentTimeMillis());
	}

	private String convertToJson(Long l) {

		String utc = LocalDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneOffset.UTC).atOffset(ZoneOffset.UTC)
				.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss O"));
		StringBuilder sb = new StringBuilder("{\"unix\":");
		sb.append(l);
		sb.append(",\"utc\":\"");
		sb.append(utc);
		sb.append("\"}");

		return sb.toString();
	}

}
