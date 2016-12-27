package login.services;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import login.beans.User;
import login.dao.UserDao;

@Component("loginService")
public class LoginService {
	
		
	/**
	 * @param user
	 * @return user object in json
	 * 
	 * Desc: Checks if user is present in database
	 */
	public String login( User user ) {
		
		String returnString="{\"result\": \"Fail\"}";
		ObjectMapper mapper = new ObjectMapper();
		Resource r=new ClassPathResource("applicationContext.xml");  
		BeanFactory factory=new XmlBeanFactory(r);  

		UserDao userDao=(UserDao)factory.getBean("userDao");
		
		
		User existingUser = userDao.getUserByUsername(user.getUsername(), user.getPassword());
		if (existingUser != null) {
			
			try {
				returnString=mapper.writeValueAsString(existingUser);
			} catch (JsonGenerationException e) {				
				e.printStackTrace();
			} catch (JsonMappingException e) {				
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		} else {
			returnString="{\"result\": \"Fail\",\"errorMsg\":\"Authentication Failed\"}";
		}					
		
		return returnString;				
	}
	

	
	

}
