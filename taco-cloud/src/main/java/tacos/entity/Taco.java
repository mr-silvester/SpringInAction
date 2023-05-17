package tacos.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@RequiredArgsConstructor
public class Taco {
    private String name;
    private List<String> ingredients;
}
