package project.sgs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.sgs.Entity.Roles;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Roles findRolesByName(String name);
}
