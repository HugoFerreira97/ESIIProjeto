import Exceptions.UserAlreadyExists;
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
}
