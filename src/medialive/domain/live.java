package medialive.domain;

public class live {
	private Long id;
	private String roomName; // ��������
	private String streamName; // ������
	private String red5URL; // red5URL����

	@Override
	public String toString() {
		return "live [id=" + id + ", roomName=" + roomName + ", streamName=" + streamName + ", red5URL=" + red5URL
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// getter setter����
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public String getRed5URL() {
		return red5URL;
	}

	public void setRed5URL(String red5url) {
		red5URL = red5url;
	}

}
