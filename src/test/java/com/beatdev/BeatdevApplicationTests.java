package com.beatdev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
public class BeatdevApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRestService() {
        //testing requests for storing users into database
        try {
            mockMvc.perform(post("/user/?name=name1&email=email1&avatarURL=avatar1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(content().string("1"));
            mockMvc.perform(post("/user/?name=name2&email=email2&avatarURL=avatar2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(content().string("2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //testing retrieving users from database
        try {
            mockMvc.perform(get("/user/1").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value("1"))
                    .andExpect(jsonPath("$.name").value("name1"))
                    .andExpect(jsonPath("$.email").value("email1"))
                    .andExpect(jsonPath("$.avatarURL").value("avatar1"));
            mockMvc.perform(get("/user/2").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value("2"))
                    .andExpect(jsonPath("$.name").value("name2"))
                    .andExpect(jsonPath("$.email").value("email2"))
                    .andExpect(jsonPath("$.avatarURL").value("avatar2"));
            //returns status 404 because user with id 3 was not created
            mockMvc.perform(get("/user/3").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());


        } catch (Exception e) {
            e.printStackTrace();
        }

        //testing changing status of the user in database
        try {
            mockMvc.perform(post("/status/1/?status=online").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value("1"))
                    .andExpect(jsonPath("$.prevStatus").value("OFFLINE"))
                    .andExpect(jsonPath("$.newStatus").value("ONLINE"));
            mockMvc.perform(post("/status/1/?status=offline").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value("1"))
                    .andExpect(jsonPath("$.prevStatus").value("ONLINE"))
                    .andExpect(jsonPath("$.newStatus").value("OFFLINE"));
            //returns status 404 because user with id 3 was not created
            mockMvc.perform(post("/status/3/?status=offline").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
