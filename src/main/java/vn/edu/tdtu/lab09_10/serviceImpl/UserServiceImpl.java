package vn.edu.tdtu.lab09_10.serviceImpl;

import org.springframework.stereotype.Service;
import vn.edu.tdtu.lab09_10.model.User;
import vn.edu.tdtu.lab09_10.repository.UserRepository;
import vn.edu.tdtu.lab09_10.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username) { return userRepository.findByUsername(username);}

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
