/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Perlt
 */
public class DataMapper {

    private DBConnector dbc;

    public DataMapper() {
        dbc = new DBConnector(new DataSource().getDataSource());
    }

    public void insertScore(String name, int score) {
        String sql = "INSERT INTO highscore(name, score) VALUES (?, ?)";
        try {
            dbc.open();
            PreparedStatement ps = dbc.preparedStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<ScoreEntity> getSortedScoreList(){
        List<ScoreEntity> scoreList = new ArrayList<>();
        
        String sql = "SELECT * FROM highscore";
            
        try {
            dbc.open();
            ResultSet rs = dbc.executeQuery(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("score");
                scoreList.add(new ScoreEntity(name, score, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.sort(scoreList);
        return scoreList;
    }
}
