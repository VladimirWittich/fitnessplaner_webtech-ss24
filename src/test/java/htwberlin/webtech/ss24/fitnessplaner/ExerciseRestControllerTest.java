package htwberlin.webtech.ss24.fitnessplaner;

import com.fasterxml.jackson.databind.ObjectMapper;
import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import htwberlin.webtech.ss24.fitnessplaner.web.persistence.ExerciseManipulationRequest;
import htwberlin.webtech.ss24.fitnessplaner.web.service.ExerciseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(ExerciseRestController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ExerciseRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseService exerciseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetAllExercises() throws Exception {
        Exercise exercise = new Exercise(
                "Bench Press",
                "John Doe",
                3,
                List.of(12, 10, 8),
                List.of(80, 85, 90),
                255,
                LocalDate.of(2024, 6, 30)
        );

        given(exerciseService.findAll()).willReturn(List.of(exercise));

        mockMvc.perform(get("/workoutplan/all"))
                .andExpect(status().isOk()) // Assert HTTP status is 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Assert content type is JSON
                .andExpect(jsonPath("$[0].name").value("Bench Press")) // Assert name field
                .andExpect(jsonPath("$[0].owner").value("John Doe")) // Assert owner field
                .andExpect(jsonPath("$[0].sets").value(3)) // Assert sets field
                .andExpect(jsonPath("$[0].repetitions").isArray()) // Assert repetitions is an array
                .andExpect(jsonPath("$[0].repetitions", hasSize(3))) // Assert repetitions array size is 3
                .andExpect(jsonPath("$[0].repetitions[0]").value(12)) // Assert first repetition value
                .andExpect(jsonPath("$[0].repetitions[1]").value(10)) // Assert second repetition value
                .andExpect(jsonPath("$[0].repetitions[2]").value(8)) // Assert third repetition value
                .andExpect(jsonPath("$[0].weight").isArray()) // Assert weight is an array
                .andExpect(jsonPath("$[0].weight", hasSize(3))) // Assert weight array size is 3
                .andExpect(jsonPath("$[0].weight[0]").value(80)) // Assert first weight value
                .andExpect(jsonPath("$[0].weight[1]").value(85)) // Assert second weight value
                .andExpect(jsonPath("$[0].weight[2]").value(90)) // Assert third weight value
                .andExpect(jsonPath("$[0].totalWeight").value(255)) // Assert totalWeight field
                .andExpect(jsonPath("$[0].createdAt").value("2024-06-30")); // Assert createdAt field
    }


    @Test
    void testCreateExercise() throws Exception {
        ExerciseManipulationRequest request = new ExerciseManipulationRequest(
                "Bench Press",
                3,
                List.of(12, 10, 8),
                List.of(80, 85, 90), 2000, "test@test"
        );

        Exercise createdExercise = new Exercise(
                "Bench Press",
                "user",
                1,
                List.of(12, 10, 8),
                List.of(80, 85, 90),
                255,
                null
        );

        given(exerciseService.create(any(ExerciseManipulationRequest.class))).willReturn(createdExercise);

        mockMvc.perform(MockMvcRequestBuilders.post("/workoutplan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Assert HTTP status is 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bench Press"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.repetitions[0]").value(12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight[0]").value(80));
    }

}

