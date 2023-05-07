package hu.cubix.cloud.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAndGetRecentMessages() throws Exception {
        mockMvc.perform(post("/message").contentType(MediaType.TEXT_PLAIN).content("test6"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(get("/message/recent"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("test6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("test5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value("test4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3]").value("test3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[4]").value("test2"));
    }
}
