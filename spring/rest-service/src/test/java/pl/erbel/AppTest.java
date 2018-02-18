package pl.erbel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloDefaultTest() throws Exception {
        mockMvc.perform(
                get("/hello")).
                andExpect(status().is(200)).
                andExpect(jsonPath("name").
                        value("world")).
                andExpect(content().
                        contentType(APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void helloNonDefaultTest() throws Exception {
        mockMvc.perform(
                get("/hello?name=costam")).
                andExpect(status().is(200)).
                andExpect(jsonPath("name").
                        value("costam")).
                andExpect(content().
                        contentType(APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void greetingDefaultTest() throws Exception {
        mockMvc.perform(
                get("/greetings")).
                andExpect(status().is(200)).
                andExpect(jsonPath("name").
                        value("Jan")).
                andExpect(content().
                        contentType(APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void greetingNonDefaultTest() throws Exception {
        mockMvc.perform(
                get("/greetings?name=costam")).
                andExpect(status().is(200)).
                andExpect(jsonPath("name").
                        value("costam")).
                andExpect(content().
                        contentType(APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(
                post("/testpost")).
                andExpect(status().is(200)).
                andExpect(jsonPath("name").
                        value("Request method has been set to POST")).
                andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
    }

}