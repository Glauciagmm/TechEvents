package com.techevents.techevents.service;

import com.techevents.techevents.entity.Events;
import com.techevents.techevents.repository.EventsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventsServicesImplTest {
    @Mock
    private EventsRepository eventsRepository;

    private EventsServicesImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new EventsServicesImpl (eventsRepository);

    }
    @Test
    void findAll() {
        //when
        underTest.findAll();
        //then
        verify(eventsRepository).findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    @Test
    void listFeatured() {
        underTest.listFeatured();
        //then
        verify(eventsRepository).findByFeaturedIsTrue();
    }

    @Test
    void canSaveEvent() {
        Events event = new Events(
                "Event",
                "2022/05/02",
                "img",
                5,
                "A new Event",
                true,
                "masterclass"

        );

        //when
        underTest.save(event);

        //then
        ArgumentCaptor<Events> eventsArgumentCaptor =
                ArgumentCaptor.forClass(Events.class);

        verify(eventsRepository).save(eventsArgumentCaptor.capture());

        Events capturedEvents = eventsArgumentCaptor.getValue();
        assertThat(capturedEvents).isEqualTo(event);
    }

    @Test
    void canFindEventById() {
        Events eventId = new Events(
                1L,
                "Event",
                "2022/05/02",
                "img",
                5,
                "A new Event",
                true,
                "masterclass"
        );

        //when
        underTest.findById(eventId.getId());

        //then
        verify(eventsRepository).findById(1L);
    }

    @Test
    void canDeleteEvent() {
        Events eventDelete = new Events(
                1L,
                "Event",
                "2022/05/02",
                "img",
                5,
                "A new Event",
                true,
                "masterclass"
        );
        underTest.delete(eventDelete.getId());
        verify(eventsRepository).deleteById(1L);
    }
}