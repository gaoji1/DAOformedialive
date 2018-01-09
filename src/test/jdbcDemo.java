package test;

import java.sql.SQLException;

import medialive.DAO.impl.playbackDAOImpl;
import medialive.domain.playback;

public class jdbcDemo {
	public static void main(String[] args) throws SQLException, InterruptedException {

		playbackDAOImpl playbackDAO = new playbackDAOImpl();
		playbackDAO.init();
//		创建playback对象并赋值
		playback pb = new playback();
		pb.setStreamName("firststream");
		pb.setFileName("测试文件名");
		pb.setRed5URL("测试URL2");
		java.util.Date dt = new java.util.Date();
		pb.setLiveDate(dt);
//		playbackDAO.save(pb);
//		playbackDAO.deleteByid(1L);
		playbackDAO.deleteBystreamName("firststream");
	}

}
