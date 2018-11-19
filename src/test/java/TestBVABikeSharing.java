import Exceptions.UserAlreadyExists;
import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBVABikeSharing {

    BikeRentalSystem brs;

    @BeforeEach
    public void setUp() {
        this.brs = new BikeRentalSystem(1);
    }

    @Test
    public void testRegisterUser() throws UserAlreadyExists {
        /* brs.registerUser(-1, "Hugo", 1);
        assertTrue(brs.getUsers().isEmpty()); */ // Test Case #1 Failed

        /* brs.registerUser(0, "Hugo", 1);
        assertFalse(brs.getUsers().isEmpty()); */ // Test Case #2 Passed

        /* brs.registerUser(1,"Hugo", 1);
        assertFalse(brs.getUsers().isEmpty()); */ // Test Case #3 Passed

        /* brs.registerUser(0,"Hugo", 0);
        assertTrue(brs.getUsers().isEmpty()); */ // Test Case #4 Failed

        /* brs.registerUser(0,"Hugo", 2);
        assertFalse(brs.getUsers().isEmpty()); */ //Test Case #5 Passed

        /* brs.registerUser(0,"Hugo", 3);
        assertTrue(brs.getUsers().isEmpty()); */ //Test Case #6 Passed

        /* brs.registerUser(0,null, 1);
        assertTrue(brs.getUsers().isEmpty()); */ // Test Case #7 Failed
    }

    @Test
    public void testAddCRedit() throws UserAlreadyExists {
        //Registar um utilizador com id=0 e outro com id=1
        brs.registerUser(0, "João", 1);
        brs.registerUser(1, "João", 1);

        //Utilizador com id = 0 em que é inserido créditos
        User u = brs.getUsers().get(0);
        User u1 = brs.getUsers().get(1);


        /* brs.addCredit(-1, 1);
        assertEquals(0, u.getCredit(), "Expected = 0, Actual = " + u.getCredit()); */ // Test Case #1 Passed

        /* brs.addCredit(0, 1);
        assertEquals(1, u.getCredit(), "Expected = 1, Actual = " + u.getCredit()); */ // Test Case #2 Passed

        /* brs.addCredit(1, 1);
        assertEquals(1, u1.getCredit(), "Expected = 1, Actual = " + u1.getCredit()); */ //Test Case #3 Passed

         /* brs.addCredit(0, 0);
        assertEquals(0, u.getCredit(), "Expected = 0, Actual = " + u.getCredit()); */ //Test Case #4 Passed

        /* brs.addCredit(0, 2);
        assertEquals(2, u.getCredit(), "Expected = 2, Actual = " + u.getCredit()); */ //Test Case #5 Passed

    }

    public void testVerifyCredit() throws UserAlreadyExists {
        //Registar um utilizador com id=0 e outro com id=1
        brs.registerUser(0, "Hugo", 1);
        brs.registerUser(1, "Hugo", 1);

        //Adicionar créditos a todos os utilizadores
        brs.addCredit(0, 1);
        brs.addCredit(1, 1);

        assertFalse(brs.verifyCredit(-1)); //Test #1 Passed
        assertTrue(brs.verifyCredit(0)); // Test #2 Passed
        assertTrue(brs.verifyCredit(1)); // Test #3 Passed
    }

    @Test
    public void testBicycleRentalFee() {

        /* assertEquals(0,brs.bicycleRentalFee(0,0,1,1),"Expected = 0, Actual = " +brs.bicycleRentalFee(0,0,1,1)); */ //Test Case #1 Passed

        /* assertEquals(1,brs.bicycleRentalFee(1,0,1,1),"Expected = 1, Actual = " +brs.bicycleRentalFee(1,0,1,1)); */ //Test Case #2 Passed

        /* assertEquals(1,brs.bicycleRentalFee(2,0,1,1),"Expected = 1, Actual = " +brs.bicycleRentalFee(2,0,1,1)); */ //Test Case #3 Passed

        /* assertEquals(0,brs.bicycleRentalFee(3,0,1,1),"Expected = 0, Actual = " +brs.bicycleRentalFee(3,0,1,1)); */ //Test Case #4 Passed

        /* assertEquals(2,brs.bicycleRentalFee(2,-1,1,1),"Expected = 2, Actual = " +brs.bicycleRentalFee(2,-1,1,1)); */ //Test Case #5 Failed

        /* assertEquals(1,brs.bicycleRentalFee(2,0,1,1),"Expected = 1, Actual = " +brs.bicycleRentalFee(2,0,1,1)); */ //Test Case #6 Passed

        /* assertEquals(-1,brs.bicycleRentalFee(2,0,-1,1),"Expected = -1, Actual = " +brs.bicycleRentalFee(2,0,-1,1)); */ //Test Case #7 Failed

        /* assertEquals(0,brs.bicycleRentalFee(2,0,0,1),"Expected = 0, Actual = " +brs.bicycleRentalFee(2,0,0,1)); */ //Test Case #8 Passed

        /* assertEquals(1,brs.bicycleRentalFee(2,0,1,-1),"Expected = 1, Actual = " +brs.bicycleRentalFee(2,0,1,-1)); */ //Test Case #9 Failed

        /* assertEquals(0,brs.bicycleRentalFee(2,0,1,0),"Expected = 0, Actual = " +brs.bicycleRentalFee(2,0,1,0)); */ //Test Case #10 Passed
    }
}
