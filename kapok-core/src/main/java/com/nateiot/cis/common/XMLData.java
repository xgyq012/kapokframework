package com.nateiot.cis.common;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author zhangweiming
 *
 */
public class XMLData {

	private Document document;

	public Document getDocument() {
		return document;
	}

	public XMLData(InputStream in) throws Exception {
		SAXReader reader = new SAXReader();
		this.document = reader.read(in);
	}
	
	public XMLData(File file) throws Exception {
		SAXReader reader = new SAXReader();
		this.document = reader.read(file);
	}
	
	public XMLData(String xmlString) throws Exception {
		this.document = DocumentHelper.parseText(xmlString);
	}
	
	public String getNodeText(String xpath) {
		Node node = this.document.selectSingleNode(xpath);
		if (node != null) {
			return node.getText();
		} else {
//			System.out.println("[WECHAT-getNodeText] 取值失败，请检查xpath:【" + xpath + "】是否正确！");
			return null;
		}
	}
	
	public void setNodeText(String xpath, String text) {
		Node node = this.document.selectSingleNode(xpath);
		if (node != null) {
			node.setText(text);
		} else {
//			System.out.println("[WECHAT-setNodeText] 赋值失败，请检查xpath:【" + xpath + "】是否正确！");
		}
	}
	
	public void printXml() throws Exception {
		StringWriter writer = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlwriter = new XMLWriter(writer, format);
		xmlwriter.write(this.document);
		xmlwriter.close();
		System.out.println(writer.toString());
	}
	
	public void writeToFile(File file) throws Exception {
		FileWriter writer = new FileWriter(file);
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlwriter = new XMLWriter(writer, format);
		xmlwriter.write(this.document);
		xmlwriter.close();
	}
	
	@Override
	public String toString() {
		return this.document.asXML();
	}
	
}
