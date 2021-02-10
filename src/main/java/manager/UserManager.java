package manager;


import db.DBConnectionProvider;
import model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManager {

  private Connection connection = DBConnectionProvider.getInstance().getConnection();

  public boolean addUser(User user){
      String sql = "INSERT INTO user(name,surname,email,password) VALUES(?,?,?,?)";
      try {
          PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          statement.setString(1, user.getName());
          statement.setString(2, user.getSurname());
          statement.setString(3, user.getEmail());
          statement.setString(4, user.getPassword());
          statement.executeUpdate();

          ResultSet rs = statement.getGeneratedKeys();
          if (rs.next()){
              user.setId(rs.getLong(1));
          }
          return true;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }

  }

  public User getUserById(int id){
      String sql = "SELECT * FROM user WHERE id =" + id;
      try {
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);
          if (resultSet.next()){
              User user = new User();
              user.setId(resultSet.getLong(1));
              user.setName(resultSet.getString(2));
              user.setSurname(resultSet.getString(3));
              user.setEmail(resultSet.getString(4));
              user.setPassword(resultSet.getString(5));
              return user;

          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return null;
  }

  public void removeUserById(int id){
      String query = "DELETE FROM user WHERE id=" + id;
      try {
          Statement statement = connection.createStatement();
          statement.executeUpdate(query);
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

    public User getUserByEmailAndPassword(String email, String password){
        String sql = "SELECT * FROM user WHERE email = '"+email + "' AND password = '"+ password + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                return user;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getAllUsers(){
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
