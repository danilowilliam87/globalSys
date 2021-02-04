package com.io.globalsys.Teste;

import com.io.globalsys.Teste.model.Cep;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public interface CepRn {
    public Cep buscaPorFaixa(Cep cep);
    public Optional<Cep> buscar(Long id);
    public Cep salvar(Cep cep);
    public void atualizar(Cep cep, Long id);
    public List<Cep> listar();
    public boolean excluir(Long id);
    public AtomicBoolean verificarFaixaLivre(Cep cep);
    public boolean verificarFaixaDoObjeto(Cep cep);
}
