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

    /*
    Current DTO logic will return a private DTO even for the logged-in user, meaning the logged-in user won't be able
    to see all of their data. Will need to validate/confirm logged-in user on the back-end?? and write tests to account
    for this. - JT
    */

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

        /*
        If private user, returns a JSON with the following properties:
            -- userId
            -- username
            -- private
        */
        this.mockMvc
                .perform(get("/api/users/username/nomad_mike"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"private\": true}"));

        /*
        If logged-in user is requesting their own data, ALWAYS returns a JSON with the following properties:
            -- userId
            -- username
            -- bio
            -- profilePicUrl
            -- private
        */
        // TEST GOES HERE!!!


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

        /*
        If private user, returns a JSON with the following properties:
            -- userId
            -- username
            -- private
        */
        this.mockMvc
                .perform(get("/api/users/userId/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"private\": true}"));

        /*
        If logged-in user is requesting their own data, ALWAYS returns a JSON with the following properties:
            -- userId
            -- username
            -- bio
            -- profilePicUrl
            -- private
        */
        // TEST GOES HERE!!!

        // Returns 404 Not Found status when user ID does not exist in database
        this.mockMvc
                .perform(get("/api/users/username/exploratory_dave"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createUser() throws Exception {
        /*
        Adds a new user to the database and returns a JSON object with the following properties:
            -- userId
            -- username
            -- bio
            -- profilePicUrl
            -- private
        */
        this.mockMvc
                .perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"username\": \"exploratory_dave\", " +
                                "\"firstName\": \"Dave\", " +
                                "\"lastName\": \"Davidson\", " +
                                "\"email\": \"dave@example.com\", " +
                                "\"password\": \"davepassword\", " +
                                "\"bio\": \"Hi I'm Dave and I like to explore!\", " +
                                "\"profilePicUrl\": \"https://example.com\", " +
                                "\"isPrivate\": false}")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(
                        "{\"userId\": 5, " +
                        "\"username\": \"exploratory_dave\", " +
                        "\"bio\": \"Hi I'm Dave and I like to explore!\", " +
                        "\"profilePicUrl\": \"https://example.com\", " +
                        "\"private\": false}"));

        // ERROR TESTS
    }

    @Test
    public void updatePublicUserInfo() throws Exception {}

    @Test
    public void updatePrivateUserInfo() throws Exception {}

    @Test
    public void updateUserPassword() throws Exception {}
}
