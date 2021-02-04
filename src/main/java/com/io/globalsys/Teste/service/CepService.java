package com.io.globalsys.Teste.service;

import com.io.globalsys.Teste.CepRn;
import com.io.globalsys.Teste.model.Cep;
import com.io.globalsys.Teste.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CepService implements CepRn {

    @Autowired
    private CepRepository repository;

    @Override
    public Cep buscaPorFaixa(Cep cep) {
        Cep r = new Cep();
        Optional<Cep> busca = repository
                .buscarPelaFaixa(cep.getFaixaInicio(), cep.getFaixaFim());
        if (busca.isPresent()){
              r =  busca.get(); 
          }
          return r;
    }

    @Override
    public Optional<Cep> buscar(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cep salvar(Cep cep) {
        return repository.save(cep);
    }

    @Override
    public Cep atualizar(Cep cep, Long id) {
        Optional<Cep> busca = repository.findById(id);
        Cep retorno = new Cep();
        if (busca.isPresent()){
            retorno = busca.get();
            retorno.setId(id);
            retorno.setCodigoLoja(cep.getCodigoLoja());
            retorno.setFaixaInicio(cep.getFaixaInicio());
            retorno.setFaixaFim(cep.getFaixaFim());
        }
       return  repository.save(retorno);
    }

    @Override
    public List<Cep> listar() {
        return repository.findAll();
    }

    @Override
    public boolean excluir(Long id) {
         repository.deleteById(id);
         return true;
    }

    @Override
    public boolean verificarFaixaLivre(Cep cep) {
        boolean ok = true;
        List<Cep>lista = repository.findAll();
        for(Cep c  : lista){
            if (cep.getFaixaInicio() >= c.getFaixaInicio() && cep.getFaixaFim() <= c.getFaixaFim()){
                ok = false;
                break;
            }
        }
        return ok;
    }

    @Override
    public boolean verificarFaixaDoObjeto(Cep cep) {
        return cep.getFaixaInicio() < cep.getFaixaFim();
    }


    //método responsável por validar se a faixa está livre ou não.
    public boolean validar(Cep cep){
        boolean valid1 = verificarFaixaDoObjeto(cep);
        Cep busca = buscaPorFaixa(cep);
        boolean valid3 = verificarFaixaLivre(cep);

        return valid1 && valid3 && busca.getCodigoLoja() == null;

    }


    public boolean validarAtualizacao(Cep cep, Cep atualizar){
       return atualizar.getFaixaInicio() >= cep.getFaixaInicio() && atualizar.getFaixaFim() <= cep.getFaixaFim();
    }
}
