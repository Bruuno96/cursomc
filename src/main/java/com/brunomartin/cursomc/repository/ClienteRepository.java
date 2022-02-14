package com.brunomartin.cursomc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brunomartin.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly=true)
	Cliente findByEmail(String email);

//	@Query("Select c from Cliente c where c.cpfOuCnpj = ?1 and c. = ?2")
//	Optional<Cliente> findByCpfAndSenha(@Param("cpf") String cpf,
//										@Param("password")String password);
}
