package br.com.multi24h.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.multi24h.model.JasperCore;
import br.com.multi24h.model.util.Encoding;

@Controller
public class JasperController {

	@RequestMapping(value = "/jasper/generateReport", 
			method = RequestMethod.POST, 
			consumes = "application/json;charset=UTF-8", 
			produces = "application/json;charset=UTF-8")
	public @ResponseBody String generateReport(@RequestHeader("templatePath") String templatePath,
			@RequestHeader("outputPath") String outputPath, @RequestBody() String jsonContent) {
		//Deal with possible encoding problems
		templatePath = new String(templatePath.getBytes(Encoding.ISO8859.getValue()), Encoding.UTF8.getValue());
		outputPath = new String(outputPath.getBytes(Encoding.ISO8859.getValue()), Encoding.UTF8.getValue());
		//Deal with Jasper core manipulation
		JasperCore core = new JasperCore();
		core.generate(templatePath, jsonContent, outputPath);
		//Retrieve the user response
		return "Jasper ok";
	}

}
