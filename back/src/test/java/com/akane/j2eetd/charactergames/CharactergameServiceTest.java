package com.akane.j2eetd.charactergames;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.entities.User;
import com.akane.j2eetd.exceptions.ResourceNotFormatException;
import com.akane.j2eetd.exceptions.ResourceNotFoundException;
import com.akane.j2eetd.services.CharactergameService;
import com.akane.j2eetd.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
public class CharactergameServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CharactergameService charactergameService;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testAddCharacter() throws ResourceNotFormatException, ResourceNotFoundException {

        // Test case
        // create user
        User user = new User();
        user.setUsername("test");
        User savedUser = userService.create(user);

        // create charactergame

        Charactergame charactergame = new Charactergame();
        charactergame.setPseudo("testCharacter");
        charactergame.setUser(user);

        Charactergame savedCharactergame = charactergameService.createOrUpdate(charactergame);

        // Asserts
        assertThat(savedCharactergame.getPseudo()).isNotNull();
        // Clean
        userService.deleteUser(savedUser.getUsername());
        charactergameService.deleteCharactergameByPseudo(savedCharactergame.getPseudo());

    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testIncrementCharacter() throws ResourceNotFormatException, ResourceNotFoundException {

        // Test case
        // create user
        User user = new User();
        user.setUsername("test");
        User savedUser = userService.create(user);

        // create charactergame

        Charactergame charactergame = new Charactergame();
        charactergame.setPseudo("testCharacter");
        charactergame.setUser(user);
        charactergame.setLuck(0L);

        Charactergame savedCharactergame = charactergameService.createOrUpdate(charactergame);

        charactergameService.updateIncrementLuckCharacter("testCharacter", 10L);

        savedCharactergame = charactergameService.getCharactergameByPseudo("testCharacter");

        // Asserts
        assertThat(savedCharactergame.getLuck()).isEqualTo(10L);
        // Clean
        userService.deleteUser(savedUser.getUsername());
        charactergameService.deleteCharactergameByPseudo(savedCharactergame.getPseudo());

    }

}
