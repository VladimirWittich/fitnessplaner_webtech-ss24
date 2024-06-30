package htwberlin.webtech.ss24.fitnessplaner;

import htwberlin.webtech.ss24.fitnessplaner.model.PersonData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BMICalculatorController.class)
public class BMICalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BMICalculatorController bmiCalculatorController;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetPersonData() throws Exception {
        PersonData personData = new PersonData(178, 90, "male");
        when(bmiCalculatorController.getPersonData()).thenReturn(List.of(personData));

        mockMvc.perform(get("/bmicalculator"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].height").value(178))
                .andExpect(jsonPath("$[0].weight").value(90))
                .andExpect(jsonPath("$[0].gender").value("male"));
    }
}
