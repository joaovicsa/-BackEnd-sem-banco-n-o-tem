/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class SequenceManager {

    public int getValue(String nomeSequencia) throws SQLException {
        ConectorBD conectorBD = new ConectorBD();

        ResultSet rs = conectorBD.getSelect("SELECT NEXT VALUE FOR " + nomeSequencia + ";");
        rs.next();
        int nextValue = rs.getInt(1);
        return nextValue;

    }
}
