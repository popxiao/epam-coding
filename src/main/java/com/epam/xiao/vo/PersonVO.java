package com.epam.xiao.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
public class PersonVO {

    @NotBlank(message = "personId can not bu null,please input!")
    @Length(max = 32, message = "personId exceeds the limit!")
    private String personId;

    @Length(max = 100, message = "person name exceeds the limit!")
    private String name;

    private String memberType;


}
