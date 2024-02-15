package jpabook.jpashop2.Contoller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "횡원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
