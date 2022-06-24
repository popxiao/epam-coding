package com.epam.xiao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "person_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    @Id
    @Column(name = "person_id",length = 32)
    private String personId;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "member_type",length = 10)
    private String memberType;
}
