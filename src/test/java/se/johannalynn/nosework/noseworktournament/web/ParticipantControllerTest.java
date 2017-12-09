package se.johannalynn.nosework.noseworktournament.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import se.johannalynn.nosework.noseworktournament.domain.ParticipantRepository;
import se.johannalynn.nosework.noseworktournament.model.Participant;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ParticipantControllerTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private ParticipantRepository repository;

        @Test
        public void shouldGetParticipants() throws Exception {
            List<Participant> participants = new ArrayList<>();
            given(this.repository.findAll())
                    .willReturn(participants);

            this.mvc.perform(get("/participants")).andExpect(status().isOk())
                    .andExpect(model().attribute("participants", Matchers.notNullValue()));
                    //.andExpect(model().attribute("participants", Matchers.isNotNull()));
        }
}
