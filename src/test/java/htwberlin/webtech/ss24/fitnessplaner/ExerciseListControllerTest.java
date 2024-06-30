package htwberlin.webtech.ss24.fitnessplaner;

import htwberlin.webtech.ss24.fitnessplaner.model.Exercise;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

@WebMvcTest(ExerciseListController.class)
public class ExerciseListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseListController exerciseListController;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetExercise() throws Exception {
        List<Exercise> exercises = List.of(
                new Exercise("Example", "vladimirwittich@web.de", 3, List.of(12, 10, 8), List.of(100, 100, 100), 3000, LocalDate.now()));


        when(exerciseListController.getExercise()).thenReturn(exercises);

        mockMvc.perform(get("/workoutplan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Example"))
                .andExpect(jsonPath("$[0].owner").value("vladimirwittich@web.de"))
                .andExpect(jsonPath("$[0].sets").value(3))
                .andExpect(jsonPath("$[0].totalWeight").value(3000));
    }
}
