package com.io.globalsys.Teste.repository;


import com.io.globalsys.Teste.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {

    @Query("from Cep c where c.faixaInicio =: faixaInicial and c.faixaFim =: faixaFinal")
    public Optional<Cep> findCepByFaixaInicioAndFaixaFim(@Param("faixaInicial") int faixaInicial,
                                                      @Param("faixaFinal") int faixaFinal);
}
