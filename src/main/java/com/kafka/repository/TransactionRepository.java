package com.kafka.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kafka.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("select SUM(t.amount) from Transaction t where t.status= 'SUCCESS'")
	Double sumOfSuccessfulTransactionAmount();
	
	@Query("select t.status ,count(t) from Transaction t group by t.status")
	List<Object[]>countTransactionForEachStatus();
	
	@Query("SELECT DATE(t.timestamp), SUM(t.amount) FROM Transaction t WHERE t.timestamp >= :fromDate GROUP BY DATE(t.timestamp) ORDER BY DATE(t.timestamp)")
	List<Object[]> sumTransactionAmountPerDay(@Param("fromDate") LocalDateTime fromDate);

	@Query(value = "select u.id,u.name ,SUM(t.amount) as TOTAL_AMOUNT from transaction t JOIN user_info u on u.id=t.sender_id WHERE t.status = 'SUCCESS' Group by u.id ,u.name ORDER BY TOTAL_AMOUNT desc LIMIT 10",nativeQuery = true)
	List<Object[]> findTopUsersByTransactionVolume();
	
	Page<Transaction> findBySenderIdOrRecieverIdOrderByTimestampDesc(Long senderId,Long recieverId,Pageable pageable);
	
	@Query("select sum(t.amount) from Transaction t where t.senderId= :userId and t.status ='SUCCESS' ")
	Double sumAmountBySender(@Param("userId") Long userId);
	
	@Query("select sum(t.amount) from Transaction t where t.recieverId= :userId and t.status ='SUCCESS' ")
	Double sumAmountByReceiver(@Param("userId") Long userId);
	
	@Query("SELECT AVG(t.amount) FROM Transaction t WHERE t.senderId = :userId OR t.recieverId = :userId")
	double averageAmountByUser(Long userId);

	@Query("SELECT MAX(t.amount) FROM Transaction t WHERE t.senderId = :userId OR t.recieverId = :userId ")
	double maxAmountBySenderId(Long userId);
	
	@Query("SELECT COUNT(t) FROM Transaction t WHERE t.senderId = :userId OR t.recieverId = :userId")
	int countBySenderIdOrReceiverId(@Param("userId") Long userId);

	
	

}
