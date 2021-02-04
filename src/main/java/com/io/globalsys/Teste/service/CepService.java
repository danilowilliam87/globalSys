package com.io.globalsys.Teste.service;

import com.io.globalsys.Teste.CepRn;
import com.io.globalsys.Teste.model.Cep;
import com.io.globalsys.Teste.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CepService implements CepRn {

    @Autowired
    private CepRepository repository;

    @Override
    public Cep buscaPorFaixa(Cep cep) {
        Cep r = new Cep();
        Optional<Cep> busca = repository
                .findByFaixaInicioAndFaixaFim(cep.getFaixaInicio(), cep.getFaixaFim());
        if (busca.isPresent()){
              r =  busca.get(); 
          }
          return r;
    }

    @Override
    public Cep salvar(Cep cep) {
        return repository.save(cep);
    }

    @Override
    public void atualizar(Cep cep, Long id) {
        Optional<Cep> busca = repository.findById(id);
        Cep retorno = new Cep();
        if (busca.isPresent()){
            retorno = busca.get();
            retorno.setId(id);
            retorno.setCodigoLoja(cep.getCodigoLoja());
            retorno.setFaixaInicio(cep.getFaixaInicio());
            retorno.setFaixaFim(cep.getFaixaFim());
        }
        repository.save(retorno);
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
    public AtomicBoolean verificarFaixaLivre(Cep cep) {
        AtomicBoolean ok = new AtomicBoolean(false);
        repository.findAll()
                .forEach(cep1 -> {
                    ok.set(!(cep.getFaixaInicio() >= cep1.getFaixaInicio()
                            && cep.getFaixaFim() <= cep1.getFaixaFim()));
                });
        return ok;
    }

    @Override
    public boolean verificarFaixaDoObjeto(Cep cep) {
        return cep.getFaixaInicio() < cep.getFaixaFim();
    }


}
