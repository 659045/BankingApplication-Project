package nl.inholland.bankingapplication.controllers;

import nl.inholland.bankingapplication.models.UserAccount;
import nl.inholland.bankingapplication.models.dto.LoginDTO;
import nl.inholland.bankingapplication.services.UserAccountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    private UserAccountService userService;

    public LoginController(UserAccountService userService) {
        this.userService = userService;
    }
    //makes it a post request and the login responds to any post request to the login endpoint
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        //request body to access it
        UserAccount user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

    }
}
