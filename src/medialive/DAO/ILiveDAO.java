package medialive.DAO;

import java.util.List;

import medialive.domain.live;

/**
 * 增删查改
 * @author gaoji
 *
 */
public interface ILiveDAO {
	
	/**
	 * 保存直播信息
	 * @param liveDemo直播对象
	 * @return
	 */
	boolean save(live liveDemo);
	/**
	 * 删除直播表中所有的内容(元组)
	 * @return
	 */
	boolean clear();
	/**
	 * 删除指定roomName的元组
	 * @param roomName
	 * @return
	 */
	boolean deleteByroom(String roomName);
	/**
	 * 删除指定streamName的元组
	 * @param streamName
	 * @return
	 */
	boolean deleteBystream(String streamName);
	/**
	 * 更改直播信息(id为主键)
	 * @param liveDemo
	 * @return
	 */
	boolean updateLive(live liveDemo);
	/**
	 * 根据id查询对应的live对象
	 * @param id
	 * @return 查询到返回live对象,查不到返回null
	 */
	live getByid(Long id);
	/**
	 * 根据流名查询live对象
	 * @param streamName
	 * @return 如果查询到了返回live对象 查不到返回null
	 */
	live getBystream(String streamName);
	/**
	 * 根据房间名查询对应live对象
	 * @param roomName 直播间名称
	 * @return 查询到返回live对象,查不到返回null
	 */
	live getByroom(String roomName);
	/**
	 * 列出所有的元组
	 * @return 查询到返回所有元组,查不到返回一个空集合
	 */
	List<live> list();
	
	

}
