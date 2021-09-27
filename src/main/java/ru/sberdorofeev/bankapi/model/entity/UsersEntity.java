package ru.sberdorofeev.bankapi.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@Entity
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
    private Timestamp createDateUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<InvoiceEntity> invoices;

}
