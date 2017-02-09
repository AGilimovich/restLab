package com.beatdev;

import com.beatdev.domain.User;
import com.beatdev.repository.AbstractUserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeatdevApplicationTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;


    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    AbstractUserRepository userRepository;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @AfterMethod
    public void tearDown() {
        this.restDocumentation.afterTest();
    }

    public void test() {
        try {
            this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(document("index"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test2(){
        try {
            this.mockMvc.perform(get("/user/5").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(document("index", responseFields(
                            fieldWithPath("contact").description("The user's contact details"),
                            fieldWithPath("contact.email").description("The user's email address"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRepository() {
        User user1 = new User();


    }

}
