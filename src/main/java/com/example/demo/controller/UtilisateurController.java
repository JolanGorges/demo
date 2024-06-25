package com.example.demo.controller;

import com.example.demo.dao.UtilisateurDao;
import com.example.demo.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @GetMapping("/liste")
    public List<Utilisateur> liste() {
        return utilisateurDao.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> get(@PathVariable int id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(id);
        if (optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalUtilisateur.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public boolean add(@RequestBody Utilisateur utilisateur) {
        utilisateur.setId(null);
        utilisateurDao.save(utilisateur);
        return true;
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        utilisateurDao.save(utilisateur);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        utilisateurDao.deleteById(id);
        return true;
    }
}