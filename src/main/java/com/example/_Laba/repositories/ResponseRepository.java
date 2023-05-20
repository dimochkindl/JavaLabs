package com.example._Laba.repositories;

import com.example._Laba.enities.ResponseEntityData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseEntityData, Integer> {
}
