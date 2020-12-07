package client.core;

import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage mainStage;
  private Scene loginScene;
  private Scene loginResultScene;
  private Scene registerScene;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    mainStage = new Stage();
  }

  public void start()
  {
    openLoginView();
    mainStage.show();
  }

  private void openLoginView()
  {
    try
    {
      if (loginScene == null)
      {
        loginScene = getScene("../views/login/Login.fxml");
      }
      mainStage.setTitle("Login");
      mainStage.setScene(loginScene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void openRegisterView()
  {
    try
    {
      if (registerScene == null)
      {
        registerScene = getScene("../views/register/Register.fxml");
      }
      mainStage.setTitle("Register User");
      mainStage.setScene(registerScene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void openLoggedInSuccessfulView()
  {
    try
    {
      if (loginResultScene == null)
      {
        loginResultScene = getScene("../views/register/LoginResult.fxml");
      }
      mainStage.setTitle("Login Successful");
      mainStage.setScene(loginResultScene);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private Scene getScene(String s) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(s));
    Parent root = loader.load();
    ViewController view = loader.getController();
    view.init(this, viewModelFactory);
    return new Scene(root);
  }

}
