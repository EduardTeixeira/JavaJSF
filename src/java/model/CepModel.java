package model;

import fabrica.FabricaDeConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mapper.Cep;

public class CepModel {

    Connection con;

    public CepModel() {
        FabricaDeConexao.Conectar();
        con = FabricaDeConexao.con;
    }

    public void verificaCon() {
        try {
            if (!con.isValid(0)) {
                FabricaDeConexao.Conectar();
                con = FabricaDeConexao.con;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insert(Cep c) throws SQLException {
        verificaCon();
        String sql = "insert into cep (cep, rua, numero, bairro, cidade, estado) values (?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, c.getCep().toString());
        stmt.setString(2, c.getRua());
        stmt.setString(3, c.getNumero().toString());
        stmt.setString(4, c.getBairro());
        stmt.setString(5, c.getCidade());
        stmt.setString(6, c.getEstado());
        stmt.execute();
        stmt.close();
        System.out.println("Gravado!");
        con.close();
    }

    public void update(Cep c) throws SQLException {
        verificaCon();
        String sql = "update cep set cep = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ? where id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, c.getCep().toString());
        stmt.setString(2, c.getRua());
        stmt.setString(3, c.getNumero().toString());
        stmt.setString(4, c.getBairro());
        stmt.setString(5, c.getCidade());
        stmt.setString(6, c.getEstado());
        stmt.setString(7, c.getId().toString());
        stmt.execute();
        stmt.close();
        System.out.println("Atualizado!");
        con.close();
    }

    public void delete(Cep c) throws SQLException {
        verificaCon();
        String sql = "delete from cep where id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, c.getId().toString());
        stmt.execute();
        stmt.close();
        System.out.println("Excluido!");
        con.close();
    }

    public ArrayList<Cep> getCeps() {
        verificaCon();
        ArrayList<Cep> cs = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from cep");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cep c = new Cep();
                c.setId(rs.getInt("id"));
                c.setRua(rs.getString("rua"));
                c.setNumero(rs.getInt("numero"));
                c.setCep(rs.getInt("cep"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
                cs.add(c);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cs;
    }

    public Cep getCep(Integer idCep) {
        verificaCon();
        Cep c = new Cep();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from cep where id = " + idCep);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setRua(rs.getString("rua"));
                c.setNumero(rs.getInt("numero"));
                c.setCep(rs.getInt("cep"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.setEstado(rs.getString("estado"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

}
