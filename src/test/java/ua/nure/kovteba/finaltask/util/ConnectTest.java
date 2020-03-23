package ua.nure.kovteba.finaltask.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectTest {

    @Test
    void connect() {
        Connection connection = Connect.connect();
    }
}