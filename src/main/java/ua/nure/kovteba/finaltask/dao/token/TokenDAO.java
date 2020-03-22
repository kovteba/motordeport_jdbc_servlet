package ua.nure.kovteba.finaltask.dao.token;

import ua.nure.kovteba.finaltask.entity.Token;

public interface TokenDAO {

    String createToken(Long id);

    Token getTokenByToken(String token);

}
