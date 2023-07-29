    package com.tokenforpdf.token.controller;

    import com.tokenforpdf.token.dto.UserDto;
    import com.tokenforpdf.token.service.UserService;
    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/v1/user")
    @AllArgsConstructor

    public class UserController {

      private final UserService userService;

        @PostMapping("/add")
        public ResponseEntity<?> addUser(@RequestBody UserDto dto) {
            return new ResponseEntity<>(userService.addUser(dto), HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getUser(@PathVariable (name = "id" ) Long id) {
                return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }

        @PostMapping("/update/{id}")
        public ResponseEntity<?> updateUser(@RequestBody UserDto updatedUserDto, @RequestParam Long id) {
            updatedUserDto.setId(id);
            return new ResponseEntity<>(userService.updateUser(updatedUserDto), HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteUser(@RequestParam(name = "id") Long id) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User with ID " + id + " deleted successfully.", HttpStatus.OK);
        }


        @GetMapping("/all")
        public ResponseEntity<List<UserDto>> getAllUsers() {
            List<UserDto> allUsers = userService.getAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
    }
