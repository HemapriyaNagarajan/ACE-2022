package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
//	@Query(value="SELECT * FROM user_table u WHERE u.tech1,u.tech2,u.tech3 LIKE %?1%",nativeQuery = true)
	 @Query(value="SELECT * FROM user_table p WHERE "
		 	+ "CONCAT(p.tech1, p.tech2, p.tech3, p.experience, p.establishment)"
	            + " LIKE %?1%", nativeQuery = true)
	public List<User> findAll(String keyword);
	 

	@Query(value="SELECT * FROM user_table c WHERE c.tech1 LIKE %?1% and c.experience>=?2 and c.establishment LIKE %?3% and c.cgpa>=?4 and c.hsc>=?5",nativeQuery = true)
	public List<User> findByParam(String tech, int years, String est, int cgpa, int hsc);
	
	
	@Query(value="SELECT * FROM user_table c WHERE c.tech1 LIKE %?1% and c.tech2 LIKE %?2% and c.experience>=?3 and c.establishment LIKE %?4% or c.cgpa>=?5 and c.hsc>=?6",nativeQuery = true)
	public List<User> findByParamTwo(String tech1, String tech2, int years, String est, int cgpa, int hsc);
	
	@Query(value="SELECT * FROM user_table c WHERE c.tech1 LIKE %?1% or c.tech2 LIKE %?2% or c.tech3 LIKE %?3% and c.experience>=?4 and c.establishment LIKE %?5% and c.cgpa>=?6 and c.hsc>=?7 ",nativeQuery = true)
	public List<User> findByParamThree(String tech1, String tech2,String tech3, int years, String est, int cgpa, int hsc);
	
	
	@Query(value="SELECT * FROM user_table u WHERE u.email=?1",nativeQuery = true)
	public List<User> findByEmail(String email);
	
}
