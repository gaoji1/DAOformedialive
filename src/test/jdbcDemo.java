package test;

import java.sql.SQLException;

import medialive.DAO.impl.playbackDAOImpl;
import medialive.domain.playback;

public class jdbcDemo {
	public static void main(String[] args) throws SQLException, InterruptedException {

		playbackDAOImpl playbackDAO = new playbackDAOImpl();
		playbackDAO.init();
		playback pb = new playback();
		pb.setStreamName("firststream");
		pb.setFileName("≤‚ ‘Œƒº˛√˚");
		pb.setRed5URL("≤‚ ‘URL2");
		java.util.Date dt = new java.util.Date();
		pb.setLiveDate(dt);
		playbackDAO.save(pb);
	}

}
