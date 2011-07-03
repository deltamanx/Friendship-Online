package lib.sql;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lib.game.PStats;
import lib.game.Player;
import lib.game.Stats;
import lib.game.Unit;
import lib.world.World;

public class SQLManager
{
	private Connection con;
	
	public SQLManager() throws SQLException, ClassNotFoundException
	{
	    String driver = "com.mysql.jdbc.Driver";
	    String connection = "jdbc:mysql://localhost:3306/fonline";
	    String user = "root";
	    String password = "catacon";
	    Class.forName(driver);
	    con = DriverManager.getConnection(connection, user, password);
	}
	
	public PreparedStatement createStatement(String state) throws SQLException
	{
		return getCon().prepareStatement(state);
	}
	
	public void savePlayer(Player p) throws SQLException
	{
		String state =  "DELETE FROM player WHERE name='" + p.getName() + "';";
		PreparedStatement pstmt = createStatement(state);
		pstmt.execute();
		state = "INSERT INTO player (name,exp,level,xPos,yPos,scale,faction,stats,pStats)" +
				" VALUES (?,?,?,?,?,?,?,?,?)";
		pstmt = createStatement(state);;
		pstmt.setString(1, p.getName());
		pstmt.setLong(2, p.getEXP());
		pstmt.setInt(3, p.getLevel());
		pstmt.setFloat(4, p.getX());
		pstmt.setFloat(5, p.getY());
		pstmt.setFloat(6, p.getScale());
		pstmt.setInt(7, p.getFaction());
		pstmt.setObject(8, p.getStats());
		pstmt.setObject(9, p.getPStats());
		pstmt.execute();
	}

	public void saveAll(World w) throws SQLException
	{
		for(int i = 0; i < w.getWorld().size(); ++i)
			if(w.getObjectAt(i) instanceof Player)
				savePlayer((Player)w.getObjectAt(i));
	}
	
	public Player loadPlayer(String playerName) throws SQLException, IOException,
	ClassNotFoundException
	{
		String state = "SELECT * FROM player WHERE name='" + playerName + "'";
		ResultSet r = createStatement(state).executeQuery();
		if(r.first())
		{
			Stats s = new Stats();
			PStats ps = new PStats();
			s = (Stats) (new ObjectInputStream(
					r.getBlob("stats").getBinaryStream())).readObject();
			ps = (PStats) (new ObjectInputStream(
					r.getBlob("pStats").getBinaryStream())).readObject();
			return new Player(r.getString("name"), r.getInt("level"), r.getLong("exp"),
					r.getFloat("xPos"), r.getFloat("yPos"), r.getFloat("scale"), s,
					r.getInt("faction"), null, null, null, null, ps, null);
		}
		return null;
	}
	
	public void saveCreature(Unit u) throws SQLException
	{
		String state =  "DELETE FROM npc WHERE name='" + u.getName() + "';";
		PreparedStatement pstmt = createStatement(state);
		pstmt.execute();
		state = "INSERT INTO npc (name,level,xPos,yPos,scale,visible,mobile,faction,stats)" +
				" VALUES (?,?,?,?,?,?,?,?,?)";
		pstmt = createStatement(state);;
		pstmt.setString(1, u.getName());
		pstmt.setInt(2, u.getLevel());
		pstmt.setFloat(3, u.getX());
		pstmt.setFloat(4, u.getY());
		pstmt.setFloat(5, u.getScale());
		pstmt.setBoolean(6, u.isVisible());
		pstmt.setBoolean(7, u.isMobile());
		pstmt.setInt(8, u.getFaction());
		pstmt.setObject(9, u.getStats());
		pstmt.execute();
	}
	
	public Unit loadCreature(String name)
	{
		return null;
	}
	
	public Unit loadCreature(int id) throws SQLException, IOException, ClassNotFoundException
	{
		String state = "SELECT * FROM npc WHERE guid=" + id;
		ResultSet r = createStatement(state).executeQuery();
		if(r.first())
		{
			Stats s = new Stats();
			s = (Stats) (new ObjectInputStream(
					r.getBlob("stats").getBinaryStream())).readObject();
			return new Unit(r.getString("name"), r.getInt("level"), r.getFloat("xPos"),
					r.getFloat("yPos"), r.getFloat("scale"), r.getBoolean("visible"), s,
					r.getBoolean("mobile"), r.getInt("faction"));
		}
		return null;
	}
	
	public void loadWorld(World w) throws SQLException, IOException, ClassNotFoundException
	{
		String state = "SELECT guid FROM npc";
		ResultSet r = createStatement(state).executeQuery();
		while(r.next())
		{
			w.addObject(loadCreature(r.getInt(1)));
			System.out.println("Loaded");
		}
	}
	
	public void setCon(Connection con)
	{
		this.con = con;
	}
	
	public Connection getCon()
	{
		return con;
	}
	
	//Testing only
	public static void main (String [] args) throws SQLException, ClassNotFoundException
	{
		SQLManager sql = new SQLManager();
		try
		{
			Unit u = new Unit("Test npc 4", 10, 10.0F, 0.0F, 1.0F, true, new Stats(), true, 1);
			sql.saveCreature(u);
			World w = new World();
			Player p = sql.loadPlayer("Player");
			System.out.println(p.getName());
			w.addObject(p);
			sql.loadWorld(w);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
