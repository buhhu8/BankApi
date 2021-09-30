package ru.sberdorofeev.bankapi.model.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "USERS_INFO")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name="MIDDLE_NAME")
    private String middleName;

    @Column(name="PASSPORT_SERIES")
    private String passportSeries;

    @Column(name = "PASSPORT_NUMBER")
    private String passportNumber;

    @Column(name = "CREATE_DATA_USER")
    private LocalDate createDateUser;

}
