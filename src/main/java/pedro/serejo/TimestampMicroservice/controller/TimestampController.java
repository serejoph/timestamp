package pedro.serejo.TimestampMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pedro.serejo.TimestampMicroservice.service.DateService;

@RestController
@RequestMapping("/api")
public class TimestampController {

	@Autowired
	DateService dateService;

	@GetMapping("/{date}")
	@ResponseBody
	
	
	public ResponseEntity<String> getTimestamp(@PathVariable(name = "date") String date) {
		
		
		String body = dateService.convert(date);
		if (body == null) return ResponseEntity.status(HttpStatus.NOT_MODIFIED).contentType(MediaType.APPLICATION_JSON).body("{\"error\":\"Invalid Date\"}");
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(body);
		

	}

	@GetMapping()
	public ResponseEntity<String> currentTimestamp() {
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(dateService.now());
		
	}
}
