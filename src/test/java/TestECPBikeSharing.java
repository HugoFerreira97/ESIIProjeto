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
        brs.addLock(0, 0);

        //Adição de uma Bicycle com id=0
        brs.addBicycle(0, 0, 0);
    }

    @Test
    public void testRegisterUser() {
        //Verificação se a excepção é lançada ao adicionar utilizador com id repetido
        assertThrows(UserAlreadyExists.class, () -> {
            brs.registerUser(0, "João", 1);
        }, "Should Throw Exception: UserAlreadyExists");         // Test Case #1 Passed

    }

    @Test
    public void testAddCredit() {
        //Adição um crédito ao utilizador com id=0
        brs.addCredit(0, 1);

        //Verificação se o crédito foi adicionado com sucesso
        assertEquals(1, brs.getUsers().get(0).getCredit(), "Expected = 1, Actual = " + brs.getUsers().get(0).getCredit());        //Test Case #1 Passed

    }

    @Test
    public void testVerifyCredit() throws UserAlreadyExists {
        //Adição de um crédito ao utilizador com id=0
        brs.addCredit(0, 1);

        //Adição mais um utilizador sem créditos
        brs.registerUser(1, "João", 2);

        //Verificação se retorna false ao verificar se é possivel adicionar créditos a um utilizador não existente na lista de utilziadores ou se um utlizador não tem creditos
        assertAll("Should return False if user in IDUser = 2 does not exist or there's no credits in IDUser = 1",
                () -> assertFalse(brs.verifyCredit(1)), //Utilizador sem créditos
                () -> assertFalse(brs.verifyCredit(2)) //Utilizador não existente
        );

        //Verificação se retorna true ao verificar se um utilizador tem créditos suficientes
        assertTrue(brs.verifyCredit(0));

    }

    @Test
    public void testGetBicycle() throws UserDoesNotExists, UserAlreadyExists {
        //Adicionar Crédito ao utilizador com IDUser = 0
        brs.addCredit(0, 1);

        //Adicionar Utilizador com créditos vazios
        brs.registerUser(1, "João", 2);

        //Adição de um Lock com id = 1 e um Deposit com id = 1
        brs.addLock(1, 1);

        //Adição de uma Bicicleta com id = 1
        brs.addBicycle(1, 1, 1);

        //Testar se é lançada a exceção UserDoesNotExists, caso não haja um utilizador com o id introduzido
        assertThrows(UserDoesNotExists.class, () -> {
            brs.getBicycle(0, 2, 0);
        }, "Should Throw Exception: UserDoesNotExists");

        //Testar se retorna -1 se tentar requisitar uma bicicleta sem que exista depósito
        assertEquals(-1, brs.getBicycle(2, 0, 0));

        //Testar se retorna -1 se os créditos do utilizador forem <1
        assertEquals(-1, brs.getBicycle(0, 1, 0));

        brs.getBicycle(0, 0, 0);

        assertAll("Should return -1 if the Bicyle is already rented or user has already 1 Bicycle associated",
                () -> assertEquals(-1, brs.getBicycle(0, 1, 0)),
                () -> assertEquals(-1, brs.getBicycle(1, 0, 0)));
    }

    @Test
    public void testReturnBicycle() throws UserDoesNotExists {

        //Testar se é lançada a exceção UserDoesNotExists, caso não haja um utilizador com o id introduzido
        assertThrows(UserDoesNotExists.class, () -> {
            brs.getBicycle(0, 1, 0);
        }, "Should Throw Exception: UserDoesNotExists");

        //Testar se retorna -1 se depósito não existir
        assertEquals(-1, brs.getBicycle(0, 0, 0));

        //Testar se retorna -1 se uma bicicleta não está associada a aluguer ativo
        //assertEquals(-1, brs.bicycleRentalFee(0,0,1,0));

        //Testar se retorna -1 se se não existirem lugares de entrega livre
        assertEquals(-1, brs.returnBicycle(0, 1, 1));

        //Testar se retorna o saldo atual do cliente, quando se calcula o pagamento

    }

    @Test
    public void testBicycleRentalFee() {

    }

}



