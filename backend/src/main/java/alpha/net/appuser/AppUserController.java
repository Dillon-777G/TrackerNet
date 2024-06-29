package alpha.net.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appusers")
public class AppUserController {

    @Autowired
    private AppUserService userService;
    
    @PostMapping("/")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        try {
            AppUser createdUser = userService.save(user);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUserByUsername(@PathVariable String username) {
        AppUser user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
        try {
            userService.deleteUserByUsername(username);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<AppUser>> getUsersByRole(@PathVariable String role) {
        return ResponseEntity.ok(userService.findByRolesContaining(role));
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<List<AppUser>> searchUsersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsernameContainingIgnoreCase(username));
    }
}
