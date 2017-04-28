package springsecurity.configuration;
 

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ViewConfiguration.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
//    @Override
//	protected void customizeRegistration(Dynamic registration) {
//	    registration.setInitParameter("dispatchPostRequest", "true");
//	    super.customizeRegistration(registration);
//	}
    
    
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
}