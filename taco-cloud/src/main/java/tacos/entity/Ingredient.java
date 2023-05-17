package tacos.entity;

import lombok.*;

@Data
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIM, VEGGIES, CHEESE, SAUSE
    }
}
