package com.akane.j2eetd.repositories;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.entities.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.transaction.Transactional;

@Repository
public interface CharactergameRepository extends JpaRepository<Charactergame,String> {

    @Query("SELECT c FROM Charactergame c where c.user.username = ?1")
    Charactergame checkUserCharacterGame(String username);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.money = c.money + ?2 where c.pseudo = ?1")
    void setMoneyTransactionCharacter(String pseudo, Long moneyTransaction);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.hpMax = c.hpMax + ?2 where c.pseudo = ?1")
    void incrementHpMaxCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.manaMax = c.manaMax + ?2 where c.pseudo = ?1")
    void incrementManaMaxCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.hungerMax = c.hungerMax + ?2 where c.pseudo = ?1")
    void incrementHungerMaxCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.staminaMax = c.staminaMax + ?2 where c.pseudo = ?1")
    void incrementStaminaMaxCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.hp = c.hp + ?2 where c.pseudo = ?1")
    void incrementHpCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.mana = c.mana + ?2 where c.pseudo = ?1")
    void incrementManaCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.stamina = c.stamina + ?2 where c.pseudo = ?1")
    void incrementStaminaCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.hunger = c.hunger + ?2 where c.pseudo = ?1")
    void incrementHungerCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.strength = c.strength + ?2 where c.pseudo = ?1")
    void incrementStrengthCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.intelligence = c.intelligence + ?2 where c.pseudo = ?1")
    void incrementIntelligenceCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.agility = c.agility + ?2 where c.pseudo = ?1")
    void incrementAgilityCharacter(String pseudo, Long valueIncrement);

    @Transactional
    @Modifying
    @Query("update Charactergame c set c.luck = c.luck + ?2 where c.pseudo = ?1")
    void incrementLuckCharacter(String pseudo, Long valueIncrement);


    /// Test pour faire une query universelle mais ne fonctionne pas que ce soit en native ou en SPel

    // test native
//    @Transactional
//    @Modifying
//    @Query(value = "update charactergame set hp = :characteristic + :value_increment where pseudo = :pseudo" ,nativeQuery = true)
//    void incrementCharacteristicCharacter( @Param("pseudo") String pseudo,  @Param("characteristic")String characteristic,  @Param("value_increment")Long value_increment);

    // test avec enum
//    @Transactional
//    @Modifying
//    @Query(value = "update charactergame set #{#characteristic.name()} = :characteristic + :value_increment where pseudo = :pseudo" ,nativeQuery = true)
//    void incrementCharacteristicCharacter( @Param("pseudo") String pseudo,  @Param("characteristic")Characteristic characteristic,  @Param("value_increment")Long value_increment);

//    @Modifying
//    @Query("update Charactergame c set c.money = c.money + :moneyTransaction where c.pseudo = :pseudo")
//    void setMoneyTransactionCharacter(@Param("pseudo") String pseudo, @Param("moneyTransaction") Long moneyTransaction);


}
