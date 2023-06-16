package com.bowling.rolling.controller;

import com.bowling.rolling.model.restaurants.domain.Restaurant;
import com.bowling.rolling.model.restaurants.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class RestaurantControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Mock
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    void testCreateRestaurant() throws Exception {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setId(4L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");

        String json = objectMapper.writeValueAsString(restaurant);

        // When
        mockMvc.perform(post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.name").value("Test Restaurant"))
                .andExpect(jsonPath("$.address").value("123 Test Street"))
                .andExpect(jsonPath("$.phoneNumber").value("123-456-7890"));

        // Then
        verify(restaurantService).createRestaurant(any(Restaurant.class));
    }

    @Test
    void testGetRestaurantById() throws Exception {
        // Given
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setAddress("123 Test Street");
        restaurant.setPhoneNumber("123-456-7890");

        given(restaurantService.getRestaurantById(1L)).willReturn(restaurant);

        // When
        mockMvc.perform(get("/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Restaurant"))
                .andExpect(jsonPath("$.address").value("123 Test Street"))
                .andExpect(jsonPath("$.phoneNumber").value("123-456-7890"));

        // Then
        verify(restaurantService).getRestaurantById(1L);
    }

    @Test
    void testUpdateRestaurant() throws Exception {
        // Given
        Restaurant existingRestaurant = new Restaurant();
        existingRestaurant.setId(1L);
        existingRestaurant.setName("Existing Restaurant");
        existingRestaurant.setAddress("123 Existing Street");
        existingRestaurant.setPhoneNumber("123-456-7890");

        Restaurant updatedRestaurant = new Restaurant();
        updatedRestaurant.setId(1L);
        updatedRestaurant.setName("Updated Restaurant");
        updatedRestaurant.setAddress("456 Updated Street");
        updatedRestaurant.setPhoneNumber("987-654-3210");

        given(restaurantService.getRestaurantById(1L)).willReturn(existingRestaurant);
        given(restaurantService.updateRestaurant(any(Restaurant.class))).willReturn(updatedRestaurant);

        // When
        mockMvc.perform(put("/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Updated Restaurant\", \"address\": \"456 Updated Street\", \"phoneNumber\": \"987-654-3210\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Restaurant"))
                .andExpect(jsonPath("$.address").value("456 Updated Street"))
                .andExpect(jsonPath("$.phoneNumber").value("987-654-3210"));

        // Then
        verify(restaurantService).getRestaurantById(1L);
        verify(restaurantService).updateRestaurant(any(Restaurant.class));
    }

    @Test
    void testDeleteRestaurant() throws Exception {
        // Given
        given(restaurantService.deleteRestaurantById(1L)).willReturn(true);

        // When
        mockMvc.perform(delete("/restaurants/1"))
                .andExpect(status().isNoContent());

        // Then
        verify(restaurantService).deleteRestaurantById(1L);
    }
}