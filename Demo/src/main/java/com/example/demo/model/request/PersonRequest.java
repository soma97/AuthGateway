package com.example.demo.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class PersonRequest {
    @Size(min = 3, max = 30)
    private String name;
    @Size(min = 3, max = 45)
    private String surname;
}
