package org.unibl.etf.master.security.auth.model.domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user")
public class User {

    @Id
    private long id;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;
}
