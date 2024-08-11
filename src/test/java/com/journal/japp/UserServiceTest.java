package com.journal.japp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {


    @Test
    public void initialTest(){
       // assertTrue()
        assertEquals(4,2+1);
    }

}
