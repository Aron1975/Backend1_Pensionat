package com.backend1.pensionat.services.impl;

import com.backend1.pensionat.dtos.DetailedKundDto;
import com.backend1.pensionat.dtos.KundDto;
import com.backend1.pensionat.models.Bokning;
import com.backend1.pensionat.models.Kund;
import com.backend1.pensionat.repos.BokningRepo;
import com.backend1.pensionat.repos.KundRepo;
import com.backend1.pensionat.services.KundService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KundServiceImpl implements KundService {

    private final KundRepo kundRepo;
    private final BokningRepo bokningsRepo;


    @Override
    public List<DetailedKundDto> getAllKunder() {
        return kundRepo.findAll().stream().map(k -> kundToDetailedKundDto(k)).toList();
    }


    @Override
    public DetailedKundDto kundToDetailedKundDto(Kund k) {
        return DetailedKundDto.builder().id(k.getId()).ssn(k.getSsn()).förnamn(k.getFörnamn())
                .efternamn(k.getEfternamn()).mobilnummer(k.getMobilnummer()).email(k.getEmail())
                .adress(k.getAdress()).stad(k.getStad()).build();
    }

    @Override
    public Kund detailedKundDtoToKund(DetailedKundDto dto) {
        Kund kund = new Kund();
        kund.setId(dto.getId());
        kund.setSsn(dto.getSsn());
        kund.setFörnamn(dto.getFörnamn());
        kund.setEfternamn(dto.getEfternamn());
        kund.setAdress(dto.getAdress());
        kund.setStad(dto.getStad());
        kund.setMobilnummer(dto.getMobilnummer());
        kund.setEmail(dto.getEmail());
        return kund;
    }

    public String spara(DetailedKundDto k){
        Kund kund = detailedKundDtoToKund(k);
        String hej = "hej där";
        kundRepo.save(kund);
        return hej;
    }

    @Override
    public void deleteKundById(long id) {
        Kund kundToDelete = kundRepo.findById(id).orElse(null);
        if (kundToDelete != null && !checkIfKundHasBokning(id)) {
            kundRepo.deleteById(id);
        }
    }

    public DetailedKundDto getKund(Integer id) {
        Kund kund = kundRepo.findById(Long.valueOf(id)).orElse(null);
        if (kund != null) {
            return kundToDetailedKundDto(kund);
        }
        return null;
    }


    @Override
    public KundDto kundToKundDto(Kund k) {
        return KundDto.builder().id(k.getId()).ssn(k.getSsn()).förnamn(k.getFörnamn()).efternamn(k.getEfternamn())
                .build();
    }


    public boolean checkIfKundHasBokning(long kundId){
        return bokningsRepo.getKundIdList().stream().anyMatch(kund -> kund.getId() == kundId);
    }

}
