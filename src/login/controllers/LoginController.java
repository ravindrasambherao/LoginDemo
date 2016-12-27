package login.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import login.beans.User;
import login.services.LoginService;

@Controller
@Component
public class LoginController {

	@Autowired
	LoginService loginService;	

	/**
	 * @param user
	 * @return user object in json
	 * 
	 * Desc: Checks the user against db credentials
	 */
	@RequestMapping(value="login", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String login( @RequestBody User user ) {
		return loginService.login(user);		
	}
	
	

	
}