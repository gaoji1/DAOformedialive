package medialive.domain;

import java.util.Date;

public class playback {
	private Long id;
	private String streamName;
	private Date liveDate;
	private String fileName;
	private String red5URL;
	@Override
	public String toString() {
		return "playback [id=" + id + ", streamName=" + streamName + ", liveDate=" + liveDate + ", fileName=" + fileName
				+ ", red5URL=" + red5URL + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public Date getLiveDate() {
		return liveDate;
	}
	public void setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRed5URL() {
		return red5URL;
	}
	public void setRed5URL(String red5url) {
		red5URL = red5url;
	}
	
	

}
