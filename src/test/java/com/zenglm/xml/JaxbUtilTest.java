/**
 * CopyRight 2010 商盟商务服务有限公司
 *
 * http://www.sumpay.cn
 */

package com.zenglm.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zenglm.dts.utils.JaxbUtil;

/**
 * @class JaxbUtil.java
 * @creater ss_sunshan@163.com
 * @date 2014-10-9 下午01:19:06
 * @declaration
 */

public class JaxbUtilTest {
	
	public static void objectToXml() {
		/*User user = new User();
		user.setId(1L);
		user.setName("word");
		user.getLetter().add("a");
		user.getLetter().add("b");
		String xml = JaxbUtil.toXml(user, "UTF-8");
		System.out.println("Jaxb Object to Xml result:\n" + xml);*/
	}
	
	public static void xmlToObject() throws Exception { 
		/*String xml = generateXmlByDom4j();
		System.out.println(xml);
		
		Document doc = DocumentHelper.parseText(xml);
		Element e = doc.getRootElement();
		System.out.println("---: "+ e.attribute("id").getValue());
		Element letter = (Element) doc.selectSingleNode("//letters");
		System.out.println("---: "+ letter.elements().size());
		System.out.println("---: "+ ((Element) letter.elements().get(0)).getText());
		
		
		User user = JaxbUtil.fromXml(xml, User.class);
		System.out.println("Jaxb Xml to Object result:\n" + user);
		System.out.println(user.getLetter().size());
		System.out.println(user.getName());  */
	}
	
	public static void toXmlWithListAsRoot() {
		User user1 = new User();
		user1.setId(1L);
		user1.setName("test");

		User user2 = new User();
		user2.setId(2L);
		user2.setName("test1");

		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);

		String xml = JaxbUtil.toXml(userList, "userList", User.class, "UTF-8");
		System.out.println("Jaxb Object List to Xml result:\n" + xml);
	}


	
	public static void main(String[] args) throws Exception {  
		//objectToXml();
		xmlToObject();
		//toXmlWithListAsRoot();
	}
	
	private static String generateXmlByDom4j() {
		/*Document document = DocumentHelper.createDocument();
		Element root = document.addElement("user").addAttribute("id", "1");
		root.addElement("name").setText("test");
		//List<String>
		Element interests = root.addElement("letters");
		interests.addElement("letter").addText("a");
		interests.addElement("letter").addText("b");
		return document.asXML();*/
		return null;
	}
	
	@XmlRootElement
	//指定子节点的顺序
	@XmlType(propOrder = { "name", "letter" })
	private static class User {

		private Long id;
		private String name;
		private String password;

		private List<String> letter = new ArrayList<String>();

		//设置转换为xml节点中的属性
		@XmlAttribute
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		//设置不转换为xml
		@XmlTransient
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		//设置对List<String>的映射, xml为<letters><letter>a</letter></letters>
		@XmlElementWrapper(name = "letters")
		@XmlElement(name = "letter")
		public List<String> getLetter() {
			return letter;
		}

		public void setLetter(List<String> letter) {
			this.letter = letter;
		}

		@Override
		public String toString() {
			ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	
	
	/**
	 * 
	 * 
	 * 			<!-- XML begin -->
			<dependency>
				<groupId>dom4j</groupId>
				<artifactId>dom4j</artifactId>
				<version>1.6.1</version>
				<exclusions>
					<exclusion>
						<groupId>xml-apis</groupId>
						<artifactId>xml-apis</artifactId>
					</exclusion>
				</exclusions>
			</dependency>
			<dependency>
				<groupId>jaxen</groupId>
				<artifactId>jaxen</artifactId>
				<version>1.1.4</version>
			</dependency>	
	 * 
	 * 
	 */

}
