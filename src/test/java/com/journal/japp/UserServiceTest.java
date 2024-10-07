package com.journal.japp;

import com.journal.japp.entity.User;
import com.journal.japp.repository.UsersRepository;
import com.journal.japp.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;


    @Disabled
    @Test
    public void initialTest(){
       // assertTrue()
        assertNotNull(usersRepository.findByUserName("Sami"));
        assertTrue(!usersRepository.findByUserName("Sami").getJournalEntries().isEmpty());
    }


    @Test
    @ParameterizedTest
    @CsvSource({
            "4,1,2",
            "3,1,2",
    })
    public void anotherTest(int expected,int a,int b){
        assertEquals(expected,a+b);
    }

    @Disabled
    @Test
    @ParameterizedTest
    @ValueSource(ints = {
            4,1,2,
            3,1,2,
    })
    public void anotherTestWithValueSource(int expected,int a,int b){
        assertEquals(expected,a+b);
    }

    @ParameterizedTest
    @EnumSource(Operation.class)
    public void testOperationWithEnumSource(Operation operation) {
        int a = 3;
        int b = 1;
        int result;

        switch (operation) {
            case ADD:
                result = a + b;
                assertEquals(4, result);
                break;
            case SUBTRACT:
                result = a - b;
                assertEquals(2, result);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + operation);
        }
    }

    @Test
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void getTestUserName(User user){
      //  assertTrue(userService.saveNewUser(user));
    }

}
