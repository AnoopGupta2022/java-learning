import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManagement {
       
    public static void main(String[] args)
    {
        Users users = new Users(new ArrayList<>());
        users.addUser(new User(1, "Alice"));
        users.addUser(new User(2, "Bob"));
        users.addUser(new User(3, "Charlie"));
        users.addUser(new User(4, "Anoop"));
        users.addUser(new User(5, "Amit"));
        System.out.println("Update Status: " + (users.updateNameById(3, "New Charlie") ? "Success" : "Failure"));
        Optional<Response> response1 = users.findUserById(3);
        Optional<Response> response2 = users.findUserById(6);
        System.out.println("Status: " + (response1.isEmpty() ? "404" : response1.get().getStatusCode()) + " , Message " + (response1.isEmpty() ? "Not Found" : response1.get().getMessage()));
        System.out.println("Status: " + (response2.isEmpty() ? "404" : response2.get().getStatusCode()) + " ,  Message " + (response2.isEmpty() ? "Not Found" : response2.get().getMessage()));

    }

}
 

class User {
    private final int id;
    private  String name;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }   
    public String getName() {
        return name;
    } 
    public void setName(String name) {
        this.name = name;
    }

 
}
class Response {
    private final String statusCode;
    private final String message;
    public Response (String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public String getMessage() {
        return message;
    }

}


class Users {
    List<User> users;
    public Users(List<User> users){
        this.users = users;
    } 
    public void addUser(User user) {
        users.add(user);
    }  
    public Optional<Response> findUserById (int id)
    {
      return  users.stream().filter(user -> user.getId() == id)
        .findFirst()
        .map(user -> new Response("200", user.getName() + " found"));
    }   

    public boolean updateNameById(int id, String newName)
    {
        
        // return users.stream().filter(user-> user.getId() == id)
        // .findFirst()
        // .map(user -> {user.setName(newName);  return true;})
        // .orElse(false);
        Optional<User> userOptional = users.stream().filter(user-> user.getId() == id)
                                        .findFirst();

        if (userOptional.isPresent()){
            userOptional.get().setName(newName);
        }
        return false;
    }
}