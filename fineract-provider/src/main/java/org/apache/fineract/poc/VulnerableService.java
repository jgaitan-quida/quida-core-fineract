package org.apache.fineract.poc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableService {

    // Hardcoded secret - Aikido Secrets scan debería detectar esto
    private static final String API_KEY = "AKIAIOSFODNN7EXAMPLE";
    private static final String DB_PASSWORD = "SuperSecret123!";

    // SQL Injection - Aikido SAST debería detectar esto
    public ResultSet findClientByName(Connection conn, String name) throws Exception {
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM clients WHERE name = '" + name + "'";
        return stmt.executeQuery(query);
    }

    // Path traversal
    public byte[] readFile(String filename) throws Exception {
        java.io.File file = new java.io.File("/data/" + filename);
        return java.nio.file.Files.readAllBytes(file.toPath());
    }
}
