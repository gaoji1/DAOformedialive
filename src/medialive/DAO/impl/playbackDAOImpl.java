package medialive.DAO.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import medialive.DAO.IPlaybackDao;
import medialive.domain.playback;

public class playbackDAOImpl implements IPlaybackDao {

	@SuppressWarnings("unused")
	private String driverClassName = "com.mysql.jdbc";
	private String url = "jdbc:mysql:///test";
	private String userName = "root";
	private String password = "Gaoji1996";

	static {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			Class.forName(p.getProperty("driverClassName"));
			System.out.println("数据库驱动加载完成");
		} catch (IOException | ClassNotFoundException e) {
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
			Connection cn = DriverManager.getConnection(url, userName, password);
			Statement sm = cn.createStatement();
			sm.execute("CREATE TABLE IF NOT EXISTS `playback` (\r\n" + 
					"  `id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `streamName` varchar(50) NOT NULL,\r\n" + 
					"  `liveDate` date NOT NULL,\r\n" + 
					"  `fileName` varchar(50) NOT NULL,\r\n" + 
					"  `red5URL` varchar(50) DEFAULT NULL,\r\n" + 
					"  PRIMARY KEY (`id`),\r\n" + 
					"  UNIQUE KEY `fileName` (`fileName`) USING BTREE\r\n" + 
					") ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;");
			System.out.println("表初始化");
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean save(playback playbackDemo) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn
					.prepareStatement("INSERT \r\n" + "INTO playback(streamName,liveDate,fileName,red5URL)\r\n"
							+ "VALUES(?,?,?,?)\r\n" + "ON DUPLICATE KEY UPDATE streamName =streamName;");
			ps.setString(1, playbackDemo.getStreamName());
			ps.setDate(2, new java.sql.Date(playbackDemo.getLiveDate().getTime()));
			ps.setString(3, playbackDemo.getFileName());
			ps.setString(4, playbackDemo.getRed5URL());
			ps.executeUpdate();
			System.out.println("增加playback信息完成");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	/**
	 * 暂时不需要将视频信息全部删除,此方法保留(可能有别的用途)
	 */
	public boolean clean() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByid(Long id) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("DELETE\r\n" + "FROM playback\r\n" + "WHERE id=?");
			ps.setLong(1, id);
			ps.executeUpdate();
			System.out.println("删除id为" + id + "的回放视频信息");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public boolean deleteBystreamName(String streamName) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("DELETE\r\n" + "FROM playback\r\n" + "WHERE streamName=?");
			ps.setString(1, streamName);
			ps.executeUpdate();
			System.out.println("删除流名称为" + streamName + "的回放视频信息");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean deleteByliveDate(Date date) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("DELETE\r\n" + "FROM playback\r\n" + "WHERE liveDate=?");
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ps.executeUpdate();
			System.out.println("删除流保存时间为" + date.toLocaleString() + "的回放视频信息");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteByfileName(String fileName) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("DELETE\r\n" + "FROM playback\r\n" + "WHERE fileName=?");
			ps.setString(1, fileName);
			ps.executeUpdate();
			System.out.println("删除文件名为" + fileName + "的回放视频信息");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public playback getByid(Long id) {
		// TODO Auto-generated method stub
		playback pb = new playback();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT *\r\n" + "FROM playback\r\n" + "WHERE id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pb.setId(rs.getLong("id"));
				pb.setStreamName(rs.getString("streamName"));
				pb.setLiveDate(rs.getDate("liveDate"));
				pb.setFileName(rs.getString("fileName"));
				pb.setRed5URL(rs.getString("red5URL"));
				System.out.println("找到了id为" + id + "的视频信息");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pb;
	}

	@Override
	public List<playback> getBystreamName(String streamName) {
		// TODO Auto-generated method stub
		List<playback> pbList = new ArrayList<>();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT *\r\n" + "FROM playback\r\n" + "WHERE streamName=?");
			ps.setString(1, streamName);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				playback pb = new playback();
				pb.setId(rs.getLong("id"));
				pb.setStreamName(rs.getString("streamName"));
				pb.setLiveDate(rs.getDate("liveDate"));
				pb.setFileName(rs.getString("fileName"));
				pb.setRed5URL(rs.getString("red5URL"));
				pbList.add(pb);
			}
			System.out.println("查找流名称为" + streamName + "的视频信息");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pbList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<playback> getByDate(Date date) {
		// TODO Auto-generated method stub
		List<playback> pbList = new ArrayList<>();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT *\r\n" + "FROM playback\r\n" + "WHERE liveDate=?");
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				playback pb = new playback();
				pb.setId(rs.getLong("id"));
				pb.setStreamName(rs.getString("streamName"));
				pb.setLiveDate(rs.getDate("liveDate"));
				pb.setFileName(rs.getString("fileName"));
				pb.setRed5URL(rs.getString("red5URL"));
				pbList.add(pb);
			}
			System.out.println("查找登记日期为" + date.toLocaleString() + "的视频信息");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pbList;
	}

	@Override
	public playback getByfileName(String fileName) {
		// TODO Auto-generated method stub
		playback pb = new playback();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT *\r\n" + "FROM playback\r\n" + "WHERE fileName=?");
			ps.setString(1, fileName);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pb.setId(rs.getLong("id"));
				pb.setStreamName(rs.getString("streamName"));
				pb.setLiveDate(rs.getDate("liveDate"));
				pb.setFileName(rs.getString("fileName"));
				pb.setRed5URL(rs.getString("red5URL"));
				System.out.println("找到了文件名为" + fileName + "的视频信息");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pb;
	}

	@Override
	public List<playback> list() {
		// TODO Auto-generated method stub
		List<playback> pbList = new ArrayList<>();
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("SELECT *\r\n" + "FROM playback\r\n" );
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				playback pb = new playback();
				pb.setId(rs.getLong("id"));
				pb.setStreamName(rs.getString("streamName"));
				pb.setLiveDate(rs.getDate("liveDate"));
				pb.setFileName(rs.getString("fileName"));
				pb.setRed5URL(rs.getString("red5URL"));
				pbList.add(pb);
			}
			System.out.println("查找全部视频信息");
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pbList;
	}

}
