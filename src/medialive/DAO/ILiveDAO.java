package medialive.DAO;

import java.util.List;

import medialive.domain.live;

/**
 * ��ɾ���
 * @author gaoji
 *
 */
public interface ILiveDAO {
	
	/**
	 * ����ֱ����Ϣ
	 * @param liveDemoֱ������
	 * @return
	 */
	boolean save(live liveDemo);
	/**
	 * ɾ��ֱ���������е�����(Ԫ��)
	 * @return
	 */
	boolean clear();
	/**
	 * ɾ��ָ��roomName��Ԫ��
	 * @param roomName
	 * @return
	 */
	boolean deleteByroom(String roomName);
	/**
	 * ɾ��ָ��streamName��Ԫ��
	 * @param streamName
	 * @return
	 */
	boolean deleteBystream(String streamName);
	/**
	 * ����ֱ����Ϣ(idΪ����)
	 * @param liveDemo
	 * @return
	 */
	boolean updateLive(live liveDemo);
	/**
	 * ����id��ѯ��Ӧ��live����
	 * @param id
	 * @return ��ѯ������live����,�鲻������null
	 */
	live getByid(Long id);
	/**
	 * ����������ѯlive����
	 * @param streamName
	 * @return �����ѯ���˷���live���� �鲻������null
	 */
	live getBystream(String streamName);
	/**
	 * ���ݷ�������ѯ��Ӧlive����
	 * @param roomName ֱ��������
	 * @return ��ѯ������live����,�鲻������null
	 */
	live getByroom(String roomName);
	/**
	 * �г����е�Ԫ��
	 * @return ��ѯ����������Ԫ��,�鲻������һ���ռ���
	 */
	List<live> list();
	
	

}
