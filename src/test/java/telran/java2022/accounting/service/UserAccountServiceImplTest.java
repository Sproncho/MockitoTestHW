package telran.java2022.accounting.service;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.dto.UserAccountResponseDto;
import telran.java2022.accounting.dto.UserRegisterDto;
import telran.java2022.accounting.model.UserAccount;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserAccountServiceImplTest {
    @InjectMocks
    UserAccountServiceImpl userAccountService;
    @Mock
    UserAccountRepository userAccountRepository;

    @Autowired
    ModelMapper modelMapper;
    @Test
    void addUser() {
        UserAccount userAccount = new UserAccount("PuRo","Pupupu","Roma","Pushkov");
        UserAccountResponseDto expected = modelMapper.map(userAccount,UserAccountResponseDto.class);
        doNothing().when(userAccountRepository).save(any(UserAccount.class));
        UserAccountResponseDto actual = userAccountService.addUser(modelMapper.map(userAccount,UserRegisterDto.class));
        assertThat(modelMapper.map(actual,UserAccount.class)).usingRecursiveComparison().isEqualTo(expected);
        verify(userAccountRepository,times(1)).save(any(UserAccount.class));
    }

    @Test
    void getUser() {
        var expected = new UserAccount("PuRo","Pupupu","Roma","Pushkov");
        when(userAccountRepository.findById(anyString())).thenReturn(Optional.of(expected));
        var actual = userAccountRepository.findById("PuRo");
        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(expected));
        verify(userAccountRepository,times(1)).findById(anyString());

    }

    @Test
    void removeUser() {
        var userAccount = new UserAccount("PuRo","Pupupu","Roma","Pushkov");
        when(userAccountRepository.findById(anyString())).thenReturn(Optional.of(userAccount));

        doNothing().when(userAccountRepository).deleteById(anyString());
        var actual = userAccountService.removeUser("PuRo");
       // assertThat(modelMapper.map(actual,)).usingRecursiveComparison().isEqualTo(Optional.of(expected));
        verify(userAccountRepository,times(1)).deleteById(anyString());

    }

    @Test
    void editUser() {
    }

    @Test
    void changeRolesList() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void run() {
    }
}