//package com.bdsoft;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.aspectj.util.FileUtil;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.type.TypeReference;
//
//import com.alibaba.fastjson.JSON;
//import com.bdsoft.utils.Lz4Compress;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//
//public class JsonTest {
//
//	public static void main(String[] args) throws Exception {
//		String path = "/home/dcy/tmp/bigjson";
//		File file = new File(path);
//		byte[] data = FileUtil.readAsByteArray(file);
//		String json = new String(data);
//		System.out.println("json-size=" + json.length() / 1024 + "k");
//		byte[] comp = Lz4Compress.compress(data);
//		System.out.println(new String(comp));
//
//		int loops = 1;
//
//		long time1 = System.currentTimeMillis();
//		for (int i = 0; i < loops; i++) {
//			JSON.parseArray(json);
//		}
//		System.out.println("fastjson耗时=" + (System.currentTimeMillis() - time1));
//
//		time1 = System.currentTimeMillis();
//		GsonBuilder gb = new GsonBuilder();
//		for (int i = 0; i < loops; i++) {
//			// gb.create().fromJson(json, new Designer[] {}.getClass());
//			gb.create().fromJson(json, new TypeToken<List<Designer>>() {
//			}.getType());
//		}
//		System.out.println("gson耗时=" + (System.currentTimeMillis() - time1));
//
//		time1 = System.currentTimeMillis();
//		ObjectMapper om = new ObjectMapper();
//		for (int i = 0; i < loops; i++) {
//			// om.readValue(json, new Designer[] {}.getClass());
//			om.readValue(json, new TypeReference<List<Designer>>() {
//			});
//		}
//		System.out.println("jackson耗时=" + (System.currentTimeMillis() - time1));
//
//		// 准备数据
//		List<JsonObj> list = new ArrayList<JsonObj>();
//		for (int i = 0; i < 500000; i++) {
//			JsonObj obj = new JsonObj();
//			obj.setId(i);
//			obj.setName("name" + String.valueOf(i));
//			list.add(obj);
//		}
//		Gson gson = new GsonBuilder().create();
//		String str = gson.toJson(list);
//
//		// 1,gson解析
//		long start1 = System.currentTimeMillis();
//		List l = gson.fromJson(str, new TypeToken<List<JsonObj>>() {
//		}.getType());
//		System.out.println("gson time elapse:" + (System.currentTimeMillis() - start1));
//
//		// 2,jackson解析
//		ObjectMapper mapper = new ObjectMapper();
//		long start2 = System.currentTimeMillis();
//		List l2 = mapper.readValue(str, new TypeReference<List<JsonObj>>() {
//		});
//		System.out.println("jackson time elapse:" + (System.currentTimeMillis() - start2));
//	}
//
//}
//
//class JsonObj {
//	private int id;
//	private String name;
//
//	public JsonObj() {
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//}
//
//@JsonIgnoreProperties(value = { "case" })
//class Designer {
//	private Follows follows;
//	private Ums ums;
//
//	// private Case cases;
//	public Designer() {
//	}
//
//	public Follows getFollows() {
//		return follows;
//	}
//
//	public void setFollows(Follows follows) {
//		this.follows = follows;
//	}
//
//	public Ums getUms() {
//		return ums;
//	}
//
//	public void setUms(Ums ums) {
//		this.ums = ums;
//	}
//
//}
//
//class Follows {
//	private int follows;
//
//	public Follows() {
//	}
//
//	public int getFollows() {
//		return follows;
//	}
//
//	public void setFollows(int follows) {
//		this.follows = follows;
//	}
//}
//
//@JsonIgnoreProperties(value = { "profile", "providerUserId", "customizedData", "provider", "groupids", "extra",
//		"modified", "email", "status", "allowedTenants" })
//class Ums {
//	private String lastName;
//	private long created;
//	private String uid;
//	private String firstName;
//
//	public Ums() {
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public long getCreated() {
//		return created;
//	}
//
//	public void setCreated(long created) {
//		this.created = created;
//	}
//
//	public String getUid() {
//		return uid;
//	}
//
//	public void setUid(String uid) {
//		this.uid = uid;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//}