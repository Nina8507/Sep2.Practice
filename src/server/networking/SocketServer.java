package server.networking;

import server.model.addsuppliermodel.AddSupplierServerModel;
import server.model.adminmodel.AdminServerModel;
import server.model.supplierModel.SupplierServerModel;
import server.model.usermodel.UserServerModel;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer
{
  private UserServerModel userServerModel;
  private AdminServerModel adminServerModel;
  private SupplierServerModel supplierServerModel;
  private AddSupplierServerModel addSupplierServerModel;

  public SocketServer(UserServerModel userServerModel, AdminServerModel adminServerModel, SupplierServerModel supplierServerModel, AddSupplierServerModel addSupplierServerModel)
  {
    this.userServerModel = userServerModel;
    this.adminServerModel = adminServerModel;
    this.supplierServerModel = supplierServerModel;
    this. addSupplierServerModel = addSupplierServerModel;
  }

  public void startServer()
  {
    {
      try
      {
        ServerSocket serverSocket = new ServerSocket(2910);
        System.out.println("Starting server...");

        while (true)
        {
          Socket socket = serverSocket.accept();
          ServerSocketHandler socketHandler = new ServerSocketHandler(socket,
              userServerModel, adminServerModel, supplierServerModel, addSupplierServerModel);

          Thread t = new Thread(socketHandler);
          t.start();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
