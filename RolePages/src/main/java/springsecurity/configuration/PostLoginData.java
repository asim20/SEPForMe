package springsecurity.configuration;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/home")
public class PostLoginData {

	@ResponseBody
	@CrossOrigin
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.POST)
	public void hope(@ModelAttribute("asim") String asim,@RequestParam("ssoId") String username,@RequestParam("password") String password){
		
		System.out.println(username+", "+password);
		
		System.out.println("post Worked");
//		return "testPost worked";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String hope2(){
		System.out.println("Get Worked");
		return "testGet worked";
	}
	
}
