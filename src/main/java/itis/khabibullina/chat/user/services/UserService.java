package itis.khabibullina.chat.user.services;

import itis.khabibullina.chat.user.models.User;
import itis.khabibullina.chat.user.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getUser() {
        String name = "";
        User user = new User(name);
        return usersRepository.save(user);
    }
}
