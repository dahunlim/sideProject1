package exitProject.web.dto;

import exitProject.web.mainResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class mainResponseDtoTest {

    @Test
    public void 롬복_테스트() {
        String name = "Test";
        int amount = 1000;

        mainResponseDto dto = new mainResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
