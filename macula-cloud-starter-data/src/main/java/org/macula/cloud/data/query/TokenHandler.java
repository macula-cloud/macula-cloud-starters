package org.macula.cloud.data.query;

/**
 * <p> <b>TokenHandler</b> Copy from MyBatis </p>
 */
public interface TokenHandler {

	/** 解析内容 */
	String handleToken(String content);
}
