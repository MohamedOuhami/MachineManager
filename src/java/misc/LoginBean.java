package misc;


import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private boolean loggedIn;
    private boolean admin;

    // Add a method to get the HttpSession
    private HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String login() {
        // Check username and password (replace this with your authentication logic)
        if ("admin".equals(username) && "admin".equals(password)) {
            this.setLoggedIn(true);
            this.setAdmin(true);

            // Set username in the session
            getSession().setAttribute("username", username);
            getSession().setAttribute("loggedIn", loggedIn);

            return "/web/machine/List.xhtml?faces-redirect=true";
        } else {
            this.setLoggedIn(false);
            FacesMessage message = new FacesMessage("Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
    }

    public String logout() {
        // Perform logout actions
        this.setLoggedIn(false);
        this.setAdmin(false);

        // Invalidate the session
        getSession().invalidate();

        return "/index.xhtml?faces-redirect=true";
    }

    // Getters and setters
}
