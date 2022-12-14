package com.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.admin.exception.AuthenticationFailedException;
import com.admin.exception.UserNotFoundException;
import com.admin.pojo.Admin;
import com.admin.repository.AdminRepository;

@SpringBootTest
public class AdminServiceTest {

	@InjectMocks
	private AdminService adminService = new AdminServiceImpl();

	@Mock
	private AdminRepository adminRepository;
	
	@Test
	public void testGetAdminById() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		Optional<Admin> optionalAdmin = Optional.of(admin);
		when(adminRepository.findById(10)).thenReturn(optionalAdmin);
		
		Admin myAdmin = adminService.getAdminById(10);
		assertEquals("ilavas", myAdmin.getUserName());
	}
	
	@Test
	public void testGetAdminByIdWithException() {
		
		when(adminRepository.findById(10)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> adminService.getAdminById(10));
	}
	
	@Test
	public void testGetAllAdmin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		Admin admin1 = new Admin();
		admin1.setAdminId(11);
		admin1.setFirstName("keshu");
		admin1.setLastName("s");
		admin1.setPhone(454545);
		admin1.setLocation("chennai");
		admin1.setUserName("kesav");
		admin1.setEmail("keshu@mail.com");
		admin1.setPassword("454545");
		
		Admin admin2 = new Admin();
		admin2.setAdminId(12);
		admin2.setFirstName("harshitha");
		admin2.setLastName("g");
		admin2.setPhone(191919);
		admin2.setLocation("avadi");
		admin2.setUserName("harshi");
		admin2.setEmail("harshi@mail.com");
		admin2.setPassword("191919");
		
		List<Admin> allAdmins = new ArrayList<>();
		allAdmins.add(admin);
		allAdmins.add(admin1);
		allAdmins.add(admin2);
		
		when(adminRepository.findAll()).thenReturn(allAdmins);
		List<Admin> admins = adminService.getAllAdmin();
		assertEquals(3, admins.size());
	}
	
	@Test
	public void testGetAllAdminByLocation() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		Admin admin1 = new Admin();
		admin1.setAdminId(11);
		admin1.setFirstName("keshu");
		admin1.setLastName("s");
		admin1.setPhone(454545);
		admin1.setLocation("chennai");
		admin1.setUserName("kesav");
		admin1.setEmail("keshu@mail.com");
		admin1.setPassword("454545");
		
		Admin admin2 = new Admin();
		admin2.setAdminId(12);
		admin2.setFirstName("harshitha");
		admin2.setLastName("g");
		admin2.setPhone(191919);
		admin2.setLocation("avadi");
		admin2.setUserName("harshi");
		admin2.setEmail("harshi@mail.com");
		admin2.setPassword("191919");
		
		List<Admin> allAdmins = new ArrayList<>();
		allAdmins.add(admin);
		allAdmins.add(admin1);
		allAdmins.add(admin2);
		
		when(adminRepository.findByLocation("bangalore")).thenReturn(allAdmins);
		List<Admin> adminBylocation = adminService.getAllAdminByLocation("bangalore");
		assertEquals(3, adminBylocation.size());
		
	}
	
	@Test
	public void testGetAllAdminByLocationWithException() {
		
		when(adminRepository.findByLocation("bangalore")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> adminService.getAllAdminByLocation("bangalore"));
	}
	
	@Test
	public void testGelAdminByUserName() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		Optional<Admin> optionalAdmin = Optional.of(admin);
		when(adminRepository.findByUserName("ilavas")).thenReturn(optionalAdmin);
		
		adminService.getAllAdminByUserName("ilavas");
		assertEquals("ilavas", admin.getUserName());
	}
	
	@Test
	public void testAdminByUserNameWithException() {
		
		when(adminRepository.findByUserName("ilavas")).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> adminService.getAllAdminByUserName("ilavas"));
	}
	
	@Test 
	public void testGetAdminByPhone() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		Optional<Admin> optionalAdmin = Optional.of(admin);
		when(adminRepository.findByPhone(998078)).thenReturn(optionalAdmin);
		
		adminService.getAllAdminByPhone(998078);
		assertEquals(998078, admin.getPhone());
	}
	
	@Test
	public void testGetAdminByPhoneWithException() {
		
		when(adminRepository.findByPhone(998078)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> adminService.getAllAdminByPhone(998078));
	}
	
	@Test
	public void testSaveAdmin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		when(adminRepository.save(admin)).thenReturn(admin);
		Admin newAdmin = adminService.saveAdmin(admin);
		assertEquals("ilavas", newAdmin.getUserName());
		verify(adminRepository,times(1)).save(admin);
	}
	
	@Test
	public void testUpdateAdmin() {
		

		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		
		Optional<Admin> optionalAdmin = Optional.of(admin);
		when(adminRepository.findById(10)).thenReturn(optionalAdmin);
		adminService.updateAdmin(admin);
		verify(adminRepository,times(1)).findById(10);
		verify(adminRepository,times(1)).save(admin);
	}
	
	@Test
	public void testUpdateAdminWithException() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		when(adminRepository.findById(11)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> adminService.updateAdmin(admin));
	}
	
	@Test
	public void testDeleteAdminById() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setFirstName("ilavarasi");
		admin.setLastName("s");
		admin.setPhone(998078);
		admin.setLocation("bangalore");
		admin.setUserName("ilavas");
		admin.setEmail("ila@mail.com");
		admin.setPassword("12345");
		
		Optional<Admin> optionalAdmin = Optional.of(admin);
		when(adminRepository.findById(10)).thenReturn(optionalAdmin);
		adminService.deleteAdminById(10);
		verify(adminRepository,times(1)).findById(10);
		verify(adminRepository,times(1)).deleteById(10);
	}
	
	@Test
	public void testDeleteAdminByIdWithException() {
		
		when(adminRepository.findById(11)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class, () -> adminService.deleteAdminById(11));
	}
	
	@Test
	public void testDoLogin() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setUserName("ilavas");
		admin.setPassword("12345");
		
		when(adminRepository.login(admin.getUserName(), admin.getPassword())).thenReturn(admin);
		assertEquals("ilavas", admin.getUserName());
		admin = adminService.doLogin(admin.getUserName(), admin.getPassword());
		verify(adminRepository,times(1)).login(admin.getUserName(), admin.getPassword());
	}

	@Test
	public void testDoLoginWithException() {
		
		Admin admin = new Admin();
		admin.setAdminId(10);
		admin.setUserName("ilavas");
		admin.setPassword("12345");
		
		when(adminRepository.login(admin.getUserName(), admin.getPassword())).thenThrow(AuthenticationFailedException.class);
		assertThrows(AuthenticationFailedException.class, () -> adminService.doLogin(admin.getUserName(), admin.getPassword()));
	}

}
