package dao;

import model.Clients;
/**

 The ClientsBLL class provides business logic operations for manipulating client data.
 It interacts with the ClientsDAO class for data access and the GUI classes for user interface interaction.
 */
public class ClientsDAO extends AbstractDAO<Clients> {
    public ClientsDAO(){
        this.identifier=1;
    }
    @Override
    protected String createInsertStatement(Clients clients) {
        StringBuilder sb=new StringBuilder();
        sb.append("insert into clients values( ");
        sb.append(clients.getId()).append(",");
        sb.append("'").append(clients.getName()).append("',");
        sb.append("'").append(clients.getAddress()).append("',");
        sb.append(clients.getAge()).append(")");

        return sb.toString();
    }

    @Override
    protected String createUpdateStatement(Clients clients) {
        StringBuilder sb=new StringBuilder();
        sb.append("UPDATE clients SET name='").append(clients.getName()).append("'");
        sb.append(", address='").append(clients.getAddress()).append("'");
        sb.append(", age=").append(clients.getAge());
        sb.append(" where id=").append(clients.getId());
        return sb.toString();
    }
}
