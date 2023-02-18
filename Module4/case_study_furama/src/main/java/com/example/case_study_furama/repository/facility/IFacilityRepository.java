package com.example.case_study_furama.repository.facility;

import com.example.case_study_furama.model.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacilityRepository extends JpaRepository<Facility,Integer> {
}