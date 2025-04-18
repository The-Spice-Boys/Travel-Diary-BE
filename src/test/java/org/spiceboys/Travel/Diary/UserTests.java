package org.spiceboys.Travel.Diary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ContextLoads() {}

    @Test
    public void getUserByUsername() throws Exception {
        /*
        If public user, returns a JSON object with the following properties:
            -- userId
            -- username
            -- bio
            -- profilePicUrl
            -- private
        */
        this.mockMvc
                .perform(get("/api/users/username/wanderer_joe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"userId\": 1, " +
                                "\"username\": \"wanderer_joe\", " +
                                "\"bio\": \"World explorer and coffee lover.\", " +
                                "\"profilePicUrl\":  \"https://picsum.photos/seed/picsum/200/300\", " +
                                "\"private\": false}"));

        // If private user, returns a JSON with only the 'private' property
        this.mockMvc
                .perform(get("/api/users/username/nomad_mike"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"private\": true}"));

        // Returns 404 Not Found status when username does not exist in database
        this.mockMvc
                .perform(get("/api/users/username/exploratory_dave"))
                .andDo(print())
                .andExpect(status().isNotFound());
        }

    @Test
    public void getUserByUserId() throws Exception {
        /*
        If public user, returns a JSON object with the following properties:
            -- userId
            -- username
            -- bio
            -- profilePicUrl
            -- private
        */
        this.mockMvc
                .perform(get("/api/users/userId/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"userId\": 2, " +
                                "\"username\": \"globetrotter_amy\", " +
                                "\"bio\": \"Adventure is out there!\", " +
                                "\"profilePicUrl\":  \"https://picsum.photos/seed/picsum/200/300\", " +
                                "\"private\": false}"));

        // If private user, returns a JSON with only the 'private' property
        this.mockMvc
                .perform(get("/api/users/userId/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"private\": true}"));

        // Returns 404 Not Found status when user ID does not exist in database
        this.mockMvc
                .perform(get("/api/users/username/exploratory_dave"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
