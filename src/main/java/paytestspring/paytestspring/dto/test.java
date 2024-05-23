package paytestspring.paytestspring.dto;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Valid
public class test {

    private String loginId;
    private String password;
}