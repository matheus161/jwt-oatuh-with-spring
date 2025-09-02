package br.com.matheus161.authapi.service;

import br.com.matheus161.authapi.model.User;
import br.com.matheus161.authapi.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }
}
