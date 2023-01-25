package com.nttdata.evaluacion.restapi.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.evaluacion.restapi.representation.Informacion;

@RestController
public class InformacionController {

	private static final String template = "Api Evaluacion v1, %s";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/info")
	public Informacion greeting(@RequestParam(value = "fecha", defaultValue = "25/01/2023" ) String fecha) {
		return new Informacion(counter.incrementAndGet(), String.format(template, fecha));
	}
}