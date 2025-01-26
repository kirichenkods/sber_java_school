package ru.sber.db;

import ru.sber.config.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresDB implements Source {
    private final String TABLE_CACHE_DATA = "public.cache_data";
    private final String KEY_FIELD = "number";
    private final String KEY_SEQUENCE = "sequence";

    public PostgresDB() {
        try (Connection connection = DriverManager.getConnection(
                DBConfig.DB_URL,
                DBConfig.DB_USER,
                DBConfig.DB_PASSWORD)) {
            String sql = "CREATE TABLE IF NOT EXISTS " +
                    TABLE_CACHE_DATA + " (" +
                    KEY_FIELD + " INTEGER PRIMARY KEY, " +
                    KEY_SEQUENCE + " INTEGER[] NOT NULL);";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе: " + e.getMessage());
        }
    }

    @Override
    public void saveSequence(int num, List<Integer> sequence) {
        try (Connection connection = DriverManager.getConnection(DBConfig.DB_URL,
                DBConfig.DB_USER, DBConfig.DB_PASSWORD)) {
            String sql = "INSERT INTO " + TABLE_CACHE_DATA +
                    " (" + KEY_FIELD + ", " + KEY_SEQUENCE + ") VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, num);
            Array array = connection.createArrayOf("Integer", sequence.toArray());
            statement.setArray(2, array);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе: " + e.getMessage());
        }
    }

    @Override
    public List<Integer> getSequence(int num) {
        List<Integer> sequence = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(
                DBConfig.DB_URL,
                DBConfig.DB_USER,
                DBConfig.DB_PASSWORD)) {
            String sql = "SELECT " + KEY_SEQUENCE +
                    " FROM " + TABLE_CACHE_DATA + " WHERE " + KEY_FIELD + " = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, num);
            ResultSet rst = stmt.executeQuery();
            if (rst.next()) {
                Array array = rst.getArray("sequence");
                sequence = List.of((Integer[]) array.getArray());
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе: " + e.getMessage());
        }
        return sequence;
    }
}
