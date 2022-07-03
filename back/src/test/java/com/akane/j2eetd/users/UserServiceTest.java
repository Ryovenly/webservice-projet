package com.akane.j2eetd.users;

import com.akane.j2eetd.entities.User;
import com.akane.j2eetd.exceptions.ResourceNotFormatException;
import com.akane.j2eetd.exceptions.ResourceNotFoundException;
import com.akane.j2eetd.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testAddUser() throws ResourceNotFormatException, ResourceNotFoundException {

        // Test case
        User user = new User();
        user.setUsername("test");
        User savedUser = userService.create(user);
        // Asserts
        assertThat(savedUser.getUsername()).isNotNull();
        // Clean
        userService.deleteUser(savedUser.getUsername());

    }

//    @Test
//    public void testUpdateSortie() {
//// Test case
//        Sortie sortie = new Sortie();
//        sortie.setName("Sortie test");
//        Sortie savedSortie = sortieService.createOrUpdate(sortie);
//        savedSortie.setName("Sortie test 2");
//        Sortie savedSortie2 = sortieService.createOrUpdate(savedSortie);
//// Asserts
//        assertThat(savedSortie2.getName()).isEqualTo("Sortie test 2");
//        assertThat(savedSortie.getId()).isEqualTo(savedSortie2.getId());
//// Clean
//        sortieService.deleteSortieById(savedSortie.getId());
//    }

}
