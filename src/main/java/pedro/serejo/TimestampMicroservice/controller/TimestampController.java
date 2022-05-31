package pedro.serejo.TimestampMicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String getTimestamp(@PathVariable(name = "date") String date) {
		return dateService.convert(date);
		
	}
	
	@GetMapping()
	public String currentTimestamp() {
		return dateService.now();
	}
}
