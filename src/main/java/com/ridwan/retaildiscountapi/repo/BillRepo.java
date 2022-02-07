package com.ridwan.retaildiscountapi.repo;

import com.ridwan.retaildiscountapi.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ridwan Mustapha
 */
@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {
}
