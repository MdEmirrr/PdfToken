package com.tokenforpdf.token.service;
import com.tokenforpdf.token.dto.UserDto;
import com.tokenforpdf.token.model.User;
import com.tokenforpdf.token.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserDto addUser(UserDto dto) {
        User user = userRepository.save(modelMapper.map(dto, User.class));
        return modelMapper.map(user, UserDto.class);
    }

    public Optional<UserDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> modelMapper.map(u, UserDto.class));
    }


    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto updateUser(UserDto updatedUserDto) {
        Long userId = updatedUserDto.getId();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();

            updatedUser.setUserName(updatedUserDto.getUserName());
            updatedUser.setEmail(updatedUserDto.getEmail());
            updatedUser.setPassword(updatedUserDto.getPassword());

            User savedUser = userRepository.save(updatedUser);
            return modelMapper.map(savedUser, UserDto.class);
        } else {
            return null;
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }
}