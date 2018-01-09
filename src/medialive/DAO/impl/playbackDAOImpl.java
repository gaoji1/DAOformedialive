package medialive.DAO.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			System.out.println("���ݿ������������");
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �����������ݿ��������url,username,password
	 */
	public void init() {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			url = p.getProperty("url");
			userName = p.getProperty("userName");
			password = p.getProperty("password");
			System.out.println("�������Ӳ���");
		} catch (IOException e) {
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
			System.out.println("����playback��Ϣ���");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	/**
	 * ��ʱ����Ҫ����Ƶ��Ϣȫ��ɾ��,�˷�������(�����б����;)
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
			System.out.println("ɾ��idΪ" + id + "�Ļط���Ƶ��Ϣ");
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
			System.out.println("ɾ��������Ϊ" + streamName + "�Ļط���Ƶ��Ϣ");
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteByliveDate(Date date) {
		// TODO Auto-generated method stub
		try (Connection cn = DriverManager.getConnection(url, userName, password);) {
			PreparedStatement ps = cn.prepareStatement("DELETE\r\n" + "FROM playback\r\n" + "WHERE liveDate=?");
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ps.executeUpdate();
			System.out.println("ɾ��������ʱ��Ϊ" + date.toLocaleString() + "�Ļط���Ƶ��Ϣ");
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
			System.out.println("ɾ���ļ���Ϊ" + fileName + "�Ļط���Ƶ��Ϣ");
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
			
			while(rs.next()) {
				pb.setId(rs.getLong("id"));
				pb.setStreamName(rs.getString("streamName"));
				pb.setLiveDate(rs.getDate("liveDate"));
				pb.setFileName(rs.getString("fileName"));
				pb.setRed5URL(rs.getString("red5URL"));
				System.out.println("�ҵ���idΪ"+id+"����Ƶ��Ϣ");
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
		return null;
	}

	@Override
	public List<playback> getByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public playback getByfileName(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<playback> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
