package pl.rybialek.spotifyapi.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfo {

    @GetMapping("/hello")
    public Principal hello(Principal principal) {
        return principal;
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
