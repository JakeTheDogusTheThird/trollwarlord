package com.example.minibank.transfer;

import com.example.common.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "license_id", nullable = false, unique = true)
    private String licenseId; // TODO store as hash

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password; // TODO store as hash

    @Column(nullable = false, unique = true)
    private String email;

    @Builder
    public User(Integer id, String licenseId, String name, String surname, String password, String email) {
        this.id = id;
        this.licenseId = licenseId;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

}
