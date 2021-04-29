package miniCodePrjPkg;

public class perf {
	
	
	   /**
     * 返回斐波那契数第n个值,n从0开始
     * 实现方式，基于递归实现
     * @param n
     * @return
     * @author mhy2011@163.com
     * @since 2015年8月18日上午9:41:30
     */
    public static int getFib(int n){
        if(n < 0){
            return -1;
        }else if(n == 0){
            return 0;
        }else if(n == 1 || n ==2){
            return 1;
        }else{
            return getFib(n - 1) + getFib(n - 2);
        }
    }
	
	public static void main(String[] args) {
		System.out.println("---");
		System.out.println(getFib(39));
	}

}
