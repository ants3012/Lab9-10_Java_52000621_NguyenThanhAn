package vn.edu.tdtu.lab09_10.service;

import vn.edu.tdtu.lab09_10.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User saveUser(User user);

    public User getUserById(Integer id);

    public User getUserByUsername(String username);

    public User updateUser(User user);

    void deleteUserById(Integer id);
}
