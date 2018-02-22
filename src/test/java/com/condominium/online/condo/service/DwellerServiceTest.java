package com.condominium.online.condo.service;

import com.condominium.online.condo.entity.Dweller;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.repository.DwellerRepository;
import com.condominium.online.condo.repository.impl.DwellerRepositoryImpl;
import com.condominium.online.condo.service.dweller.DwellerService;
import com.condominium.online.condo.service.dweller.impl.DwellerServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { DwellerRepositoryImpl.class, DwellerServiceImpl.class })
public class DwellerServiceTest {

    private static final String DWELLER_NAME = "Godzilla";
    private static final String DWELLER_CPF = "36724476925";
    private static final String DWELLER_APARTMENT_NUMBER = "68C";
    private Dweller dweller;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @MockBean
    private DwellerRepository dwellerRepository;

    @Autowired
    private DwellerService dwellerService;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        dweller = new Dweller(DWELLER_NAME, DWELLER_CPF, DWELLER_APARTMENT_NUMBER);
    }

    @Test
    public void whenSaveDwellerThenReturnSavedDweller() throws Exception {

        dwellerService.insert(dweller);
        verify(dwellerRepository, atLeastOnce()).save(dweller);
    }

    @Test
    public void whenSaveDwellerWithNullNameThenReturnInvalidUserException() throws Exception {

        dweller.setName(null);

        doThrow(new InvalidUserException("Invalid Name")).when(dwellerRepository).save(dweller);
        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid Name");

        dwellerService.insert(dweller);
    }

    @Test
    public void whenSaveDwellerWithEmptyNameThenReturnInvalidUserException() throws Exception {

        dweller.setName("");

        doThrow(new InvalidUserException("Invalid Name")).when(dwellerRepository).save(dweller);
        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid Name");

        dwellerService.insert(dweller);
    }

    @Test
    public void whenSaveDwellerWithNullCPFThenReturnInvalidUserException() throws Exception {

        dweller.setCpf(null);

        doThrow(new InvalidUserException("Invalid CPF")).when(dwellerRepository).save(dweller);
                expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid CPF");

        dwellerService.insert(dweller);
    }


    @Test
    public void whenSaveDwellerWithEmptyCPFThenReturnInvalidUserException() throws Exception {

        dweller.setCpf("");

        doThrow(new InvalidUserException("Invalid CPF")).when(dwellerRepository).save(dweller);
        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid CPF");

        dwellerService.insert(dweller);
    }

    @Test
    public void whenSavingDwellerWithNullApartmentThenReturnInvalidUserException() throws Exception {

        dweller.setApartmentNumber(null);

        doThrow(new InvalidUserException("Invalid apartment")).when(dwellerRepository).save(dweller);
        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid apartment");

        dwellerService.insert(dweller);
    }

    @Test
    public void whenSavingDwellerWithEmptyApartmentThenReturnInvalidUserException() throws Exception {

        dweller.setApartmentNumber("");

        doThrow(new InvalidUserException("Invalid apartment")).when(dwellerRepository).save(dweller);
        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("Invalid apartment");

        dwellerService.insert(dweller);
    }

    @Test
    public void whenRemovingExistingUserThenReturnSuccessful() throws InvalidUserException {

        dweller.setId(123);

        when(dwellerRepository.exists(dweller.getId())).thenReturn(true);
        doNothing().when(dwellerRepository)
                   .delete(dweller.getId());

        dwellerService.delete(String.valueOf(dweller.getId()));

        verify(dwellerRepository, atLeastOnce()).delete(123);
    }

    @Test
    public void whenRemovingNonExistingUserIdThenReturnInvalidUserException() throws InvalidUserException {

        dweller.setId(0);

        expectedException.expect(InvalidUserException.class);
        expectedException.expectMessage("No such user");

        when(dwellerRepository.exists(dweller.getId())).thenReturn(false);
        doNothing().when(dwellerRepository)
                   .delete(dweller.getId());

        dwellerService.delete(String.valueOf(dweller.getId()));
    }
}
