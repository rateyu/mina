//
//public class ConnectionSource {
//    public static void main(String[] args){
//        long begin=System.currentTimeMillis();
//        for(int i=0;i<10000;i++){
//            Connection conn=DBManager.getConn();
//            System.out.print(i+"   ");
//            DBManager.closeConn(conn);
//        }
//        long end=System.currentTimeMillis();
//        System.out.println("用时："+(end-begin));
//    }
//}
