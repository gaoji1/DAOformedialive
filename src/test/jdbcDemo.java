package test;

import java.sql.SQLException;

import medialive.DAO.impl.liveDAOImpl;
import medialive.domain.live;

public class jdbcDemo {
	public static void main(String[] args) throws SQLException, InterruptedException {

		liveDAOImpl liveDAO = new liveDAOImpl();
		live liveDemo = new live();
//		liveDemo.setRoomName("�Ҿ���Ҫ����!");
//		liveDemo.setStreamName("firstStream");
//		liveDAO.updateLive(liveDemo);
		
		
	}

}
