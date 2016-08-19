package com.nateiot.core.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangweiming
 *
 */
public enum ContentType {

	UNKNOW("application/octet-stream"),
	APK("application/octet-stream"),
	TXT("text/plain"),
	DOC("application/msword"),
	DOT("application/msword"),
	DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	XLS("application/vnd.ms-excel"),
	XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	PPT("application/vnd.ms-powerpoint"),
	PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
	PDF("application/pdf"),
	JPG("image/jpeg"),
	JPEG("image/jpeg"),
	PNG("image/png"),
	GIF("image/gif"),
	MP4("video/mp4"),
	MKV("video/mkv"),
	MP3("audio/mp3");

	private String value;

	private ContentType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return StringUtils.lowerCase(value);
	}
}
