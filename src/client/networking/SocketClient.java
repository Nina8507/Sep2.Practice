package client.networking;

import shared.transfer.Request;
import shared.transfer.User;
import shared.transfer.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client
{
  private PropertyChangeSupport support;
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;

  public SocketClient()
  {
    support = new PropertyChangeSupport(this);
    startClient();
  }

  private void startClient()
  {
    try
    {
      System.out.println("Client started...");
      Socket socket = new Socket("localhost", 2910);
      outToServer = new ObjectOutputStream(socket.getOutputStream());
      inFromServer = new ObjectInputStream(socket.getInputStream());
      new Thread(() -> listenToServer()).start();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void listenToServer()
  {
    try
    {
      while (true)
      {
        Request request = (Request) inFromServer.readObject();
        support.firePropertyChange(request.getRequestType(), null, request.getRequestArg());
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void login(User user)
  {
    Request request = new Request(UserAction.LOGIN_REQUEST.toString(), user);
    try
    {
      outToServer.writeObject(request);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addListener(String name,
      PropertyChangeListener listener)
  {
    if (name == null || "".equals(name))
      addListener(listener);
    else
    {
      support.addPropertyChangeListener(name, listener);
    }
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void removeListener(String name,
      PropertyChangeListener listener)
  {
    if (name == null || "".equals(name))
      removeListener(listener);
    else
    {
      support.removePropertyChangeListener(name, listener);
    }
  }
}