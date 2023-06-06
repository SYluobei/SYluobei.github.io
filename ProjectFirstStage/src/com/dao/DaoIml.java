package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bean.Book;
import com.bean.Goods;
import com.bean.Order;
import com.bean.ShoppingCart;
import com.bean.ThreeBook;
import com.bean.TopThree;
import com.bean.User;
import com.util.Util;

public class DaoIml implements Dao{
	Util util = new Util();
	QueryRunner qr = new QueryRunner(util.datasource);
	
	/**
	 * 1.1登录
	 */
	@Override
	public boolean login(String user_name, String pwd) {
		String sql = "SELECT user_name,pwd FROM user WHERE user_name = ? AND pwd = ?";
		Object params[] = {user_name,pwd};
		try {
			List<User> list = qr.query(sql, params, new BeanListHandler<>(User.class));
			if(!list.isEmpty()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 *判断用户名是否重复 
	 */
	@Override
	public boolean judgeExist(String user_name) {
		String sql = "SELECT user_name FROM user WHERE user_name = ?";
		Object params[] = {user_name};
		try {
			String name = qr.query(sql, params, new ScalarHandler<>());
			if(name!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 1.2注册
	 */
	@Override
	public void register(User user) {
		String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
		Object params[] = {user.getUser_id(),user.getUser_name(),user.getPwd(),user.getMoney(),user.getPermission()};
		try {
			 qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1.3充值
	 */
	@Override
	public void recharge(String user_name, double money) {
		String sql = "UPDATE user SET money = ? WHERE user_name = ?";
		Object params[] = {money,user_name};
		try {
			 qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询余额
	 */
	@Override
	public double queryMoney(String user_name) {
		String sql = "SELECT money FROM user WHERE user_name = ?";
		Object params = user_name;
		try {
			double money =  qr.query(sql, params, new ScalarHandler<>());
			 return money;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取用户权限类型
	 */
	@Override
	public int getPermission(String user_name) {
		String sql = "SELECT permission FROM user WHERE user_name = ?";
		Object params = user_name;
		try {
			int permission =  qr.query(sql, params, new ScalarHandler<>());
			 return permission;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 1.2删除用户（admin）
	 */
	@Override
	public void delUser(String user_name) {
		String sql = "DELETE FROM user WHERE user_name = ?";
		Object params = user_name;
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看所有用户
	 */
	@Override
	public List<User> showAllUser() {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM user";
		try {
			list =  qr.query(sql, new BeanListHandler<>(User.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 1.3修改用户（admin）
	 */
	@Override
	public void changeUser(String user_name, User user) {
		String sql = "UPDATE user SET user_name = user_name";
		List<Object> params = new ArrayList<>();
		if(!user.getUser_name().isEmpty()) {
			sql += ", user_name = ? ";
			params.add(user.getUser_name());
		}
		if(!user.getPwd().isEmpty()) {
			sql += ", pwd = ? ";
			params.add(user.getPwd());
		}
		if(user.getMoney()!=-1) {
			sql += ", money = ? ";
			params.add(user.getMoney());
		}
		if(user.getPermission()!=0) {
			sql += ", permission = ? ";
			params.add(user.getPermission());
		}
		sql += " WHERE user_name = ?";
		params.add(user_name);		
		try {
			qr.update(sql, params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1.5多条件查询用户（admin）
	 */
	@Override
	public List<User> showByCofiUser(User user, double maxmoney, double minmoney) {
		String sql = "SELECT * From user WHERE 1=1 ";
		List<Object> params = new ArrayList<>();
		if(!user.getUser_name().isEmpty()) {
			sql += " AND user_name = ? ";
			params.add(user.getUser_name());
		}
		if(!user.getPwd().isEmpty()) {
			sql += " AND pwd = ? ";
			params.add(user.getPwd());
		}
		if(maxmoney!=-1&&minmoney!=-1) {
			sql += " AND money <= ? ";
			sql += " AND money >= ? ";
			params.add(maxmoney);
			params.add(minmoney);
		}
		if(user.getPermission()!=0) {
			sql += " AND permission = ? ";
			params.add(user.getPermission());
		}
		try {
			List<User> list = qr.query(sql, params.toArray(), new BeanListHandler<>(User.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 1.4查看所有书籍(Book)
	 */
	@Override
	public List<Book> showAllBook() {
		List<Book> list = new ArrayList<>();
		String sql = "SELECT * FROM book";
		try {
			list =  qr.query(sql, new BeanListHandler<>(Book.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//判断书名是否存在
	@Override
	public boolean judgeBookExist(String book_name) {
		String sql = "SELECT book_name FROM book WHERE book_name = ?";
		Object params = book_name;
		try {
			String name = qr.query(sql, params, new ScalarHandler<>());
			if(name!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 1.1添加书籍
	 */
	@Override
	public void addBook(Book book) {
		String sql = "INSERT INTO book VALUES(?,?,?,?)";
		Object params[] = {book.getBook_id(),book.getBook_name(),book.getPrice(),book.getRemain_num()};
		try {
			 qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1.2删除书籍
	 */
	@Override
	public void delBook(String book_name) {
		String sql = "DELETE FROM book WHERE book_name = ?";
		Object params = book_name;
		try {
			 qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1.3修改书籍
	 */
	@Override
	public void changeBook(String book_name, Book book) {
		String sql = "UPDATE book SET book_name = book_name";
		List<Object> params = new ArrayList<>();
		if(!book.getBook_name().isEmpty()) {
			sql += ", book_name = ? ";
			params.add(book.getBook_name());
		}
		if(book.getPrice()!=-1) {
			sql += ", price = ? ";
			params.add(book.getPrice());
		}
		if(book.getRemain_num()!=0) {
			sql += ", remain_num = ? ";
			params.add(book.getRemain_num());
		}
		sql += " WHERE book_name = ?";
		params.add(book_name);		
		try {
			qr.update(sql, params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 1.5多条件查询书籍
	 */
	@Override
	public List<Book> showByCofiBook(String book_name, double maxprice, double minprice, int max_num, int min_num) {
		String sql = "SELECT * From book WHERE 1=1 ";
		List<Object> params = new ArrayList<>();
		if(!book_name.isEmpty()) {
			sql += " AND book_name = ? ";
			params.add(book_name);
		}
		if(maxprice!=-1&&minprice!=-1) {
			sql += " AND price <= ? ";
			sql += " AND price >= ? ";
			params.add(maxprice);
			params.add(minprice);
		}
		if(max_num!=0&&min_num!=0) {
			sql += " AND remain_num <= ? ";
			sql += " AND remain_num >= ? ";
			params.add(max_num);
			params.add(min_num);
		}
		try {
			List<Book> list = qr.query(sql, params.toArray(), new BeanListHandler<>(Book.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *判断库存是否足够
	 */
	@Override
	public boolean judegeBookNum(int remain_num,String book_name) {
		String sql = "SELECT remain_num FROM book WHERE book_name = ? AND remain_num >= ?";
		Object params[] = {book_name,remain_num};
		try {
			Object num = qr.query(sql, params, new ScalarHandler<>());			
			if(num!=null) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取指定书名的书籍信息
	 */
	@Override
	public Book getBook(String book_name) {
		String sql = "SELECT * FROM book WHERE book_name = ?";
		Object param = book_name;
		try {
			Book book = qr.query(sql, param, new BeanHandler<>(Book.class));
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 商品信息保存
	 */
	@Override
	public void addGoods(Goods goods) {
		String sql = "INSERT INTO goods VALUES(?,?,?,?,?,?,?)";
		Object params[] = {goods.getGoods_id(),goods.getUser_id(),goods.getBook_id(),goods.getBook_name(),goods.getBuy_num(),goods.getPrice(),goods.getGoods_sum()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据用户名获取用户编号，再根据用户编号获取goods信息集合
	 */
	@Override
	public List<Goods> getGoodsList(String user_name) {
		String user_id = getUserId(user_name);
		String sql = "SELECT * FROM goods WHERE user_id = ?";
		Object param = user_id;
		try {
			List<Goods> goods = qr.query(sql, param, new BeanListHandler<>(Goods.class));
			return goods;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 9.修改库存，修改用户余额
	 */
	@Override
	public void tradeDown(String user_name, double sum,String order_id) {
		String sql = "SELECT user_name FROM user WHERE permission = 2";
		String buy_name = "";
		try {
			buy_name = qr.query(sql, new ScalarHandler<>());
			Connection con = null;
			try {
				con = util.datasource.getConnection();
				con.setAutoCommit(false);
				sql = "UPDATE user SET money = money - ? WHERE user_name = ?";
				Object params[] = {sum,user_name};
				qr.update(sql, params);
				sql = "UPDATE user SET money = money + ? WHERE user_name = ?";
				Object paramss[] = {sum,buy_name};
				qr.update(sql, paramss);
				List<Goods> list = getGoodsList(user_name);
				List<String> olist = getOrderGoodsList();	//获取OrderGoods中的订单单项号
				List<Goods> glist = new ArrayList<>();
				for(Goods i:list) {
					for(int j = 0;j<olist.size();j++) {
						if(i.getGoods_id().equals(olist.get(j))) {
							break;
						}
						if(j==olist.size()-1) {
							glist.add(i);
						}
					}
				}
				for(Goods i : glist) {
					sql = "UPDATE book SET remain_num = remain_num - ? WHERE book_name = ?";
					Object bookparams[] = {i.getBuy_num(),i.getBook_name()};
					qr.update(sql, bookparams);
				}
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally {
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}				
				}
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * 8.余额充足，录入订单表与订单关系表并打印订单信息
	 */
	@Override
	public Order getChangeOrder(String user_name,double sum,String remark,List<Goods> goodslist,String order_id) {
		Connection con = null;
		try {
			con = util.datasource.getConnection();
			con.setAutoCommit(false);
			String sql = "SELECT user_id FROM user WHERE user_name = ?";
			Object param = user_name;
			String user_id = qr.query(sql, param, new ScalarHandler<>());			
			LocalDateTime time = LocalDateTime.now();
			sql = "INSERT INTO orders VALUES(?,?,?,?,?)";
			Order order = new Order(order_id.toString(), sum, time.toString(), remark, user_id);
			Object params[] = {order_id.toString(),sum,time,remark,user_id};
			qr.update(sql, params);
			for(Goods i : goodslist) {
				sql = "INSERT INTO ordergoods VALUES(?,?,?)";
				Object paramss[] = {i.getGoods_id(),user_id,order_id.toString()};
				qr.update(sql, paramss);				
			}
			con.commit();
			return order;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * 加入购物车
	 */
	@Override
	public void putShoopingCart(String user_name, List<Goods> goodslist) {
		String user_id = getUserId(user_name);
		for(Goods i:goodslist) {
			String sql = "INSERT INTO shoppingcart (goods_sum,buy_num,book_name,user_id) VALUES(?,?,?,?)";
			Object params[] = {i.getGoods_sum(),i.getBuy_num(),i.getBook_name(),user_id};
			try {
				qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}

	/**
	 * 1.2查看购物车
	 */
	@Override
	public List<ShoppingCart> queryShoppingCart(String user_name) {
		String user_id = getUserId(user_name);
		String sql = "SELECT * FROM shoppingcart WHERE user_id = ?";
		Object param = user_id;
		try {
			List<ShoppingCart> list = qr.query(sql, param, new BeanListHandler<>(ShoppingCart.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据用户名获取用户id
	 */
	@Override
	public String getUserId(String user_name) {
		String sql = "SELECT user_id FROM user WHERE user_name = ?";
		Object param = user_name;
		try {
			String user_id = qr.query(sql, param, new ScalarHandler<>());
			return user_id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除该用户的购物车
	 */
	@Override
	public void cleanCart(String user_name) {
		String user_id = getUserId(user_name);
		String sql = "DELETE FROM shoppingcart WHERE user_id = ?";
		Object param = user_id;
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查看订单
	 */
	@Override
	public List<Order> getOrder() {
		String sql = "SELECT * FROM orders";
		try {
			List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class));
			sql = "SELECT date FROM orders";
			List<Object> list1 = qr.query(sql, new ColumnListHandler<>());
			for(int i = 0; i<list.size();i++) {
				list.get(i).setDate(list1.get(i).toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过用户id找到用户name
	 */
	@Override
	public String getNameById(String user_id) {
		String sql = "SELECT user_name FROM user WHERE user_id = ?";
		Object param = user_id;
		try {
			String name = qr.query(sql, param, new ScalarHandler<>());
			return name;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 1.7.查看前三名重要的买家
	 */
	@Override
	public List<TopThree> getTopThree() {		
		String sql = "SELECT * FROM topthree ORDER BY sum_money DESC,buy_times DESC limit 3";		
		try {
			List<TopThree> list = qr.query(sql, new BeanListHandler<>(TopThree.class));
			return list;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新topthree表
	 */
	@Override
	public void refershTop() {
		String sql = "DELETE FROM topthree";
		try {
			qr.update(sql);
			sql = "SELECT user_id FROM user";
			List<String> list = qr.query(sql, new ColumnListHandler<>());
			for(String i : list) {
				double sum_money = getSumById(i);	//根据id获取orders表中的sum总和
				int buy_times = (int)getTimesById(i);	//根据id获取orders表中id出现过几次
				sql = "INSERT INTO topthree VALUES(?,?,?)";
				Object params[] = {i,sum_money,buy_times};
				qr.update(sql, params);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 据id获取orders表中id出现过几次
	 */
	private long getTimesById(String i) {
		String sql = "SELECT COUNT(user_id) FROM orders WHERE user_id = ?";
		Object param = i;
		try {
			long num = qr.query(sql,param, new ScalarHandler<>());
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 根据id获取orders表中的sum总和
	 */
	private double getSumById(String i) {
		String sql = "SELECT sum FROM orders WHERE user_id = ?";
		Object param = i;
		try {
			List<Double> list = qr.query(sql, param, new ColumnListHandler<>());
			double sum = 0;
			for(double j:list) {
				sum+=j;
			}
			return sum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 1.8.显示本年某个月每周流水量
	 */
	@Override
	public Map<String,Double> showWeekIncome(String month) {
		String date = "2023-"+month;
		String sql = "SELECT * FROM orders";
		try {
			List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class));
			sql = "SELECT date FROM orders";
			List<Object> datelist = qr.query(sql, new ColumnListHandler<>());
			for(int i = 0; i<list.size(); i++) {
				list.get(i).setDate(datelist.get(i).toString());
			}
			list = list.stream().filter(i->i.getDate().startsWith(date)).collect(Collectors.toList());
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double sum4 = 0;
			for(Order i : list) {
				String[] arr = i.getDate().split("-");
				int num = Integer.valueOf(arr[2]);
				if(num<8) {
					sum1+=i.getSum();
				}else if(num<15){
					sum2+=i.getSum();
				}else if(num<22) {
					sum3+=i.getSum();
				}else {
					sum4+=i.getSum();
				}
			}
			Map<String,Double> map = new LinkedHashMap<>();
			map.put("第一周", sum1);
			map.put("第二周", sum2);
			map.put("第三周", sum3);
			map.put("第四周", sum4);
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取库存为零的书籍名称
	 */
	@Override
	public List<String> getBookNameByRe() {
		String sql = "SELECT book_name FROM book WHERE remain_num = 0";
		try {
			List<String> list = qr.query(sql, new ColumnListHandler<>());
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 1.9.显示售出数量最少的三本书并设置优惠金额
	 */
	@Override
	public List<ThreeBook> getThreeBook() {
		String sql = "SELECT t.book_name,IFNULL(c.count,0) sel_num,t.price FROM (SELECT count(book_name) count,book_name FROM goods GROUP BY book_name) c right join (SELECT book_name,price FROM book) t on c.book_name = t.book_name ORDER BY sel_num ASC LIMIT 3";
		try {
			List<ThreeBook> list = qr.query(sql, new BeanListHandler<>(ThreeBook.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置优惠金额
	 */
	@Override
	public void setPrice(String book_name, int discount) {
		String sql = "UPDATE book SET price = price - ? WHERE book_name = ?";
		Object params[] = {discount,book_name};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据订单编号获取订单单项编号，再获取订单单项表
	 */
	@Override
	public List<Goods> getGoodsListByOrder(String order_id) {
		String sql = "SELECT g.goods_id,g.user_id,g.book_id,g.book_name,g.buy_num,g.price,g.goods_sum FROM ordergoods s JOIN goods g ON s.goods_id = g.goods_id WHERE s.order_id = ?";
		Object param = order_id;
		try {
			List<Goods> goods = qr.query(sql, param, new BeanListHandler<>(Goods.class));
			return goods;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//获取OrderGoods中的订单单项号
	@Override
	public List<String> getOrderGoodsList() {
		String sql = "SELECT goods_id FROM ordergoods";
		try {
			List<String> olist = qr.query(sql, new ColumnListHandler<>());
			return olist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
