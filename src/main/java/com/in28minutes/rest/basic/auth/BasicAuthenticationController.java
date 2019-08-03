package com.in28minutes.rest.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthenticationController {

	/*able to get here only when sending the basic authentication header created with interceptor
	 *when someone logs in, they'll hit this url, if proper authentication, they'll get the response back
	 *most of the work is done automatically by spring security in combination with configuration we provided
	 */
	@GetMapping("/basicauth")
	public AuthenticationBean helloWorldBean() {
		// throw new RuntimeException("Oh no! some error has occured!");
		return new AuthenticationBean("you are authenticated");
	}

}
