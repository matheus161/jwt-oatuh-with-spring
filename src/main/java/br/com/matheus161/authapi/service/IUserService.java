package br.com.matheus161.authapi.service;

import br.com.matheus161.authapi.model.User;
import br.com.matheus161.authapi.security.MyToken;

public interface IUserService {
    public User addUser(User user);
    public User getByUsername(String username);
    public MyToken userLogin(User user);
}
