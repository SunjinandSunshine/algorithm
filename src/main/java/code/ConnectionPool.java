//package code;
//
///**
// * @Author SunnyJ
// * @Date 2022/8/30 17:15
// */
//public class ConnectionPool {
//    // 空闲线程集合
//    private List<Connection> freeConnection = new Vector<Connection>();
//    // 活动线程集合
//    private List<Connection> activeConnection = new Vector<Connection>();
//    // 记录线程总数
//    private static int connCount = 0;
//    private DbBean dbBean;
//    public ConnectionPool(DbBean dbBean) {
//        this.dbBean = dbBean;
//        init();
//    }
//    public void init() {
//        try {
//            for (int i = 0; i < dbBean.getInitConnections(); i++) {
//                Connection newConnection = newConnection();
//                if (newConnection != null) {
//                    // 添加到空闲线程中...
//                    freeConnection.add(newConnection);
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    // 创建新的Connection
//    private Connection newConnection() {
//        try {
//            if (dbBean == null) {
//                return null;
//            }
//            Class.forName(dbBean.getDriverName());
//            Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(),
//                    dbBean.getPassword());
//            connCount++;
//            return connection;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public Connection getConnection() {
//        // * ####1.判断活动线程数是否大于最大线程 如果大于最大线程数,则进行等待...<br>
//        Connection connection = null;
//        try {
//            if (connCount < dbBean.getMaxActiveConnections()) {
//                // 还有活动线程可以使用
//                // * ####2.判断空闲线程数是否大于0 如果空闲线程数<0，创建新的连接<br>
//                if (freeConnection.size() > 0) {
//                    connection = freeConnection.remove(0);
//                } else {
//                    // 创建新的连接
//                    connection = newConnection();
//                }
//                boolean available = isAvailable(connection);
//                if (available) {
//                    activeConnection.add(connection);
//                } else {
//                    connCount--;// i--操作
//                    connection = getConnection();// 递归调用getConnection方法
//                }
//            } else {
//                // 大于最大线程数,进行等待,重新获取连接
//                wait(dbBean.getConnTimeOut());
//                connection = getConnection();// 递归调用getConnection方法
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // * ####3.如果空闲线程数>0，则获取当前空闲线程,存入在活动线程集合中 <br>
//        return connection;
//    }
//
//    // 判断连接是否可用
//    public boolean isAvailable(Connection connection) {
//        try {
//            if (connection == null || connection.isClosed()) {
//                return false;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return true;
//    }
//
//    public void releaseConnection(Connection connection) {
//        try {
//            if (connection == null) {
//                return;
//            }
//            if (isAvailable(connection)) {
//                // 判断空闲线程数是否大于最大线程数
//                if (freeConnection.size() < dbBean.getMaxConnections()) {
//                    freeConnection.add(connection);
//                } else {
//                    // 空闲线程数已经满了
//                    connection.close();
//                }
//                activeConnection.remove(connection);
//                connCount--;
//                notifyAll();
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//    public class DbBean {
//
//        /* 链接属性 */
//        private String driverName = "com.mysql.jdbc.Driver";
//
//        private String url = "jdbc:mysql://localhost:3306/test";
//
//        private String userName = "root";
//
//        private String password = "root";
//
//        private String poolName = "thread01";// 连接池名字
//
//        private int minConnections = 1; // 空闲池，最小连接数
//
//        private int maxConnections = 10; // 空闲池，最大连接数
//
//        private int initConnections = 5;// 初始化连接数
//
//        private long connTimeOut = 1000;// 重复获得连接的频率
//
//        private int maxActiveConnections = 100;// 最大允许的连接数，和数据库对应
//
//        private long connectionTimeOut = 1000 * 60 * 20;// 连接超时时间，默认20分钟
//    }
//}
