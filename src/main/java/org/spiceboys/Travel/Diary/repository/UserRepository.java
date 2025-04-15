package org.spiceboys.Travel.Diary.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.spiceboys.Travel.Diary.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
