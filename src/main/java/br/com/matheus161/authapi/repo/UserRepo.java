package br.com.matheus161.authapi.repo;

import br.com.matheus161.authapi.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepo extends ListCrudRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
