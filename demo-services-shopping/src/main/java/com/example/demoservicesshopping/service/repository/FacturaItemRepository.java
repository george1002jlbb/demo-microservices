package com.example.demoservicesshopping.service.repository;

import com.example.demoservicesshopping.model.FacturaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaItemRepository extends JpaRepository<FacturaItem, Long> {
}
