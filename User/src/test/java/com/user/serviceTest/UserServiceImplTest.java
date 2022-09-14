package com.user.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.exception.UserNotFoundException;
import com.user.models.User;
import com.user.repository.UserRepository;
import com.user.service.Impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceImplTest.class)
class UserServiceImplTest {

	@Mock
	User user;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	void addUserTest() {
		userServiceImpl.addUser(user);
		verify(userRepository, times(1)).save(any());
	}

	@Test
	void updateUserTest() {
		user = new User();
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
		userServiceImpl.updateUser(user);
		verify(userRepository, times(1)).save(any());
	}

//	@Test
//	void viewAllUser() {
//		Mockito.when(userRepository.findAll()).thenReturn(Stream.of(user).collect(Collectors.toList()));
//		assertEquals(1, userServiceImpl.viewUser().size());
//	}
//
//	@Test
//	void viewAllUserException() {
//		Mockito.when(userRepository.findAll()).thenReturn(null);
//		Exception exception = assertThrows(UserNotFoundException.class, () -> {
//			userServiceImpl.viewUser();
//		});
//		String expectedMessage = "User not found";
//		String actualMessage = exception.getMessage();
//		assertTrue(actualMessage.contains(expectedMessage));
//	}

	@Test
	void viewUserTest() {
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
		userServiceImpl.viewUser(any());
		verify(userRepository, times(1)).findById(any());
	}

	@Test
	void viewUserExceptionTest() {
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			userServiceImpl.viewUser(any());
		});
		String expectedMessage = "No such User";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void deleteOrderTest() {
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));
		String string = userServiceImpl.deleteUser(any());
		verify(userRepository, times(1)).deleteById(any());
		assertEquals("Deleted Successfully", string);

	}

	@Test
	void deleteUserNotFoundTest() {
		Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());
		String string = userServiceImpl.deleteUser(any());
		assertEquals("User Not found", string);
	}

}
