package medialive.DAO;


import java.util.Date;
import java.util.List;

import medialive.domain.playback;

/**
 * ��ɾ��,û�и�,ֱ��¼����Ϣһ����д�벻������޸�(�Ժ���ܻ����)
 * 
 * @author gaoji
 *
 */
public interface IPlaybackDao {

	/**
	 * ��ӻط���Ϣ
	 * 
	 * @param playbackDemo
	 *            playback����
	 * @return
	 */
	boolean save(playback playbackDemo);

	/**
	 * ����������
	 * 
	 * @return
	 */
	boolean clean();

	/**
	 * ����idɾ��Ԫ��
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteByid(Long id);

	/**
	 * ����������ɾ��Ԫ��
	 * 
	 * @param streamName
	 * @return
	 */
	boolean deleteBystreamName(String streamName);

	/**
	 * ����ָ������ɾ����Ƶ
	 * 
	 * @param date
	 * @return
	 */
	boolean deleteByliveDate(Date date);

	/**
	 * �����ļ���ɾ����Ƶ
	 * 
	 * @param fileName
	 * @return
	 */
	boolean deleteByfileName(String fileName);

	/**
	 * ��id����Ƶ��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	playback getByid(Long id);

	/**
	 * ���������Ʋ�����Ƶ��Ϣ
	 * 
	 * @param streamName
	 * @return
	 */
	List<playback> getBystreamName(String streamName);

	/**
	 * �������ڲ�����Ƶ��Ϣ
	 * 
	 * @param date
	 * @return
	 */
	List<playback> getByDate(Date date);

	/**
	 * �����ļ���������Ƶ��Ϣ,�ļ���Ψһ
	 * 
	 * @param fileName
	 * @return
	 */
	playback getByfileName(String fileName);

	/**
	 * �г���ǰ���е���Ƶ��Ϣ
	 * 
	 * @return
	 */
	List<playback> list();

}
