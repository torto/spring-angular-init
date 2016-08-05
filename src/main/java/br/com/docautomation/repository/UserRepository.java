package br.com.docautomation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.docautomation.model.UserSys;


public interface UserRepository extends JpaRepository<UserSys, Long> {
	
	UserSys findByUsername(String username);

	UserSys findByUserFace(String userFace);
	
	UserSys findOneByUsernameAndPassword(String username, String password);
	
	UserSys findByIdUser(long idUser);


}
