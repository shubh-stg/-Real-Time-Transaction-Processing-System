package com.kafka.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kafka.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("select SUM(t.amount) from Transaction t where t.status= 'SUCCESS'")
	Float sumOfSuccessfulTransactionAmount();
	
	@Query("select t.status ,count(t) from Transaction t group by t.status")
	List<Object[]>countTransactionForEachStatus();
	
	@Query("SELECT DATE(t.timestamp), SUM(t.amount) FROM Transaction t WHERE t.timestamp >= :fromDate GROUP BY DATE(t.timestamp) ORDER BY DATE(t.timestamp)")
	List<Object[]> sumTransactionAmountPerDay(@Param("fromDate") LocalDateTime fromDate);

	@Query("SELECT t.senderId, SUM(t.amount) FROM Transaction t GROUP BY t.senderId ORDER BY SUM(t.amount) DESC")
	List<Object[]> findTopUsersByTransactionVolume();

}
