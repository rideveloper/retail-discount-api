package com.ridwan.retaildiscountapi.repo;

import com.ridwan.retaildiscountapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ridwan Mustapha
 */
@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
}
