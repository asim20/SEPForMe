package springsecurity.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Asim").password("aag").roles("ADMIN","DBA");
        
//        try{
//        Class.forName("com.mysql.jdbc.Driver");
//      
//        String DB_URL="jdbc:mysql://141.19.145.165:3306/testdb";
//        String user="root";
//        String passwort="123";
//        Connection con=DriverManager.getConnection(DB_URL,user,passwort);
//        Statement st=con.createStatement();
//        ResultSet rs=st.executeQuery("select * from user");
//        while(rs.next()){
//        	String username=rs.getString("username");
//        	String passwortDB=rs.getString("passwort");
//        	String rolle=rs.getString("rolle");
//        	System.out.println(username+", passwort: "+passwortDB+", Rolle: "+rolle);
//        	auth.inMemoryAuthentication().withUser(username).password(passwortDB).roles(rolle);
//        	
//        }
//        }catch(Exception e){
//        	System.out.println("Can't connect to Database");
//        }
       
    }
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/admin/**").access("hasRole('ADMIN')")
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				.antMatchers("/welcomeAfterLogin/**").access("hasRole('USER')")
				.and().formLogin().loginPage("/login")
				.usernameParameter("ssoId").passwordParameter("password")
				.and().csrf().and().exceptionHandling()
				.accessDeniedPage("/accessDenied");

	}

}