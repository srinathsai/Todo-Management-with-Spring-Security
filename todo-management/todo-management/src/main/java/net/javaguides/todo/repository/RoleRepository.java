package net.javaguides.todo.repository;

import net.javaguides.todo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface RoleRepository  extends JpaRepository<Role,Long> {

}
