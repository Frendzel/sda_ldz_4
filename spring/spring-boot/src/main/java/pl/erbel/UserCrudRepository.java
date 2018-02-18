package pl.erbel;

import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Long> {

    User findUserByLogin(String login);

    User findUserByLoginOrFirstNameOrLastNameOrderByPassword(
            String login, String firstName, String password
    );

}
