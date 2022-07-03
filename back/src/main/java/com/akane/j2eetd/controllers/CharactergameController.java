package com.akane.j2eetd.controllers;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.exceptions.ResourceNotFoundException;
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
    public Charactergame getCharactergame(@PathVariable(value = "pseudo") String pseudo) throws ResourceNotFoundException {
        return charactergameService.getCharactergameByPseudo(pseudo);
    }

    @Operation(summary = "Récupération d'un personnage à partir de son utilisateur")
    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public Charactergame getCharactergameByUser(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        return charactergameService.getCharactergameByUser(username);
    }

    @Operation(summary = "Mise à jour d'un personnage")
    @RequestMapping(method = RequestMethod.PUT)
    public Charactergame modifyCharactergame(@RequestBody @Valid Charactergame charactergame) {
        return charactergameService.createOrUpdate(charactergame);
    }

    @Operation(summary = "Création d'un personnage")
    @RequestMapping(method = RequestMethod.POST)
    public Charactergame addCharactergame(@RequestBody @Valid Charactergame charactergame) {
        return charactergameService.createOrUpdate(charactergame);
    }

    @Operation(summary = "Récupération de toutes les personnages")
    @RequestMapping(path = "_all", method = RequestMethod.GET)
    public List<Charactergame> getCharactergames() {
        return charactergameService.getAllCharactergames();
    }

    @Operation(summary = "Suppression d'un personnage")
    @RequestMapping(path = "/{pseudo}", method = RequestMethod.DELETE)
    public void deleteCharacter(@PathVariable(value = "pseudo") String pseudo) throws ResourceNotFoundException {
        charactergameService.deleteCharactergameByPseudo(pseudo);
    }

    @Operation(summary = "Transaction de l'argent du personnage selon le montant définit")
    @RequestMapping(path = "/{pseudo}/money", method = RequestMethod.PUT)
    public void transactionMoney(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "amount") Long amount) throws ResourceNotFoundException {
        charactergameService.setAmountMoney(pseudo, amount);
    }

    @Operation(summary = "Incrémente les hpmax du personnage")
    @RequestMapping(path = "/{pseudo}/hpmax", method = RequestMethod.PUT)
    public void incrementHpMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementHpMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente les manamax du personnage")
    @RequestMapping(path = "/{pseudo}/manamax", method = RequestMethod.PUT)
    public void incrementManaMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementManaMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'endurance max du personnage")
    @RequestMapping(path = "/{pseudo}/staminamax", method = RequestMethod.PUT)
    public void incrementStaminaMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementStaminaMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la satiété max du personnage")
    @RequestMapping(path = "/{pseudo}/hungermax", method = RequestMethod.PUT)
    public void incrementHungerMaxCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementHungerMaxCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente les hp du personnage")
    @RequestMapping(path = "/{pseudo}/hp", method = RequestMethod.PUT)
    public void incrementHpCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementHpCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente le mana du personnage")
    @RequestMapping(path = "/{pseudo}/mana", method = RequestMethod.PUT)
    public void incrementManaCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementManaCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'endurance du personnage")
    @RequestMapping(path = "/{pseudo}/stamina", method = RequestMethod.PUT)
    public void incrementStaminaCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementStaminaCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la satiété du personnage")
    @RequestMapping(path = "/{pseudo}/hunger", method = RequestMethod.PUT)
    public void incrementHungerCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementHungerCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la force du personnage")
    @RequestMapping(path = "/{pseudo}/strength", method = RequestMethod.PUT)
    public void incrementStrengthCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementStrengthCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'intelligence du personnage")
    @RequestMapping(path = "/{pseudo}/intelligence", method = RequestMethod.PUT)
    public void incrementIntelligenceCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementIntelligenceCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente l'agilité du personnage")
    @RequestMapping(path = "/{pseudo}/agility", method = RequestMethod.PUT)
    public void incrementAgilityCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementAgilityCharacter(pseudo, valueIncrement);
    }

    @Operation(summary = "Incrémente la chance du personnage")
    @RequestMapping(path = "/{pseudo}/luck", method = RequestMethod.PUT)
    public void incrementLuckCharacter(@PathVariable(value = "pseudo") String pseudo, @RequestParam(value = "valueIncrement") Long valueIncrement) throws ResourceNotFoundException {
        charactergameService.updateIncrementLuckCharacter(pseudo, valueIncrement);
    }

}
