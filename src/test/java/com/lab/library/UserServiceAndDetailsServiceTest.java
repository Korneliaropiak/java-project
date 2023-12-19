package com.lab.library;

import com.lab.library.domain.Role;
import com.lab.library.domain.User;
import com.lab.library.domain.UserDto;
import com.lab.library.repository.RoleRepository;
import com.lab.library.repository.UserRepository;
import com.lab.library.security.CustomUserDetailsService;
import com.lab.library.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceAndDetailsServiceTest {

    private UserRepository userRepository = mock(UserRepository.class);
    private RoleRepository roleRepository = mock(RoleRepository.class);
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private UserServiceImpl userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
    private CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepository);

    // Tests for UserServiceImpl

    @Test
    void saveUserTest() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john@example.com");
        userDto.setPassword("password");

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(userRole);

        userService.saveUser(userDto);

        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void findUserByEmailTest() {
        User mockUser = new User();
        mockUser.setEmail("john@example.com");
        when(userRepository.findByEmail("john@example.com")).thenReturn(mockUser);

        User result = userService.findUserByEmail("john@example.com");

        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void findAllUsersTest() {
        User mockUser = new User();
        mockUser.setName("John Doe");
        mockUser.setEmail("john@example.com");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(mockUser));

        List<UserDto> userDtos = userService.findAllUsers();

        assertEquals(1, userDtos.size());
        assertEquals("John", userDtos.get(0).getFirstName());
        assertEquals("Doe", userDtos.get(0).getLastName());
        assertEquals("john@example.com", userDtos.get(0).getEmail());
    }

    // Tests for CustomUserDetailsService

    @Test
    void loadUserByUsernameUserNotFoundTest() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonexistent@example.com");
        });
    }
}
