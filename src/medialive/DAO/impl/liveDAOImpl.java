package medialive.DAO.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import medialive.DAO.ILiveDAO;
import medialive.domain.live;

public class liveDAOImpl implements ILiveDAO {
	private String driverClassName = "com.mysql.jdbc";
	private String url = "jdbc:mysql:///test";
	private String userName = "root";
	private String password = "Gaoji1996";

	static {
		try {
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			Properties p = new Properties();
			p.load(in);
			Class.forName(p.getProperty("driverClassName"));
			System.out.println("live数据库驱动初始化完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置连接数据库所必须的url,username,password
	 */
	public void init() {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			url = p.getProperty("url");
			userName = p.getProperty("userName");
			password = p.getProperty("password");
			System.out.println("设置链接参数");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean save(live liveDemo) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("INSERT INTO live(roomName,streamName,red5URL)\r\n"
					+ "                       VALUES\r\n" + "                       (?,?,?)\r\n"
					+ "			ON DUPLICATE KEY UPDATE streamName =streamName;");
			ps.setString(1, liveDemo.getRoomName());
			ps.setString(2, liveDemo.getStreamName());
			ps.setString(3, liveDemo.getRed5URL());
			ps.executeUpdate();
			System.out.println("增加live信息完成");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean clear() {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("delete from live");
			ps.executeUpdate();
			System.out.println("清空live信息");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public boolean deleteByroom(String roomName) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("DELETE FROM live\r\n" + "WHERE roomName =?;");
			ps.setString(1, roomName);
			ps.executeUpdate();
			System.out.println("删除房间名为" + roomName + "的live信息");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteBystream(String streamName) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("DELETE FROM live\r\n" + "WHERE streamName =?;");
			ps.setString(1, streamName);
			ps.executeUpdate();
			System.out.println("删除直播流名为" + streamName + "的live信息");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public boolean updateLive(live liveDemo) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn
					.prepareStatement("UPDATE live  \r\n" + "SET roomName=?\r\n" + "WHERE streamName=?");
			ps.setString(1, liveDemo.getRoomName());
			ps.setString(2, liveDemo.getStreamName());
			ps.executeUpdate();
			System.out.println("直播流" + liveDemo.getStreamName() + "的房间名被改为:" + liveDemo.getRoomName());
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public live getByid(Long id) {
		// TODO Auto-generated method stub
		live liveDemo = new live();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT * \r\n" + "FROM live  \r\n" + "WHERE  id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				System.out.println("没有id为" + id + "的直播间");
			} else {
				liveDemo.setId(rs.getLong("id"));
				liveDemo.setRoomName(rs.getString("roomName"));
				liveDemo.setStreamName(rs.getString("streamName"));
				liveDemo.setRed5URL(rs.getString("red5URL"));
				System.out.println("id为" + id + "的直播间信息已经被查出来了!");
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liveDemo;

	}

	@Override
	public live getBystream(String streamName) {
		// TODO Auto-generated method stub
		live liveDemo = new live();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT * \r\n" + "FROM live  \r\n" + "WHERE  streamName=?");
			ps.setString(1, streamName);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				System.out.println("没有流名称为" + streamName + "的直播间");
			} else {
				liveDemo.setId(rs.getLong("id"));
				liveDemo.setRoomName(rs.getString("roomName"));
				liveDemo.setStreamName(rs.getString("streamName"));
				liveDemo.setRed5URL(rs.getString("red5URL"));
				System.out.println("流名称为" + streamName + "的直播间信息已经被查出来了!");
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liveDemo;
	}

	@Override
	public live getByroom(String roomName) {
		// TODO Auto-generated method stub
		live liveDemo = new live();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT * \r\n" + "FROM live  \r\n" + "WHERE  roomName=?");
			ps.setString(1, roomName);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				System.out.println("没有房间名称为" + roomName + "的直播间");
			} else {
				liveDemo.setId(rs.getLong("id"));
				liveDemo.setRoomName(rs.getString("roomName"));
				liveDemo.setStreamName(rs.getString("streamName"));
				liveDemo.setRed5URL(rs.getString("red5URL"));
				System.out.println("房间名称为" + roomName + "的直播间信息已经被查出来了!");
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liveDemo;
	}

	@Override
	public List<live> list() {
		// TODO Auto-generated method stub
		List<live> liveDemos = new ArrayList<live>();

		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT *\r\n" + "FROM live");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				live liveDemo = new live();
				liveDemo.setId(rs.getLong("id"));
				liveDemo.setRoomName(rs.getString("roomName"));
				liveDemo.setStreamName(rs.getString("streamName"));
				liveDemo.setRed5URL(rs.getString("red5URL"));
				liveDemos.add(liveDemo);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liveDemos;
	}

}
