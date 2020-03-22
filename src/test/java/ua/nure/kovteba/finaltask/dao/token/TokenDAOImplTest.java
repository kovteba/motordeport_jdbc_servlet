package ua.nure.kovteba.finaltask.dao.token;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.entity.Token;

import static org.junit.jupiter.api.Assertions.*;

class TokenDAOImplTest {

    private static TokenDAOImpl tokenDAO = new TokenDAOImpl();

    private static String newToken = null;

    @Test
    void createToken() {
        newToken = tokenDAO.createToken(1L);
    }

    @Test
    void getTokenByToken() {
        Token token = tokenDAO.getTokenByToken(newToken);
        assertEquals(token.getToken(), newToken);
    }
}