package com.capstone.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.capstone.application.exception.AllergyServiceException;

import com.capstone.application.model.Allergy;

import com.capstone.application.repository.AllergyRepository;

import com.capstone.application.service.impl.AllergyServiceImpl;

@ExtendWith(MockitoExtension.class)

public class AllergyServiceTest {

@InjectMocks

AllergyServiceImpl service;

@Mock

AllergyRepository repo;

Allergy allergy;

@BeforeEach

void setUp() {

allergy = new Allergy(1,"food","foodAllergy");

}

@Test

void findAllTest() throws AllergyServiceException{

Allergy allergy1 = new Allergy(2,"dust","dustAllergy");

List<Allergy> list = new ArrayList<Allergy>();

list.add(allergy1);

list.add(allergy);

when(repo.findAll()).thenReturn(list);

assertEquals(2,service.findAll().size());

}

@Test

void findAllergyByIdTest() throws AllergyServiceException{

Optional<Allergy> result=Optional.ofNullable(allergy);

Optional s=Optional.empty();

when(repo.findById(anyInt())).thenReturn(result);

assertEquals(result,service.findById(1));

}

}
