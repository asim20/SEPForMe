package springsecurity.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@Controller
public class HelloWorldController extends HttpServlet{
 
     
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite. ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
    	if(auth.getPrincipal().toString().contains("ROLE_ADMIN")){
    		System.out.println("eingeloggt");
    		model.addAttribute("user", getPrincipal());
    		return "admin";
    	}else if(auth.getPrincipal().toString().contains("ROLE_USER")){
    		model.addAttribute("user", getPrincipal());
    		return "welcomeAfterLogin";
    	}
        return "welcome";
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	System.out.println(req.getParameter("username"));
    }
    
//    @CrossOrigin
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    @RequestMapping(value="home",headers="Content-Type=text/html",method=RequestMethod.POST)
//    public String home(HttpServletRequest request,HttpServletResponse response){
////    	try {
////			response.getWriter().append("aag");
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//    	System.out.println("localhost:8080/RolePages");
////    	System.out.println(body);
//    	return "Welcome";
//    }
    
//    @CrossOrigin
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    @RequestMapping(method = RequestMethod.OPTIONS)
//    public String handle(@RequestBody String body, HttpServletRequest request,HttpServletResponse response) {
//    	System.out.println("trolololololo");
//    	System.out.println(body);
//        return "it works";
//    }

    
//    @CrossOrigin
//    @ResponseBody
//    @RequestMapping(value="/home",method=RequestMethod.GET)
//    public String gethome( HttpServletRequest request,HttpServletResponse response){
////    	try {
////			response.getWriter().append("aag");
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//    	System.out.println("localhost:8080/RolePages");
////    	System.out.println(body);
//    	return "welcome";
//    }
    
    @RequestMapping(value= "/welcomeAfterLogin", method=RequestMethod.GET)
    public String loggedPage(ModelMap model){
    	
    	return "welcomeAfterLogin";
    }
    
    @RequestMapping(value= "/login", method=RequestMethod.GET)
    public String loginPage(ModelMap model){
    	
    	return "login";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin";
    }
 
    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "dba";
    }
 
    @RequestMapping(value="/logout", method = RequestMethod.GET)
       public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (auth != null){    
        	  System.out.println(auth.getName());
             new SecurityContextLogoutHandler().logout(request, response, auth);
          }
          return "welcome";
       }
 
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }
     
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}