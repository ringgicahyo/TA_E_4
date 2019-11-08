package apap.tugasakhir.situ.repository;

import org.springframework.stereotype.Repository;

import apap.tugasakhir.situ.model.JenisLowonganModel;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JenisLowonganDb extends JpaRepository<JenisLowonganModel, Integer> {

}