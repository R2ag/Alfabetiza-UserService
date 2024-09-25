package br.com.rlag.alfabetiza.userservice.service;

import br.com.rlag.alfabetiza.userservice.model.User;
import br.com.rlag.alfabetiza.userservice.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerOrLoginUser(String googleToken) throws FirebaseAuthException {
        // Verifica o token com Firebase
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(googleToken);
        String firebaseUid = decodedToken.getUid();

        // Verifica se o usuário já existe
        Optional<User> existingUser = userRepository.findByFirebaseUid(firebaseUid);

        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        // Se não existir, cria um novo usuário
        User newUser = new User();
        newUser.setFirebaseUid(firebaseUid);
        newUser.setEmail(decodedToken.getEmail());
        newUser.setName(decodedToken.getName());
        newUser.setProfileImageUrl(decodedToken.getPicture());
        newUser.setRole(User.Role.ALUNO); // Papel padrão é aluno
        return userRepository.save(newUser);
    }
}
