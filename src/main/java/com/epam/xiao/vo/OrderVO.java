package com.epam.xiao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {

    @NotBlank(message = "personId can not bu null,please input!")
    @Length(max = 32, message = "personId exceeds the limit!")
    private String personId;

    @Length(max = 50, message = "orderName exceeds the limit!")
    private String orderName;

    @Digits(integer = 9, fraction=2, message = "amount format is not correct!")
    @DecimalMin(value = "0.00", message = "amount format is not correct!")
    @NotNull(message = "order amount can not bu null,please input!")
    private BigDecimal amount;

}
