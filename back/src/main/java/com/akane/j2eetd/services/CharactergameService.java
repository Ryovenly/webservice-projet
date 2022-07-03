package com.akane.j2eetd.services;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.entities.User;
import com.akane.j2eetd.exceptions.ResourceNotFoundException;
import com.akane.j2eetd.repositories.CharactergameRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
public class CharactergameService {

    @Autowired
    private CharactergameRepository charactergameRepository;

    private static final Logger logger = LoggerFactory.getLogger(CharactergameService.class);

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    public Charactergame createOrUpdate(Charactergame charactergame) {
        logger.info("Update or create user... {}", charactergame.getPseudo());
        return charactergameRepository.save(charactergame);
    }

    @RolesAllowed({ "ROLE_ADMIN" })
    public List<Charactergame> getAllCharactergames() {
        logger.info("Get all Charactergames...");
        return charactergameRepository.findAll();
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public Charactergame getCharactergameByPseudo(String pseudo) throws ResourceNotFoundException {
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Get Charactergame by pseudo... {}", pseudo);
            return charactergame.get();
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public Charactergame getCharactergameByUser(String username) throws ResourceNotFoundException {
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.checkUserCharacterGame(username));
        if (charactergame.isPresent()) {
            logger.info("Get Charactergame by user... {}", username);
            return charactergame.get();
        }
        throw new ResourceNotFoundException(Charactergame.class, username);
    }

    @RolesAllowed({ "ROLE_ADMIN" })
    @ApiResponse(responseCode = "404", description = "Le charactergame n'existe pas")
    public void deleteCharactergameByPseudo(String pseudo) throws ResourceNotFoundException {
        try{
            charactergameRepository.deleteById(pseudo);
            logger.info("Deleted charactergame... {}", pseudo);
        }catch(Exception e){
            throw new ResourceNotFoundException(Charactergame.class, pseudo);
        }

    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void setAmountMoney(String pseudo, Long amount) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update money {} by {}", pseudo, amount);
            charactergameRepository.setMoneyTransactionCharacter(pseudo, amount);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementHpMaxCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update HpMax {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementHpMaxCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementManaMaxCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update ManaMax {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementManaMaxCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementStaminaMaxCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update StaminaMax {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementStaminaMaxCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementHungerMaxCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update HungerMax {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementHungerMaxCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementHpCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Hp {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementHpCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementManaCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Mana {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementManaCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementStaminaCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Stamina {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementStaminaCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementHungerCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Hunger {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementHungerCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementStrengthCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Strength {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementStrengthCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementIntelligenceCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Intelligence {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementIntelligenceCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementAgilityCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Agility {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementAgilityCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    @ApiResponse(responseCode = "404", description = "Charactergame existe pas")
    public void updateIncrementLuckCharacter(String pseudo, Long valueIncrement) throws ResourceNotFoundException {
        getCharactergameByPseudo(pseudo);
        Optional<Charactergame> charactergame = Optional.ofNullable(charactergameRepository.findById(pseudo).orElse(null));
        if (charactergame.isPresent()) {
            logger.info("Update Luck {} by {}", pseudo, valueIncrement);
            charactergameRepository.incrementLuckCharacter(pseudo, valueIncrement);
            return;
        }
        throw new ResourceNotFoundException(Charactergame.class, pseudo);
    }

}
