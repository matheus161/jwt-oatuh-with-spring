package br.com.matheus161.authapi.service;

import br.com.matheus161.authapi.model.User;
import br.com.matheus161.authapi.repo.UserRepo;
import br.com.matheus161.authapi.security.MyToken;
import br.com.matheus161.authapi.security.TokenUtil;
import org.antlr.v4.runtime.Token;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public MyToken userLogin(User user) {
        User storedUser = repo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(user.getPassword(), storedUser.getPassword())) {
            return TokenUtil.encode(storedUser);
        }

        throw new RuntimeException("Invalid username or password");
    }
}
