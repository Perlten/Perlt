package database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataMapper {
    
    private DBConnector DBCon;

    public DataMapper() {
        DBCon = new DBConnector(new DataSource().getDataSource());
    }
    
    public void insertNewScore(String username, int score){
        String sql = "insert into highscore(name, score) values(?, ?);";
         try {
            DBCon.open();
            PreparedStatement ps = DBCon.preparedStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, score);
            ps.execute();
            DBCon.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<ScoreEntity> getSortedScoreList(){
        List<ScoreEntity> list = new ArrayList<>();
        String sql = "select * from highscore;";
        
        try {
            DBCon.open();
            ResultSet resultSet = DBCon.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("highscoreId");
                String username = resultSet.getString("name");
                int score = resultSet.getInt("score");
                
                list.add(new ScoreEntity(id, username, score));
            }
            DBCon.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Collections.sort(list);
        return list;
    }
}
