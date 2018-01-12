package test;

import java.sql.SQLException;
import java.util.List;

import medialive.DAO.impl.liveDAOImpl;
import medialive.DAO.impl.playbackDAOImpl;
import medialive.domain.playback;

public class jdbcDemo {
	public static void main(String[] args) throws SQLException, InterruptedException {

		playbackDAOImpl playbackDAO = new playbackDAOImpl();
		liveDAOImpl li = new liveDAOImpl();
		playbackDAO.init();
		li.init();
		// 创建playback对象并赋值

		List<playback> pbList = playbackDAO.list();
		if(pbList.isEmpty()) {
			System.out.println("当前没有任何视频信息");
		}
		for(playback pb:pbList) {
			System.out.println(pb);
		}

	}
}
