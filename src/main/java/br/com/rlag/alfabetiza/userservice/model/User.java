package br.com.rlag.alfabetiza.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String firebaseUid;
    private String name;
    private String email;
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "json")
    private String accessibilityPreferences;

    // Getters and setters

    public enum Role {
        ALUNO, ADMINISTRADOR
    }
}
