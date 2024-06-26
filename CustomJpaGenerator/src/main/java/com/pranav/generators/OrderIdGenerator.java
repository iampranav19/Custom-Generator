package com.pranav.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderIdGenerator implements IdentifierGenerator {

    private static final long serialVersionUID = 9166829337601676302L;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String prefix = "OD_";
        String suffix = "";
        Connection connection = null;
        
        try {
            connection = session
                .getJdbcConnectionAccess()
                .obtainConnection();
            
            try (PreparedStatement ps = connection.prepareStatement("SELECT ORDER_ID_SEQ.NEXTVAL FROM DUAL")) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int seqVal = rs.getInt(1);
                    suffix = String.format("%03d", seqVal);  // Format the sequence value with leading zeros
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to generate order ID", e);
        } finally {
            if (connection != null) {
                try {
                    session
                        .getJdbcConnectionAccess()
                        .releaseConnection(connection);
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to release connection", e);
                }
            }
        }

        return prefix + suffix;
    }
}
