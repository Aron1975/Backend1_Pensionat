package com.backend1.pensionat.repos;

import com.backend1.pensionat.models.Kund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundRepo extends JpaRepository<Kund, Long> {
}
