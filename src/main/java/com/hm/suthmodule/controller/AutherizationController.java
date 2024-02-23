package com.hm.suthmodule.controller;

import com.hm.authmodule.repository.UserRepository;
import com.hm.suthmodule.controller.utils.AuthUtils;
import com.hm.suthmodule.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutherizationController {

    private  UserRepository userRepository;
    @Autowired
    private AuthUtils authUtils;

public ResponseEntity<String> handlePostRequest(@RequestBody User user, @RequestHeader("Autherization") String authToken){
boolean tokenValidation= AuthUtils.isValidAuthToken(authToken);
    if(!tokenValidation){
return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid authentication token");
}
    AuthUtils authUtios=new AuthUtils(userRepository);

if(authUtios.processData(user)){
return ResponseEntity.ok("Success");
}else{
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure");
}
}

}
