package com.backend1.pensionat.repos;

import com.backend1.pensionat.dtos.KundDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Kund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BokningRepo extends JpaRepository<Bokning, Long> {

    @Query("select kund from Bokning")
    public List<Kund> getKundIdList();


}
