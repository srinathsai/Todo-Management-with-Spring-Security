package net.javaguides.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// User entity for the db to remember whether valid person is accessing the db or not.
    private long id;
    private String name;
    @Column(nullable = false, unique =true)
    private String username;
    @Column(nullable = false, unique =true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch= FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private Set<Role> roles;


}

//@ManyToMany and @JoinTable , @JoinColumns are used to merge user and role tables and combinedly together for sending DB.


