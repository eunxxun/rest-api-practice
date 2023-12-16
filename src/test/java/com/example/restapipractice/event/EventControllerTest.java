package com.example.restapipractice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc; //MockMvc를 이용하면 mocking되어 있는 디스패처 서블릿을 상대로 가짜 요청을 만들어 디스패처 서블릿으로 보내고 응답을 확인할 수 있는 테스트를 만들수 있다.

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                .name("Spring")
                .description("test입니닷")
                .beginEnrollmentDateTime(LocalDateTime.of(2023,12,16,16,53))
                .closeEnrollmentDateTime(LocalDateTime.of(2023,12,17,16,53))
                .beginEventDateTime(LocalDateTime.of(2023,12,16,16,53))
                .endEventDateTime(LocalDateTime.of(2023,12,17,16,53))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("서울역")
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(event))
                        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
        ;
    }
}
