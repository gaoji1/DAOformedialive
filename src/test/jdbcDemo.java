package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import medialive.DAO.impl.liveDAOImpl;
import medialive.domain.live;

public class jdbcDemo {
	public static void main(String[] args) throws SQLException, InterruptedException {

		liveDAOImpl liveDAO = new liveDAOImpl();
//		live liveDemo = new live();
//		liveDemo.setRoomName("�Ҿ���Ҫ����!");
//		liveDemo.setStreamName("firstStream");
//		liveDAO.updateLive(liveDemo);
		List<live> liveDemos = new ArrayList<live>();
		liveDAO.init();
		liveDemos = liveDAO.list();
		if(liveDemos.isEmpty()) {
			System.out.println("��ǰû��ֱ����");
		}
		for(live e:liveDemos) {
			System.out.println(e.toString());
		}

		
	}

}
