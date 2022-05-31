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

		
		
		
		
		DateParser parser =  new UtcParser(new UnixParser((new LocalDateParser(null))));
		Optional<Long> l = parser.startParsing(date);
		if (l.isEmpty()) return null; 
		String json = convertToJson(l.get());
		return json;
		
		//		if (date == null) return "null";
//		if (date.isEmpty()) return "empty";
//		
//		LocalDateTime ldt;
//		try {
//			Long millis = Long.parseLong(date);
//			ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneOffset.UTC);
//			return convertToJson(ldt, millis);
//		} catch (DateTimeException | NumberFormatException e) {
//			
//		}
//		try {
//			ldt = LocalDate.parse(date).atStartOfDay();
//			return convertToJson(ldt);
//		} catch (DateTimeParseException exc) {
//			
//		}
//
//		return null;
	}

	private String convertToJson(LocalDateTime ldt, Long millis) {
		String utc = ldt.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
		StringBuilder sb = new StringBuilder("{\"unix\":");
		sb.append(millis);
		sb.append(",\"utc\":\"");
		sb.append(utc);
		sb.append("\"}");

		return sb.toString();
	
	}

	private String convertToJson(LocalDateTime ldt) {
		String utc = ldt.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
		Long unix = ldt.toEpochSecond(ZoneOffset.UTC) * 1000;

		StringBuilder sb = new StringBuilder("{\"unix\":");
		sb.append(unix);
		sb.append(",\"utc\":\"");
		sb.append(utc);
		sb.append("\"}");

		return sb.toString();
	}

	public String now() {
		LocalDateTime ldt = LocalDateTime.now(ZoneOffset.UTC);
		return convertToJson(ldt, System.currentTimeMillis());
	}
	
	private String convertToJson(Long l) {
		
		String utc = LocalDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneOffset.UTC).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
		StringBuilder sb = new StringBuilder("{\"unix\":");
		sb.append(l);
		sb.append(",\"utc\":\"");
		sb.append(utc);
		sb.append("\"}");

		return sb.toString();
	}

}
