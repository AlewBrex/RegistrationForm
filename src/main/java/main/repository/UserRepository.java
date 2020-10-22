package main.repository;

import main.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
    User save(User user);
    Boolean findByLogin(String login);
    Boolean findByEmail(String email);
    Boolean findByPassword(String password);
    Boolean findByFullName(String surname, String name, String patronymic);
}