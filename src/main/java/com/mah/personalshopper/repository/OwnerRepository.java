package com.mah.personalshopper.repository;

import com.mah.personalshopper.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
