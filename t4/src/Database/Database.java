
package Database;

import Auth.CertificateHelper;
import Auth.User;

import javax.xml.bind.DatatypeConverter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class Database {

    public Connection conn = null;

    private static Database database = null;
    private HashMap<Integer, String> Map;
    private final int loginCode = 1;


    // singleton
    private Database() {}

    private static HashMap<Integer, String> initMap(){
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1001, "Sistema iniciado");
        map.put(1002, "Sistema encerrado");
        map.put(2001, "Autenticação etapa 1 iniciada");
        map.put(2002, "Autenticação etapa 1 encerrada");
        map.put(2003, "Login name identificado com acesso liberado.");
        map.put(2004, "Login name identificado com acesso bloqueado.");
        map.put(2005, "Login name <login_name> não identificado.");
        map.put(3001, "Autenticação etapa 2 iniciada");
        map.put(3002, "Autenticação etapa 2 encerrada");
        map.put(3003, "Senha pessoal verificada positivamente");
        map.put(3004, "Primeiro erro da senha pessoal contabilizado");
        map.put(3005, "Segundo erro da senha pessoal contabilizado");
        map.put(3006, "Terceiro erro da senha pessoal contabilizado");
        map.put(3007, "Acesso do usuario bloqueado pela autenticação etapa 2");
        map.put(4001, "Autenticação etapa 3 iniciada");
        map.put(4002, "Autenticação etapa 3 encerrada");
        map.put(4003, "Chave privada verificada positivamente");
        map.put(4004, "Chave privada verificada negativamente (caminho inválido)");
        map.put(4005, "Chave privada verificada negativamente (frase secreta inválida)");
        map.put(4006, "Chave privadaverificada negativamente (assinatura digital inválida)");
        map.put(4007, "Acesso do usuario bloqueado pela autenticação etapa 3.");
        map.put(5001, "Tela principal apresentada");
        map.put(5002, "Opção 1 do menu principal selecionada");
        map.put(5003, "Opção 2 do menu principal selecionada");
        map.put(5004, "Opção 3 do menu principal selecionada");
        map.put(5005, "Opção 4 do menu principal selecionada");
        map.put(6001, "Tela de cadastro apresentada");
        map.put(6002, "Botão cadastrar pressionado ");
        map.put(6003, "Senha pessoal inválida fornecida ");
        map.put(6004, "Caminho do certificado digital inválido ");
        map.put(6005, "Confirmação de dados aceita ");
        map.put(6006, "Confirmação de dados rejeitada ");
        map.put(6007, "Botão voltar de cadastropara o menu principal pressionado ");
        map.put(7001, "Tela de alteração da senha pessoal e certificado apresentada");
        map.put(7002, "Senha pessoal inválida fornecida ");
        map.put(7003, "Caminho do certificado digital inválido fornecido");
        map.put(7004, "Confirmação de dados aceita");
        map.put(7005, "Confirmação de dados rejeitada");
        map.put(7006, "Botão voltar de carregamento para o menu principal pressionado");
        map.put(8001, "Tela de consulta de arquivos secretos apresentada");
        map.put(8002, "Botão voltar de consulta para o menu principal pressionado");
        map.put(8003, "Botão Listar de consulta pressionado");
        map.put(8004, "Caminho de pasta inválido fornecido");
        map.put(8005, "Arquivo de índicedecriptado com sucesso ");
        map.put(8006, "Arquivo de índiceverificado (integridade e autenticidade) com sucesso");
        map.put(8007, "Falha na decriptação do arquivo de índice");
        map.put(8008, "Falha na verificação (integridade e autenticidade) do arquivo de índice");
        map.put(8009, "Lista de arquivos presentes no índice apresentada");
        map.put(8010, "Arquivo selecionado para decriptação");
        map.put(8011, "Acesso permitido ao arquivo");
        map.put(8012, "Acesso negado ao arquivo");
        map.put(8013, "Arquivo decriptado com sucesso");
        map.put(8014, "Arquivo verificado (integridade e autenticidade) com sucesso");
        map.put(8015, "Falha na decriptação do arquivo");
        map.put(8016, "Falha na verificação (integridade e autenticidade) do arquivo");
        map.put(9001, "Tela de saída apresentada");
        map.put(9002, "Saída não liberada por falta de one-time password para");
        map.put(9003, "Botão sair pressionado por");
        map.put(9004, "Botão voltar de sair para o menu principal pressionado");

        return map;
    }

    public static Database getInstance() throws Exception {
        if(database != null)
            return database;

        database = new Database();

        String url = "jdbc:sqlite:database/vault.db";

        database.conn = DriverManager.getConnection(url);
        database.Map = initMap();
        return database;
    }

    private static void logRegistry(Registry registry) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("INSERT INTO registry(timestamp, user_id, event_code) VALUES (?, ?, ?)");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, registry.Timestamp);
        if (registry.UserEmail != null){
            ps.setInt(2, User.GetUserID(registry.UserEmail));
        }
        else {
            ps.setInt(2, -1);
        }
        ps.setInt(3, registry.Code);

        ps.executeUpdate();
    }

    private static void logMessage(Registry registry) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("INSERT INTO messages(timestamp, user_id, content) VALUES (?, ?, ?)");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, registry.Timestamp);
        if (registry.UserEmail != null){
            ps.setInt(2, User.GetUserID(registry.UserEmail));
        }
        else{
            ps.setInt(2, -1);
        }
        ps.setString(3, db.Map.get(registry.Code));

        ps.executeUpdate();
    }

    public static void log(Registry registry) throws Exception {
        logRegistry(registry);
        logMessage(registry);
    }

    public void incAccessCounter(String email) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET access_counter = access_counter + 1 WHERE email=?;");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ps.executeUpdate();
        ps.close();
    }

    public void incBlockCounter(String email) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET blocked_counter = blocked_counter +1 WHERE email=?;");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ps.executeUpdate();
        ps.close();
    }

    public void clearBlockedCounter(String email) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET blocked_counter = 0 WHERE email=?;");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ps.executeUpdate();
        ps.close();
    }

    public void insertBlocked(String email, long time) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET blocked_milliseconds = ? WHERE email=?;");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setLong(1, time);
        ps.setString(2, email);
        ps.executeUpdate();
        ps.close();
    }

    public int getBlockedCount(String email) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("Select blocked_counter FROM users WHERE email=?;");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        int blockedCount =  rs.getInt("blocked_counter");
        ps.close();
        return blockedCount;
    }

    public long getBlockedMilli(String email) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("Select blocked_milliseconds FROM users WHERE email=?;");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        long blockedMilli =  rs.getLong("blocked_milliseconds");
        ps.close();
        return blockedMilli;
    }

    public String getGroupName(int id) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("SELECT name FROM groups WHERE id = ?;");

        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        String name = resultSet.getString("name");
        resultSet.close();
        ps.close();

        return name;
    }

    public ResultSet getUser(String email) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("SELECT * FROM users WHERE email = ?;");

        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    public ResultSet getUsersCount() throws Exception {
        String query = String.format("SELECT COUNT(*) FROM users;");
        PreparedStatement ps = Database.getInstance().conn.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    public int getEmailCount(String email) throws Exception {
        Database db = Database.getInstance();
        String query = "SELECT COUNT(email) FROM users WHERE email=?;";
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ResultSet resultSet = ps.executeQuery();
        int count = resultSet.getInt("COUNT(email)");

        resultSet.close();
        ps.close();
        return count;
    }

    public void insertUser(Auth.User user) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("INSERT INTO users(email,hash,salt,certificate,group_id,uid) VALUES (?,?,?,?,?,?);");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setString(1, user.Email);
        ps.setString(2, DatatypeConverter.printHexBinary(user.Hash));
        ps.setString(3, new String(user.Salt));
        ps.setString(4, CertificateHelper.getCertificateBase64(user.Certificate));
        ps.setInt(5, user.GetGroupID());
        ps.setInt(6, User.GetUserID(user.Email));

        ps.executeUpdate();
        ps.close();
    }

    public void deleteUser(Auth.User user) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("DELETE FROM users WHERE email = ?");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setString(1, user.Email);

        ps.executeUpdate();
        ps.close();
    }

    public void insertGroup(String groupString, int groupID) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("INSERT INTO groups(id,name) VALUES (?,?);");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setInt(1, groupID);
        ps.setString(2, groupString);

        ps.executeUpdate();
        ps.close();
    }


    public void setCertificate(Auth.User user) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET certificate = ? WHERE email = ?");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setString(1, Auth.CertificateHelper.getCertificateBase64(user.Certificate));
        ps.setString(2, user.Email);

        ps.executeUpdate();
        ps.close();
    }
}