package ua.nure.kovteba.finaltask.dao.token;

import org.junit.jupiter.api.Test;
import ua.nure.kovteba.finaltask.entity.Token;

import static org.junit.jupiter.api.Assertions.*;

class TokenDAOImplTest {

    private static TokenDAOImpl tokenDAO = new TokenDAOImpl();

    @Test
    void createToken() {
        String newToken = tokenDAO.createToken(1L);
        System.out.println(newToken);
    }

    @Test
    void getTokenByToken() {
        Token token = tokenDAO.getTokenByToken("ce694b2b-d578-41aa-9e16-9e4fccf3d8b6");
    }

    @Test
    void deleteTokenByToken() {
        tokenDAO.deleteTokenByToken("ce694b2b-d578-41aa-9e16-9e4fccf3d8b6");
    }
}