package com.example.test.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.Model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
