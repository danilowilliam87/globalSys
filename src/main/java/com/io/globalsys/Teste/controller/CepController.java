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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cep")
public class CepController {

    @Autowired
    private CepService service;



    @ResponseBody
    @PostMapping("/salvar")
    public Cep salvar(@RequestBody  Cep cep){
        Cep busca = buscaPorFaixa(cep);
        if (service.validar(cep)) {
            return service.salvar(cep);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Faixa de Valores já em uso");
        }
    }

    @ResponseBody
    @GetMapping("/busca/{id}")
    public Cep busca(@PathVariable Long id){
        return service.buscar(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Loja não localizada"));

    }

    @GetMapping("/")
    public String ola(){
        return "ola";
    }

    @GetMapping("/busca_faixa")
    @ResponseBody
    public Cep buscaPorFaixa(@RequestBody Cep cep){
        Cep cep1 = new Cep();
        cep1.setFaixaInicio(cep.getFaixaInicio());
        cep1.setFaixaFim(cep.getFaixaFim());

        return service.buscaPorFaixa(cep);
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<Cep> listar(){
        return service.listar();

    }

    @PutMapping("/atualizar/{id}")
    @ResponseBody
    public void atualizar(@RequestBody Cep atualizado , @PathVariable(name = "id") Long id){
        Optional<Cep> busca = service.buscar(id);
        if(busca.isPresent()){
            if(service.validarAtualizacao(busca.get(), atualizado))
                service.atualizar(atualizado, id);
            else if (service.validar(atualizado)){
                service.atualizar(atualizado, id);
            }  else {
                throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Impossível atualizar");
            }
        }
        else{
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Loja/Cep não localizado");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Long id){
        Optional<Cep> busca = service.buscar(id);
        if (busca.isPresent())
            service.excluir(id);
        else {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Loja/Cep não localizado");
        }
    }

}
