package br.com.rlag.alfabetiza.userservice.controller;

import br.com.rlag.alfabetiza.userservice.model.User;
import br.com.rlag.alfabetiza.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.firebase.auth.FirebaseAuthException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/google-auth")
    public ResponseEntity<?> registerOrLoginWithGoogle(@RequestBody String googleToken) {
        try {
            User user = userService.registerOrLoginUser(googleToken);
            return ResponseEntity.ok(user);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(401).body("Invalid Google token");
        }
    }

}
