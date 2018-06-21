package com.developerstack.controller;

import com.developerstack.config.JWTgenerator;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
public class TokenController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String dashboard() {
        return "welcome";
    }

    @RequestMapping(value = "/auth/generate", method = RequestMethod.GET)
    public String generateToken(ModelMap model, @RequestParam String username,@RequestParam String password) {
        JWTgenerator jwTgenerator = new JWTgenerator();
        String token;
        try {
            token = jwTgenerator.createJWT(username, 1000, password);
        } catch (UnsupportedEncodingException e) {
            return "display";
        }
        model.addAttribute("message", ("Hello Spring MVC !!! the token is:"+token));
        return "display";
    }

    @RequestMapping(value = "/auth/validate", method = RequestMethod.GET)
    public String tokenValidate(ModelMap model, @RequestParam String password,@RequestParam String jwtToken) {
        JWTgenerator jwTgenerator = new JWTgenerator();
        Claims tokenClaim;
        tokenClaim = jwTgenerator.parseJWT(jwtToken, password);
        if (tokenClaim.isEmpty()){
            return "display";
        }
        model.addAttribute("message", ("Hello Spring MVC !!! the token is:"+tokenClaim.getIssuer()));
        return "display";
    }


}
