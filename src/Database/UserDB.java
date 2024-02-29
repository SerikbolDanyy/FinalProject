package Database;

import Connector.Blog;
import Connector.Commet;
import Connector.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/post", "postgres", "postgre");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserById(Long id){
        User user = null;
        try{

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getLong("id"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("fullName"));
            }
            preparedStatement.close();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public static User getUser(String email){
        User user = null;
        try{

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getLong("id"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("fullName"));
            }
            preparedStatement.close();

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void addUser(String email, String password, String fullName){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(email, password, fullName)" + "VALUES (?, ?, ?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullName);
            preparedStatement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void addPost(long userId, String title, String content) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO blogs(userid, title, content, postdate)" + "VALUES (?, ?, ?, NOW())");
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, content);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static List<Blog> getBlog(){
        List<Blog> blogs = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM blogs");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long userId = resultSet.getLong("userid");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String postDate = String.valueOf(resultSet.getTimestamp("postdate"));
                Blog blog = new Blog();
                User user = new User();
                blog.setUser(UserDB.getUserById(userId));
                blog.setContent(content);
                blog.setTitle(title);
                blog.setPostDate(postDate);
                blogs.add(blog);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return blogs;
    }

    public static void addComment(Commet comment){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO comment (userid, blogid, comment, postdate) " + "VALUES (?, ?, ?, NOW())");

            statement.setLong(1, comment.getUser().getId());
            statement.setLong(2, comment.getBlog().getId());
            statement.setString(3, comment.getComment());
            statement.executeUpdate();

            statement.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Commet> getAllComments(Long blogId){
        ArrayList<Commet> comments = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT c.id, c.user_id, c.comment, c.blog_id, u.full_name, u.email, c.post_date " + "FROM comments c " + "INNER JOIN users u ON c.user_id = u.id " + "WHERE c.blog_id = ? " + "ORDER BY c.post_date DESC");
            statement.setLong(1, blogId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Commet comment = new Commet();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));

                comment.setUser(user);
                Blog blog = new Blog();
                blog.setId(resultSet.getLong("blog_id"));
                comment.setBlog(blog);
                comments.add(comment);
            }
            statement.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return comments;
    }

}
