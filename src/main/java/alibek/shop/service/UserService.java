package alibek.shop.service;

import alibek.shop.entity.Users;
import alibek.shop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public Users getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return usersRepository.findByLogin(authentication.getName()).orElse(null);
    }

    public Users getProfile(Long userId) {
        return usersRepository.findUserById(userId);
    }
}
