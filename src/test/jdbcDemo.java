package test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import medialive.DAO.impl.playbackDAOImpl;
import medialive.domain.playback;

public class jdbcDemo {
	public static void main(String[] args) throws SQLException, InterruptedException {

		playbackDAOImpl playbackDAO = new playbackDAOImpl();
		playbackDAO.init();
//		����playback���󲢸�ֵ
		List<playback> pbList = playbackDAO.getBystreamName("firststream");
		if(pbList.isEmpty()) {
			System.out.println("û���ҵ�����Ҫ�����Ƶ��Ϣ");
		}
		for(playback pb:pbList) {
			System.out.println(pb.toString());
		}
		

	}

}
