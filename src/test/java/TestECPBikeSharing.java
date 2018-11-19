import Exceptions.UserAlreadyExists;
import Exceptions.UserDoesNotExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestECPBikeSharing {
    BikeRentalSystem brs;

    @BeforeEach
    public void setUp() throws UserAlreadyExists {
        //Inicialização do BikeRentalSystem com rentalFee=1
        brs = new BikeRentalSystem(1);

        //Adição de um User com id=0
        brs.registerUser(0, "Hugo", 1);

        //Adição de um Lock com id=0 e um Deposit com id=0
        brs.addLock(1, 1);

        //Adição de uma Bicycle com id=0
        brs.addBicycle(1, 1, 0);
    }

    @Test
    public void testRegisterUser() throws UserAlreadyExists {
        //Verificação se a excepção é lançada ao adicionar utilizador com id repetido
        assertThrows(UserAlreadyExists.class, () -> {
            brs.registerUser(0, "João", 1);
        }, "Should Throw Exception: UserAlreadyExists"); // Test Case #1 Passed

        //Verificação se o utilizador foi adicionado com sucesso
        brs.registerUser(1, "Hugo", 1);
        assertEquals(1, brs.getUsers().get(1).getIDUser(), "Expected = 1, Actual = " + brs.getUsers().get(1).getIDUser()); //Test Case #2 Passed

    }

    @Test
    public void testAddCredit() {
        //Adição um crédito ao utilizador com id=0
        brs.addCredit(0, 1);

        //Verificação se o crédito foi adicionado com sucesso
        assertEquals(1, brs.getUsers().get(0).getCredit(), "Expected = 1, Actual = " + brs.getUsers().get(0).getCredit()); // Test Case #1 Passed

    }

    @Test
    public void testVerifyCredit() throws UserAlreadyExists {
        //Adição de um crédito ao utilizador com id=0
        brs.addCredit(0, 1);

        //Adição de mais um utilizador sem créditos
        brs.registerUser(1, "João", 2);

        //Verificação se retorna false ao verificar se é possivel adicionar créditos a um utilizador não existente na lista de utilziadores ou se um utlizador não tem créditos
        assertAll("Should return false if User does not exist or there's no credits in User",
                () -> assertFalse(brs.verifyCredit(1)), //Utilizador sem créditos/ Test Case #1 Passed
                () -> assertFalse(brs.verifyCredit(2)) //Utilizador não existente/ Test Case #2 Passed
        );

        //Verificação se retorna true ao verificar se um utilizador tem créditos suficientes
        assertTrue(brs.verifyCredit(0), "Expected = true, Actual = " + brs.verifyCredit(0)); // Test Case #3 Passed

    }

    @Test
    public void testBicycleRentalFee() {
        //Testar se retorna (endtime-stattime) * rentalFee se o rentalProgram for 1
        assertEquals(1, brs.bicycleRentalFee(1, 0, 1, 0), "Expected = 1, Actual = " + brs.bicycleRentalFee(1, 0, 1, 0)); // Test Case #1 Passed

        //Testar se retorna rentalFee * (endtime-stattime) se o rentalProgram for 2 e a divisão inteira de nRentals por 10 != 0
        assertEquals(1, brs.bicycleRentalFee(2, 0, 1, 1), "Expected = 1, Actual = " + brs.bicycleRentalFee(2, 0, 1, 10)); // Test Case #2 Passed

        //Testar se retorna 10*rentalFee + ((endtime-starttime)-10)*rentalFee/2
        assertEquals(10, brs.bicycleRentalFee(2, 0, 10, 1), "Expected = 10, Actual = " + brs.bicycleRentalFee(2, 0, 10, 1)); //Test case #3 Passed

        //Testar se retorna 0 se o rentalProgram=3 ou %nRentals/10 !=0
        assertAll("Should return 0 if rentalProgram=3 or %nRentals/10 != 0",
                () -> assertEquals(0, brs.bicycleRentalFee(3, 0, 1, 0)), //Test Case #4 Passed
                () -> assertEquals(0, brs.bicycleRentalFee(2, 0, 1, 10)) // Test Case #5 Passed
        );

    }

    @Test
    public void testReturnBicycle() throws UserDoesNotExists, UserAlreadyExists {

        //Atribuição de Bicycle ao User com id=0
        brs.getBicycle(1, 0, 0);

        //Adição de um User com id=1
        brs.registerUser(1, "João", 1);

        //Adição de um novo Deposit com id=2 e Lock com id=2
        brs.addLock(2, 2);

        //Adição de uma Bicycle com id=1 a um Deposit com id=2 e Lock de id=2
        brs.addBicycle(2, 2, 1);

        //Testar se retorna -1 se o User não existir, se o Deposit não existir ou a Bicycle não estiver associado a um aluguer
        assertAll("Should return -1 if IDUser does not exist, IDDeposit does not exist or Bicycle is not associated to active rent",
                () -> assertEquals(-1, brs.returnBicycle(3, 0, 1), "Expected = -1, Actual = " + brs.returnBicycle(3, 0, 1)), //Test case #1 Passed
                () -> assertEquals(-1, brs.returnBicycle(2, 3, 1), "Expected = -1, Actual = " + brs.returnBicycle(2, 3, 1)), //Test case #2 Passed
                () -> assertEquals(-1, brs.returnBicycle(2, 1, 1), "Expected = -1, Actual = " + brs.returnBicycle(2, 1, 1)) //Test case #3 Passed
        );

        //Testar se retorna -1 se não existir Deposit livre
        assertEquals(-1, brs.returnBicycle(2, 0, 1), "Expected = -1, Actual = " + brs.returnBicycle(2, 0, 1)); //Test case #4 Passed

        //Testar se retorna o saldo atual do cliente se existir User, Deposit e Bicycle associada ao aluguer ativo
        assertEquals(0, brs.returnBicycle(1, 1, 1), "Expected = -1, Actual = " + brs.returnBicycle(1, 1, 1)); //Test case #5 Failed

    }

    @Test
    public void testGetBicycle() throws UserDoesNotExists, UserAlreadyExists {

        //Adição de um User com id=1
        brs.registerUser(1, "Hugo", 2);
        //Atribuição de um crédito ao User com id=1
        brs.addCredit(1, 1);
        //Atribuição de uma Bicycle ao User com id=1
        brs.getBicycle(1, 1, 0);

        //Adição de um User com id=0
        brs.registerUser(2, "João", 1);
        //Adição de um crédito ao User com id=2
        brs.addCredit(2, 1);

        //Adição de um User com id=3
        brs.registerUser(3, "Hugo", 1);

        //Adição de um Deposit com id=2 e Lock com id=2
        brs.addLock(2, 2);

        //Adição de uma Bicycle com id=1 no Deposit com id=2 e Lock de id=2
        brs.addBicycle(2, 2, 1);

        //Adição de um Depósito com id=3 e Lock com id=3
        brs.addLock(3, 3);

        //Testar se executa a exceção UserDoesNotExists
        assertThrows(UserDoesNotExists.class, () -> {
            brs.getBicycle(1, 4, 0);
        }, "Should Throw Exception: UserDoesNotExists"); // Test Case #1 Passed

        //Testar se retorna -1 se não existir depósito
        assertEquals(-1, brs.getBicycle(4, 2, 0), "Expected = -1, Actual = " + brs.getBicycle(4, 2, 0)); // Test Case #2 Passed

        //Testar se retorna -1 se os créditos do utilizador forem <1
        assertEquals(-1, brs.getBicycle(2, 3, 0), "Expected = -1, Actual = " + brs.getBicycle(2, 3, 0)); // Test Case #3 Passed

        //Testar se retorna -1 se o depósito não tem bicicletas disponíveis ou se o utilizador já tiver um aluguer ativo
        assertAll("Should return -1 if the Bicyle is already rented or user has already 1 Bicycle associated",
                () -> assertEquals(-1, brs.getBicycle(1, 2, 0), "1. Expected = -1, Actual= " + brs.getBicycle(1, 2, 0)), // Test Case #4 Passed
                () -> assertEquals(-1, brs.getBicycle(2, 1, 0), "2. Expected = -1, Actual = " + brs.getBicycle(2, 1, 0))); //Test Case #5 Failed
    }


}



