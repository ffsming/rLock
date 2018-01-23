# rLock
利用redis Set实现redis分布式锁
使用示例：
    
    // name 唯一标识 建议 类名+方法名，默认为""
    // expireTime 过期时间，单位：秒 ，默认为50
    @RLock(name="dd" , expireTime = 10)
    @RequestMapping("/test")
    public void test(){
	log.info("3232323");
	return;
     }
	
