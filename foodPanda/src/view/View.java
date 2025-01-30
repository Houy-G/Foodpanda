package view;

import controller.Controller;
import model.User;

public class View extends Controller {
    User user=new User();

    @Override
    public void login() {
        super.login();
    }
    public View(){
        login();
    }
}
