package com.akane.j2eetd.services;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.entities.Characteristic;
import com.akane.j2eetd.repositories.CharactergameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CharactergameService {

    @Autowired
    private CharactergameRepository charactergameRepository;
    @Autowired
    private UserService userService;

    public Charactergame createOrUpdate(Charactergame charactergame) {
        return charactergameRepository.save(charactergame);
    }

    public List<Charactergame> getAllCharactergames() {
        return charactergameRepository.findAll();
    }

    public Charactergame getCharactergameByPseudo(String pseudo) {
        return charactergameRepository.findById(pseudo).orElse(null);
    }

    public Charactergame getCharactergameByUser(String username) {
        return charactergameRepository.checkUserCharacterGame(username);
    }

    public void deleteCharactergameByPseudo(String pseudo) {
        charactergameRepository.deleteById(pseudo);
    }

    public void setAmountMoney(String pseudo, Long amount){
        charactergameRepository.setMoneyTransactionCharacter(pseudo, amount);
    }

    public void updateIncrementHpMaxCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementHpMaxCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementManaMaxCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementManaMaxCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementStaminaMaxCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementStaminaMaxCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementHungerMaxCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementHungerMaxCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementHpCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementHpCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementManaCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementManaCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementStaminaCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementStaminaCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementHungerCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementHungerCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementStrengthCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementStrengthCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementIntelligenceCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementIntelligenceCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementAgilityCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementAgilityCharacter(pseudo, valueIncrement);
    }

    public void updateIncrementLuckCharacter(String pseudo, Long valueIncrement){
        charactergameRepository.incrementLuckCharacter(pseudo, valueIncrement);
    }

//    public void incrementCharacteristicCharacter(String pseudo, String characteristic, Long amount){
//        charactergameRepository.incrementCharacteristicCharacter(pseudo,characteristic, amount);
//    }

//    public void assignUser(){
//        return charactergameRepository.save()
//    }

}
