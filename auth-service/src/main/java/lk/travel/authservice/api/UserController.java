package lk.travel.authservice.api;

import lk.travel.authservice.dto.UserDTO;
import lk.travel.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @GetMapping(path = "search/email", params = "email")
    public ResponseEntity<UserDTO> searchByEmailUser(@RequestParam String email) {
        UserDTO userDTO = userService.searchByEmailUser(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<UserDTO> searchByIDUser(@RequestParam int userID) {
        return ResponseEntity.ok(userService.searchUser(userID));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

}
