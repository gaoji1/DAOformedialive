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
//		创建playback对象并赋值
		List<playback> pbList = playbackDAO.getBystreamName("firststream");
		if(pbList.isEmpty()) {
			System.out.println("没有找到符合要求的视频信息");
		}
		for(playback pb:pbList) {
			System.out.println(pb.toString());
		}
		

	}

}
