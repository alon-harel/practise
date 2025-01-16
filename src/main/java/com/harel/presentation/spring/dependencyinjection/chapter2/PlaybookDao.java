package com.harel.presentation.spring.dependencyinjection.chapter2;

import com.harel.presentation.spring.Playbook;

public class PlaybookDao {
    public Playbook readPlaybookById(long playbookId) {
        /*
        String url = "jdbc:postgresql://localhost:5432/your_database";
        String user = "your_username";
        String password = "your_password";

        String query = "SELECT id, name, ... FROM playbook";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            Playbook playbook;

            // Row mapper-like approach
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                // Map row to Playbook object
                playbook = new Playbook(id, name, ...);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        return new Playbook();
    }
}
