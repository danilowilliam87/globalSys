package com.io.globalsys.Teste.controller;

import com.io.globalsys.Teste.model.Cep;
import com.io.globalsys.Teste.repository.CepRepository;
import com.io.globalsys.Teste.service.CepService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private CepService service;

    @Autowired
    private CepRepository repository;

    @ResponseBody
    @PostMapping("/salvar")
    public Cep salvar(@RequestBody  Cep cep){
        return service.salvar(cep);
    }

    @ResponseBody
    @GetMapping("/busca")
    public Cep busca(@PathVariable Long id){
        return service.buscar(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

}
