package ru.sberdorofeev.bankapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "USERS_INFO")
public class UsersEntity {

    @Column(name = "ID")
    @Id
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="middle_name")
    private String middleName;
    @Column(name="passport_series")
    private String passportSeries;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "create_date_user")
    private Timestamp createDateUser;
}
