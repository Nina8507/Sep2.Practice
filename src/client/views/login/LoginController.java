package client.views.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import shared.transfer.User;
import shared.transfer.UserAction;

import java.beans.PropertyChangeEvent;

public class LoginController implements ViewController
{
  @FXML private TextField usernameTextField;
  @FXML private TextField passwordTextField;
  @FXML private Button onLoginButton;

  private ViewHandler viewHandler;
  private LoginVM loginVM;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.loginVM = viewModelFactory.getLoginVM();
    usernameTextField.textProperty()
        .bindBidirectional(loginVM.usernameProperty());
    passwordTextField.textProperty()
        .bindBidirectional(loginVM.passwordTextProperty());
    loginVM.addListener(UserAction.LOGIN_RESULT.toString(), this::onLoginRequest);
  }

  private void onLoginRequest(PropertyChangeEvent evt)
  {

      System.out.println("IM HERE");
//      User user = (User) evt.getNewValue();
      viewHandler.openMainViewScene();
//      if(UserAction.LOGIN_RESULT.toString().equals(UserAction.LOGIN_REQUEST_SECRETARY.toString()))
//      {
//        //viewHandler.openSecretaryView();
//      } else if(UserAction.LOGIN_RESULT.toString().equals(UserAction.LOGIN_REQUEST_EMPLOYEE.toString()))
//      {
//        //viewHandler.openEmployeeView();
//      }else if(UserAction.LOGIN_REQUEST_MANAGER.toString().equals(UserAction.LOGIN_REQUEST_MANAGER.toString()))
//      {
//        viewHandler.openMainViewScene();
//      }

  }

  public void onLoginButton(ActionEvent evt)
  {
    loginVM.login();
  }

}
