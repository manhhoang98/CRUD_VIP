package codegym.config.repository;

import codegym.config.model.Account;
import codegym.config.model.Roles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IRolesRepo extends PagingAndSortingRepository<Roles,Long> {

}
