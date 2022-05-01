package com.rodrigo.testevenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rodrigo.testevenda.domain.Venda;


public interface VendaRepository  extends JpaRepository<Venda, Integer>{

}
