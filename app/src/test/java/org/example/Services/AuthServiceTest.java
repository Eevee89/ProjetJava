package org.example.Services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import org.example.Repositories.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.example.Entities.Staff;


class AuthServiceTest {

    @Mock
    private StaffRepository staffRepository; // Simule le repo

    @InjectMocks
    private AuthService authService; // Injecte le mock dans AuthService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    void testIsSuperAdmin() {
        String adminJson = "{\"email\":\"admin@example.com\", \"role\":\"STAFF\"}";

        // Simule un staff avec privilège 0 (Super Admin)
        Staff mockStaff = new Staff();
        mockStaff.setPrivilege(0);
        when(staffRepository.findStaffByEmail("admin@example.com")).thenReturn(mockStaff);

        boolean result = authService.isSuperAdmin(adminJson);

        assertTrue(result, "L'utilisateur doit être reconnu comme Super Admin");
    }

    @Test
    void testIsNotSuperAdmin() {
        // Un utilisateur qui n'est pas Super Admin
        String userJson = "{\"email\":\"user@example.com\", \"role\":\"STAFF\"}";

        // Simuler un utilisateur STAFF avec un privilège 1 (Admin, pas Super Admin)
        Staff mockStaff = new Staff();
        mockStaff.setPrivilege(1); // Admin, mais pas Super Admin (0)

        when(staffRepository.findStaffByEmail("user@example.com")).thenReturn(mockStaff);

        boolean result = authService.isSuperAdmin(userJson);

        assertFalse(result, "L'utilisateur ne doit pas être reconnu comme Super Admin");
    }
}
