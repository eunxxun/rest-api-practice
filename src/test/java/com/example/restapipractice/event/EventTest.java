package com.example.restapipractice.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder()
                .name("REST API")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        //Given
        String name = "Event";
        String desc = "Spring";

        //When
        Event event = new Event();
        event.setName(name);
        event.setDescription(desc);

        //Then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(desc);
    }
}