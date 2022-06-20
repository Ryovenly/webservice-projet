package com.akane.j2eetd.controllers;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.services.CharactergameService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/charactergames")
public class CharactergameController {

    @Autowired
    private CharactergameService charactergameService;

    @Operation(summary = "Récupération d'un personnage à partir de son identifiant")
    @RequestMapping(path = "/{pseudo}", method = RequestMethod.GET)
    public Charactergame getCharactergame(@PathVariable(value = "pseudo") String pseudo) {
        return charactergameService.getCharactergameByPseudo(pseudo);
    }

    @Operation(summary = "Création ou mise à jour d'un personnage")
    @RequestMapping(method = RequestMethod.PUT)
    public Charactergame addCharactergame(@RequestBody @Valid Charactergame charactergame) {
        return charactergameService.createOrUpdate(charactergame);
    }

    @Operation(summary = "Récupération de toutes les personnages")
    @RequestMapping(path = "_all", method = RequestMethod.GET)
    public List<Charactergame> getCharactergames() {
        return charactergameService.getCharactergame();
    }

    @Operation(summary = "Suppression d'un personnage")
    @RequestMapping(path = "/{pseudo}", method = RequestMethod.DELETE)
    public void deleteCharacter(@PathVariable(value = "pseudo") String pseudo) {
        charactergameService.deleteCharactergameByPseudo(pseudo);
    }

    @Operation(summary = "Transaction de l'argent du personnage selon le montant définit")
    @RequestMapping(path = "/{pseudo}/money/{amount}", method = RequestMethod.PUT)
    public void transactionMoney(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "amount") Long amount){
        charactergameService.setAmountMoney(pseudo, amount);
    }

    @Operation(summary = "Incrémente les hpmax du personnage")
    @RequestMapping(path = "/{pseudo}/hpmax/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementHpMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementHpMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente les manamax du personnage")
    @RequestMapping(path = "/{pseudo}/manamax/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementManaMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementManaMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'endurance max du personnage")
    @RequestMapping(path = "/{pseudo}/staminamax/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementStaminaMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementStaminaMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la satiété max du personnage")
    @RequestMapping(path = "/{pseudo}/hungermax/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementHungerMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementHungerMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente les hp du personnage")
    @RequestMapping(path = "/{pseudo}/hp/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementHpCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementHpCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente le mana du personnage")
    @RequestMapping(path = "/{pseudo}/mana/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementManaCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementManaCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'endurance du personnage")
    @RequestMapping(path = "/{pseudo}/stamina/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementStaminaCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementStaminaCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la satiété du personnage")
    @RequestMapping(path = "/{pseudo}/hunger/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementHungerCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementHungerCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la force du personnage")
    @RequestMapping(path = "/{pseudo}/strength/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementStrengthCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementStrengthCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'intelligence du personnage")
    @RequestMapping(path = "/{pseudo}/intelligence/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementIntelligenceCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementIntelligenceCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'agilité du personnage")
    @RequestMapping(path = "/{pseudo}/agility/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementAgilityCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementAgilityCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la chance du personnage")
    @RequestMapping(path = "/{pseudo}/luck/{valueIncrement}", method = RequestMethod.PUT)
    public void incrementLuckCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "valueIncrement") Long valueIncrement){
        charactergameService.updateIncrementLuckCharacter(pseudo, valueIncrement);
    }

//    @RequestMapping(path = "/{pseudo}/{characteristic}/{value}", method = RequestMethod.PUT)
//    public void IncrementCharacteristicCharacter(@PathVariable(value = "pseudo") String pseudo, @PathVariable(value = "characteristic") String characteristic, @PathVariable(value = "value") Long value){
//        charactergameService.incrementCharacteristicCharacter(pseudo, characteristic ,value);
//    }


}
