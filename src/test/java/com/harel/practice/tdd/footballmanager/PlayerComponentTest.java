package com.harel.practice.tdd.footballmanager;

import com.harel.practise.tdd.footballmanager.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Application.class)
@AutoConfigureMockMvc
public class PlayerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFetchPlayerById() throws Exception {
        this.mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("player")));
    }
}
