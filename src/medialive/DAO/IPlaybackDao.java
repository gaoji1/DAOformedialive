package medialive.DAO;


import java.util.Date;
import java.util.List;

import medialive.domain.playback;

/**
 * 增删查,没有改,直播录像信息一旦被写入不能随便修改(以后可能会添加)
 * 
 * @author gaoji
 *
 */
public interface IPlaybackDao {

	/**
	 * 添加回放信息
	 * 
	 * @param playbackDemo
	 *            playback对象
	 * @return
	 */
	boolean save(playback playbackDemo);

	/**
	 * 清理整个表
	 * 
	 * @return
	 */
	boolean clean();

	/**
	 * 根据id删除元素
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteByid(Long id);

	/**
	 * 根据流名称删除元素
	 * 
	 * @param streamName
	 * @return
	 */
	boolean deleteBystreamName(String streamName);

	/**
	 * 根据指定日期删除视频
	 * 
	 * @param date
	 * @return
	 */
	boolean deleteByliveDate(Date date);

	/**
	 * 根据文件名删除视频
	 * 
	 * @param fileName
	 * @return
	 */
	boolean deleteByfileName(String fileName);

	/**
	 * 用id找视频信息
	 * 
	 * @param id
	 * @return
	 */
	playback getByid(Long id);

	/**
	 * 根据流名称查找视频信息
	 * 
	 * @param streamName
	 * @return
	 */
	List<playback> getBystreamName(String streamName);

	/**
	 * 根据日期查找视频信息
	 * 
	 * @param date
	 * @return
	 */
	List<playback> getByDate(Date date);

	/**
	 * 根据文件名查找视频信息,文件名唯一
	 * 
	 * @param fileName
	 * @return
	 */
	playback getByfileName(String fileName);

	/**
	 * 列出当前所有的视频信息
	 * 
	 * @return
	 */
	List<playback> list();

}
