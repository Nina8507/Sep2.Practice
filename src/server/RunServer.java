package server;

import server.model.addsuppliermodel.AddSupplierServerModel;
import server.model.addsuppliermodel.AddSupplierServerModelManager;
import server.model.adminmodel.AdminServerModel;
import server.model.adminmodel.AdminServerModelManager;
import server.model.supplierModel.SupplierServerModel;
import server.model.supplierModel.SupplierServerModelManager;
import server.model.usermodel.UserServerModel;
import server.model.usermodel.UserServerModelManager;
import server.networking.SocketServer;

import java.sql.SQLException;

public class RunServer
{
  public static void main(String[] args)
  {
    UserServerModel userServerModel = new UserServerModelManager();
    AdminServerModel adminServerModel = new AdminServerModelManager();
    SupplierServerModel supplierServerModel = new SupplierServerModelManager();
    AddSupplierServerModel addSupplierServerModel = new AddSupplierServerModelManager();
    SocketServer socketServer = new SocketServer(userServerModel, adminServerModel, supplierServerModel, addSupplierServerModel);
    socketServer.startServer();
  }
}
