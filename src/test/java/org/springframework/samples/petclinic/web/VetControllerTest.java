package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    ClinicService clinicService;

    @Mock
    Map<String,Object> model;

    @InjectMocks
    VetController controller;

    List<Vet> vetList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        vetList.add(new Vet());
        given(clinicService.findVets()).willReturn(vetList);
    }

    @Test
    void showVetList() {
        //When
        String view = controller.showVetList(model);
        //Then
        then(clinicService).should().findVets();
        then(model).should().put(anyString(), any());
        assertThat("vets/VetList").isEqualToIgnoringCase(view);
    }

    @Test
    void showResourcesVetList() {
        //When
        Vets vets = controller.showResourcesVetList();
        //Then
        then(clinicService).should().findVets();
        assertThat(vets.getVetList()).hasSize(1);

    }
}