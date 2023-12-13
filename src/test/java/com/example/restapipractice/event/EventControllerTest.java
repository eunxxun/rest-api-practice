package com.example.restapipractice.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc; //MockMvc를 이용하면 mocking되어 있는 디스패처 서블릿을 상대로 가짜 요청을 만들어 디스패처 서블릿으로 보내고 응답을 확인할 수 있는 테스트를 만들수 있다.

    @Test
    public void createEvent() throws Exception {
        mockMvc.perform(post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        )
                .andExpect(status().isCreated());
    }
}
