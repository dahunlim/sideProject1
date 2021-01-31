package exitProject.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class mainResponseDto {

    private final String name;
    private final int amount;

}
