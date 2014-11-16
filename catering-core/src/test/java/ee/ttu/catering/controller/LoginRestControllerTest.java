package ee.ttu.catering.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ee.ttu.catering.config.unittest.UnitTestEnv;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = UnitTestEnv.class)
public class LoginRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	Logger LOG = Logger.getLogger(this.getClass());
	
    public LoginRestControllerTest() {}

    MockMvc mvc;
    @Autowired WebApplicationContext wac;
    
    @Before
    public void setup(){
       mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    public static final String CONTENT = "{\"loggedIn\":false,\"username\":\"admin\",\"password\":\"admin\",\"rememberMe\":true}";
    @Test
//    @Transactional
    public void testLogin() {
        try{
        mvc.perform(MockMvcRequestBuilders.post("/rest/login/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(CONTENT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
       
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    @Transactional
    @Ignore
    public void testMenuDelete() {
    	MvcResult result;
    	try{
    		String CONTENT = "{\"id\":2,\"entityVersion\":0,\"name\":\"test\",\"created\":null}";
    		
    		result = mvc.perform(MockMvcRequestBuilders.post("/rest/menu")
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON)
    				.content(CONTENT))
    				.andExpect(status().isInternalServerError())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    				.andReturn();
    		
			mvc.perform(MockMvcRequestBuilders.get("/rest/menu/2")
					.accept(MediaType.APPLICATION_JSON))
			        .andExpect(MockMvcResultMatchers.status().isOk())
			        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
			        .andExpect(MockMvcResultMatchers.content().string(CONTENT));
    		
    		result = mvc.perform(MockMvcRequestBuilders.delete("/rest/menu/2")
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    				.andReturn(); 
    		
    	    LOG.info("RESULT " + result.getResponse().getContentAsString());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
}