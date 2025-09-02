package br.com.matheus161.authapi.service;

import br.com.matheus161.authapi.model.User;

public interface IUserService {
    public User addUser(User user);
    public User getByUsername(String username);
}
