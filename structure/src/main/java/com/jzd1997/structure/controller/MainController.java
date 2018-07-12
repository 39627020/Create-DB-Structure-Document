package com.jzd1997.structure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jzd1997.structure.service.IMainService;

@RestController
public class MainController {
	@Autowired
	IMainService mainService;
	
	@GetMapping("/create")
	public boolean create() {
		return mainService.findStructure();
	}
}
