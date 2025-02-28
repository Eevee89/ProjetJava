package org.example.Services;

import com.google.gson.Gson;
import org.example.Entities.AuthHeader;
import org.example.Repositories.PatientRepository;
import org.example.Repositories.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private AuthService authService;

    private Gson gson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gson = new Gson();
    }

    @Test
    void authentify_validUserCredentials() {
        // Given
        AuthHeader authHeader = new AuthHeader();
        authHeader.email = "test@example.com";
        authHeader.password = "password123";
        authHeader.role = "USER";
        String jsonString = gson.toJson(authHeader);

        when(patientRepository.countByEmail("test@example.com")).thenReturn(1);
        when(patientRepository.findPasswordWithEmail("test@example.com")).thenReturn("password123");

        // When
        boolean result = authService.authentify(jsonString);

        // Then
        assertTrue(result, "Authentication should succeed with valid credentials");
        verify(patientRepository, times(1)).countByEmail("test@example.com");
        verify(patientRepository, times(1)).findPasswordWithEmail("test@example.com");
    }

    @Test
    void authentify_invalidUserEmail() {
        // Given
        AuthHeader authHeader = new AuthHeader();
        authHeader.email = "invalid@example.com";
        authHeader.password = "password123";
        authHeader.role = "USER";
        String jsonString = gson.toJson(authHeader);

        when(patientRepository.countByEmail("invalid@example.com")).thenReturn(0);

        // When
        boolean result = authService.authentify(jsonString);

        // Then
        assertFalse(result, "Authentication should fail with an invalid email");
        verify(patientRepository, times(1)).countByEmail("invalid@example.com");
        verify(patientRepository, never()).findPasswordWithEmail(anyString());
    }

    @Test
    void authentify_invalidUserPassword() {
        // Given
        AuthHeader authHeader = new AuthHeader();
        authHeader.email = "test@example.com";
        authHeader.password = "wrongPassword";
        authHeader.role = "USER";
        String jsonString = gson.toJson(authHeader);

        when(patientRepository.countByEmail("test@example.com")).thenReturn(1);
        when(patientRepository.findPasswordWithEmail("test@example.com")).thenReturn("password123");

        // When
        boolean result = authService.authentify(jsonString);

        // Then
        assertFalse(result, "Authentication should fail with an invalid password");
        verify(patientRepository, times(1)).countByEmail("test@example.com");
        verify(patientRepository, times(1)).findPasswordWithEmail("test@example.com");
    }

    @Test
    void authentify_invalidRole() {
        // Given
        AuthHeader authHeader = new AuthHeader();
        authHeader.email = "test@example.com";
        authHeader.password = "password123";
        authHeader.role = "ADMIN";
        String jsonString = gson.toJson(authHeader);

        // When
        boolean result = authService.authentify(jsonString);

        // Then
        assertFalse(result, "Authentication should fail with an invalid role");
        verify(patientRepository, never()).countByEmail(anyString());
        verify(patientRepository, never()).findPasswordWithEmail(anyString());
    }

    @Test
    void authentify_nullJsonString() {
        // Given
        String jsonString = null;

        // When
        boolean result = authService.authentify(jsonString);

        // Then
        assertFalse(result, "Authentication should fail with a null JSON string");
    }

    @Test
    void authentify_emptyJsonString() {
        // Given
        String jsonString = "";

        // When
        boolean result = authService.authentify(jsonString);

        // Then
        assertFalse(result, "Authentication should fail with an empty JSON string");
    }

    @Test
    void authentify_malformedJsonString() {
        // Given
        String jsonString = "{email:\"test@example.com\",password:\"password123\",role:\"USER\"}";

        // When
        boolean result = authService.authentify(jsonString);

        // Then
        assertFalse(result, "Authentication should fail with a malformed JSON string");
    }
}