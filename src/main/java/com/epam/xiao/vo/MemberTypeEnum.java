package com.epam.xiao.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public enum MemberTypeEnum {
    GOLD("01","Gold"),
    SILVER("02","Silver"),
    COPER("03","Coper")
    ;

     private String code;
     private String type;

     public static String getCodeByType(String code){
         if(StringUtils.isEmpty(code)){
             return null;
         }
         MemberTypeEnum[] values = MemberTypeEnum.values();
         for(int i =0;i< values().length;i++){
             if(code.equalsIgnoreCase(values[i].getCode())){
                 return values[i].getType();
             }
         }
         return null;
     }



}
