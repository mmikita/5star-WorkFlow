package com.starworkflow.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {

	
	   @RequestMapping(value = "/user/", method = RequestMethod.GET)
	    public String all() {
	     return "list";
	    }
	
}
